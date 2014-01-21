
/**
package casa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import processing.core.PApplet;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;
import processing.core.PImage;
import processing.core.PVector;

public class Main extends PApplet {
    
public static int [] consumiValori = {
    918,
    975,
    993,
    1054,
    1065,
    1154,
    1204,
    1247,
    1270,
    1316,
};

public static String [] consumiNomi = {
    "Campobasso",
    "Benevento",
    "Cosenza",
    "Frosinone",
    "Crotone",
    "Lodi",
    "Cremona",
    "Siena",
    "Pavia",
    "Mantova"
};

public static int criticalLevel = 0;

public static int [] blockedAt = {
    4,3,2,2,2,1,1,1,0,0,1,1,1,1
};
    
public static final int GAME_WIDTH = 700;

public static boolean lightning = false;

//roba sull'albero
public static int ALBERO_Y = 600;
public static int ALBERO_X = 0;
public static int STROKE = 20;
public static double time;
public static float crescita = 0;
public static float maxLevel = 1;

public static double MOUSE_AFFECTION_X = 4;
public static double MOUSE_AFFECTION_Y = 15;
public static double MOUSE_MAX_DISTANCE = 1;

public static double TEMPO_DI_CRESCITA = .2;
public static double START_TIME_CRESCITA = 0;

public static int LIGHT_RADIUS = 70;

public static int GROWTH_RATE = 40;


public static Nodo nodoinit = new Nodo(GAME_WIDTH/2, 600);

public static int [][] numRamiChePartonoDaQui = new int[12][1000];
public static int [] numRamiIndex = new int[12];
public static double [][] valoriRandom = new double[12][100];
public static int[] valoriRandomIndex = new int[12];

public static int[] blackPixels = new int[GAME_WIDTH*GAME_WIDTH];

public static int[] indexes = new int[12];
public static int[] contaRami = new int[12];
public static int[] numRamiPerLivello = new int[12];

public static int[] dati_id = new int[11];

public static PImage shadowmap, sfondo, roba;


public ParticleSystem ps;

    public static void main(String args[]) {
        PApplet.main(new String[]{casa.Main.class.getName()});
    }
    
    
    public void setup() {
        size(GAME_WIDTH, GAME_WIDTH);
        background(100);
        frameRate(200);
        noSmooth();
        
        START_TIME_CRESCITA = System.currentTimeMillis();
        
        randomizzaNumeroRamiDaQui();
        randomizzaNumeriDaQui();
        
        for (int i = 0; i < 12; i++) {
            valoriRandomIndex[i] = 0;
            numRamiIndex[i] = 0;
            numRamiPerLivello[i] = 0;
            contaRami[i] = 0;
        }
        
        
        contaRami(1);
        criticalLevel = trovaLivello(10);
        
        for (int i = 0; i < 12; i++) {
            valoriRandomIndex[i] = 0;
            numRamiIndex[i] = 0;
            contaRami[i] = 0;
        }
        //loadImages();
        ps = new ParticleSystem(new PVector(width/2,650));
        Roba.applet = this;
        sfondo = loadImage("casa/sfondoalbero2.png");
        roba = loadImage("casa/pappa2.png");
        //riempiAlbero(nodoinit);
        
        createBackground();
        
        shadowmap = new PImage(GAME_WIDTH, GAME_WIDTH);
    }
    
    public void draw() {
        text("consumo: ", 0, 0);
        background(sfondo);
        time = Roba.applet.millis()/1000;
        
        if (lightning) {
            shadowMap();
        }
        
        
        stroke(255, 1);
        ps.addParticle();
        ps.run();
        
        noFill();
        stroke(255);
        strokeWeight(1);
        bezier(380 + mouseX/30, 540 +mouseY/30,  390 +mouseX/20, 540+mouseY/20,  420, 580,  300, 650);
        noStroke();
        
        fill(255);
        ellipse(380+mouseX/30, 540+mouseY/30, 10, 10);
        ellipse(375+mouseX/30, 550+mouseY/30, 7, 7);
        ellipse(383+mouseX/30, 549+mouseY/30, 4, 4);
        //filter(BLUR, 1);
        //ellipse(350,350,400,400);  
        
        cresci();
        smooth();
        
        for (int i = 0; i < 12; i++) {
            valoriRandomIndex[i] = 0;
            numRamiIndex[i] = 0;
            contaRami[i] = 0;
            iddd[i] = false;
        }
        
        
        Scritte.clear();
        
        jumping = 0; jumping7 = 0; jumping8 = 0; jumping9 = 0;
        centri.clear();
        centri.add(new Centro(350, 570, 100));
        idd = 0;
        
        drawTree(350, 600, 120, radians(90), 10, 1, 0);
        if (lightning) {
            image(shadowmap, 0, 0);
        }
        
        for (int i = 0; i < Scritte.size(); i++) {
            Scritte.get(i).draw(this);
        }
        image(roba, 0, 0);
    }        
        
    public static boolean iddd[] = new boolean[12];
    public static int idd = 0;
    public void drawTree (double x0, double y0, double len, double angle, int big, int currentLevel, int id) {
        if(currentLevel <= maxLevel) {
                
            if (currentLevel == maxLevel) {
                   len *= crescita;
               }
               double x1 = x0 + len * Math.cos(angle);
               double y1 = y0 - len * Math.sin(angle);
            try {
               if (currentLevel - (blockedAt[id -1 ] + criticalLevel) == 0 && iddd[id] == false) {
                   Scritte.add(new Testo("•" + consumiNomi[id -1 ], (int) x1, (int) y1));
                   iddd[id] = true;
               }
                if (currentLevel -  (blockedAt[id -1] + criticalLevel) > 0) {
                    return;
                }
            } catch(Exception e) {
            }
                
               
                    
               x1 = muoviConMouseX(x1, (mouseX - x1));
               y1 = muoviConMouseY(y1, (mouseY - y1));
               
               
               aggiungiCentro(x1, y1, currentLevel);
               
               stroke(255, 255/((float)currentLevel / 2) + 30);
               
               if (big <= 0) big = 1;
               strokeWeight((float) (big + .1));
               
               if (currentLevel == criticalLevel) {
                   idd++;
                   id = idd;
               }
               
               numRamiIndex[currentLevel]++;
               if (numRamiIndex[currentLevel] >= 99) {
                   numRamiIndex[currentLevel] = 0;
               }
  
               Roba.applet.line((int)x0, (int)y0, (int)x1, (int)y1);
               
               for (int i = 0; i < numRamiChePartonoDaQui[currentLevel][numRamiIndex[currentLevel]]; i++) {
                   valoriRandomIndex[currentLevel]++;
                   if (valoriRandomIndex[currentLevel] == 99) {valoriRandomIndex[currentLevel] = 0;}
                   drawTree(x1, y1, len * 0.80, angle + radians(90) * valoriRandom[currentLevel][valoriRandomIndex[currentLevel]], big-2, currentLevel + 1, id);
               }
               
        }
    }
    
    public static int jumping = 0, jumping7 = 0, jumping8 = 0, jumping9 = 0;
    public static int radius = 0;
    public static void aggiungiCentro (double x1, double y1, int currentLevel) {
        
        switch (currentLevel) {
            case 1: radius = 100;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 2: radius = 90;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 3: radius = 75;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 4: radius = 60;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 5: radius = 50;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 6: radius = 35;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 7: radius = 25;
                if (jumping7 >= 4) {
                    centri.add(new Centro((int)x1, (int)y1, radius)); jumping7 = 0;
                }
                else {
                    jumping7++;
                }
            
                break;
            case 8: radius = 12;
                if (jumping8 >= 6) {
                    centri.add(new Centro((int)x1, (int)y1, radius)); jumping8 = 0;
                }
                else {
                    jumping8++;
                }
                break;
            case 9: radius = 4;
                if (jumping9 >= 10) {
                    centri.add(new Centro((int)x1, (int)y1, radius)); jumping9 = 0;
                }
                else {
                    jumping9++;
                }
                break;
            default: radius = 0;
                break;
        }
    }
    
    public static void cresci() {
        if (maxLevel < 11) {
            crescita = (float) ((System.currentTimeMillis() - START_TIME_CRESCITA) /1000 / TEMPO_DI_CRESCITA);
            if (crescita >= 1) {
                aumentaLivelloMax();
            }
        }
    }
    
    public static void aumentaLivelloMax() {
            maxLevel += 1;
            crescita = 0;
            START_TIME_CRESCITA = System.currentTimeMillis();
    }
    
    
    
    
    
    
    public static double muoviConMouseX(double x, double distanceX) {
        if (distanceX > 0) {
                   x = x + (Math.sqrt(distanceX)) / MOUSE_AFFECTION_X;
               }
               else {
                   x = x + (-Math.sqrt(Math.abs(distanceX))) / MOUSE_AFFECTION_X;
               }
        return x;
    }
    
    public static double muoviConMouseY(double y, double distanceY) {
        if (distanceY > 0) {
                   y = y + (Math.sqrt(distanceY)) / MOUSE_AFFECTION_Y;
               }
               else {
                   y = y + (-Math.sqrt(Math.abs(distanceY))) / MOUSE_AFFECTION_Y;
               }
        return y;
      }
    
    
    public void randomizzaNumeroRamiDaQui () {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < Math.pow(2, i) + 1; j++) {
                numRamiChePartonoDaQui[i][j] = (int)(Math.random()*4) +1;
            }
        }
    } 
    
    public void randomizzaNumeriDaQui () {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 100; j++) {
                valoriRandom[i][j] = (Math.random() - .5);
                
                if (j != 0) {
                while (Math.abs(valoriRandom[i][j] - valoriRandom[i][j-1]) < 0.3) {
                    valoriRandom[i][j] = (Math.random() - .5);
                }
                }
            }
        }
    }
    
    public int trovaLivello(int numRami) {
        int rami = 0;
        int livelloDesiderato = 0;
        
        for (int i = 1; i < 10; i++) {
            if (numRamiPerLivello[i] >= numRami) {
                aggiustaNumeroRami(i, numRami);
                System.out.println(i);
                return i;
            }
        }
        return 0;
    }
    
    
    public static boolean[] first = new boolean[12];
    public static void contaRami (int currentLevel) {
        
        if (currentLevel >= 10) {return;}
        
        
        numRamiPerLivello[currentLevel]++;
        
        
        numRamiIndex[currentLevel]++;
               if (numRamiIndex[currentLevel] >= 99) {
                   numRamiIndex[currentLevel] = 0;
               }
               
        if (first[currentLevel]) {
            first[currentLevel] = false;
            indexes[currentLevel] = numRamiIndex[currentLevel];
        }
        
        for (int i = 0; i < numRamiChePartonoDaQui[currentLevel][numRamiIndex[currentLevel]]; i++) {
                   contaRami(currentLevel + 1);
        }
        
        
    }
    
    public void aggiustaNumeroRami (int livello, int numRami) {
        int rami = 0;
        
        System.out.println("Il livello da operare e'" + (livello));
        
        //conta il numero dei rami
        rami = numRamiPerLivello[livello];
        System.out.print("Il numero di rami su quel livello e' " + rami);
        
        while (rami > numRami) {
        
        for (int i = 0; i < Math.pow(2, livello); i++) {
            if (numRamiChePartonoDaQui[livello - 1][indexes[livello] + i] > 1 && rami > numRami) {
                numRamiChePartonoDaQui[livello - 1][indexes[livello] + i] -= 1;
                rami--;
            }
            
        }
        
        
        }
        
        System.out.println("ciao");
    }
    
    public static ArrayList<Testo> Scritte = new ArrayList<Testo>();
    public static ArrayList<Centro> centri = new ArrayList<Centro>();
    public static int px, py;
    public int background = color(0,0,0,200);
    public int pixelBeforeColor;
    public int temp1, temp2;
    public double distanceSquared, distance;
    public void shadowMap () {
        shadowmap.loadPixels();
                System.out.println(centri.size());
        
        shadowmap.pixels = blackPixels.clone();
                
                
        for (int k = 0; k < centri.size(); k++) {
            for (int i = centri.get(k).y - centri.get(k).radius; i < centri.get(k).y + centri.get(k).radius; i++) {
                for (int j = centri.get(k).x - centri.get(k).radius; j < centri.get(k).x + centri.get(k).radius; j++) {
                    distanceSquared = (((i - centri.get(k).y) * (i - centri.get(k).y)) + (j - centri.get(k).x) * (j - centri.get(k).x));
                    if (distanceSquared < centri.get(k).radius*centri.get(k).radius) {
                        distance = Math.sqrt(distanceSquared);
                        if ((int)((shadowmap.pixels[i * GAME_WIDTH + j] >> 24) & 0xFF) > (230*distance/centri.get(k).radius)) {
                            shadowmap.pixels[i * GAME_WIDTH + j] = 230*(int)distance/centri.get(k).radius << 24;
                        }
                    }
                }
            }
        }
        shadowmap.format = ARGB;
        shadowmap.updatePixels();
    }
    
    public void createBackground() {
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_WIDTH; j++) {
                py = GAME_WIDTH * i; px = j;
                
                blackPixels[py + px] = color(0,0,0,230);
            }
        }
    }
    
   public void keyPressed() {
        if (key == ' ') {
            lightning = !lightning;
        } else {
        }
      }
    
    
    
    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   // public void loadImages() {
//        gallina = loadImage("Drawables/brownchickenstanding.png");
////        gallina.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallina2 = loadImage("Drawables/brownchickenwalking.png");
////        gallina2.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallina3 = loadImage("Drawables/brownchickeneating.png");
////        gallina3.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallina4 = loadImage("Drawables/brownchickensitting.png");
////        gallina4.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallina5 = loadImage("Drawables/brownchickenegg.png");
//        gallina5.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        
//        gallinaGialla = loadImage("Drawables/yellowchickenstanding.png");
////        gallinaGialla.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGialla2 = loadImage("Drawables/yellowchickenwalking.png");
////        gallinaGialla2.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGialla3 = loadImage("Drawables/yellowchickeneating.png");
////        gallinaGialla3.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGialla4 = loadImage("Drawables/yellowchickensitting.png");
////        gallinaGialla4.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGialla5 = loadImage("Drawables/yellowchickenegg.png");
////        gallinaGialla5.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        
//        gallinaGrigia = loadImage("Drawables/graychickenstanding.png");
////        gallinaGrigia.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGrigia2 = loadImage("Drawables/graychickenwalking.png");
////        gallinaGrigia2.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGrigia3 = loadImage("Drawables/graychickeneating.png");
////        gallinaGrigia3.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGrigia4 = loadImage("Drawables/graychickensitting.png");
////        gallinaGrigia4.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaGrigia5 = loadImage("Drawables/graychickenegg.png");
////        gallinaGrigia5.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        
//        gallinaDerp = loadImage("Drawables/derpchickenstanding.png");
////        gallinaDerp.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaDerp2 = loadImage("Drawables/derpchickenwalking.png");
////        gallinaDerp2.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaDerp3 = loadImage("Drawables/derpchickeneating.png");
////        gallinaDerp3.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        gallinaDerp4 = loadImage("Drawables/derpchickensitting.png");
////        gallinaDerp4.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//
//        
//        fagiano = loadImage("Drawables/fagianostanding.png");
//        fagiano.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        fagiano2 = loadImage("Drawables/fagianowalking.png");
//        fagiano2.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//        
//        pixel = loadImage("Drawables/unpixelrosa.png");
//        pixel.resize(4, 4);
//        
//        cacca = loadImage("Drawables/cacca.png");
//        cacca.resize(CACCA_SIZE, CACCA_SIZE);
//        cacca2 = loadImage("Drawables/cacca2.png");
//        cacca2.resize(CACCA_SIZE, CACCA_SIZE);
//        cacca3 = loadImage("Drawables/cacca3.png");
//        cacca3.resize(CACCA_SIZE, CACCA_SIZE);
//        
//        sfondo = loadImage("Drawables/sfondo3_2.png");
//        sfondo.resize(GAME_WIDTH, GAME_WIDTH);
//        sfondoMenu = loadImage("Drawables/menubar.png");
//        sfondoMenu.resize(MENU_WIDTH, GAME_WIDTH);
//        bottoneSfondo = loadImage("Drawables/button.png");
//        bottoneSfondo.resize(BOTTONE_WIDTH, BOTTONE_HEIGHT);
//        bottoneSfondoPremuto = loadImage("Drawables/buttonpressed.png");
//        bottoneSfondoPremuto.resize(BOTTONE_WIDTH, BOTTONE_HEIGHT);
//        bottoneSfondoBloccato = loadImage("Drawables/buttonblocked.png");
//        bottoneSfondoBloccato.resize(BOTTONE_WIDTH, BOTTONE_HEIGHT);
//        
//        icocibo = loadImage("Drawables/cibo.png");
//        icocibo.resize(ICON_WIDTH, ICON_HEIGHT);
//        icogallina = loadImage("Drawables/moarchickens.png");
//        icogallina.resize(ICON_WIDTH, ICON_HEIGHT);
//        icopulisci = loadImage("Drawables/puliscicacca.png");
//        icopulisci.resize(ICON_WIDTH, ICON_HEIGHT);
//        icofagiano = loadImage("Drawables/fagianoico.png");
//        icofagiano.resize(ICON_WIDTH, ICON_HEIGHT);
//        icomoarclicks = loadImage("Drawables/moarclicks.png");
//        icomoarclicks.resize(ICON_WIDTH, ICON_HEIGHT);
//        icoincubatrice = loadImage("Drawables/incubatrice.png");
//        icoincubatrice.resize(ICON_WIDTH, ICON_HEIGHT);
//        icoraggruppagalline = loadImage("Drawables/raggruppagalline.png");
//        icoraggruppagalline.resize(ICON_WIDTH, ICON_HEIGHT);
//        icoterreno = loadImage("Drawables/moreterreno.png");
//        icoterreno.resize(ICON_WIDTH, ICON_HEIGHT);
//        
//        mirino = loadImage("Drawables/mirino.png");
//        mirino.resize(MIRINO_WIDTH, MIRINO_HEIGHT);
//        
//        clicken = loadImage("Drawables/chickenmergerbig.png");
//        
//        pacchetto = loadImage("Drawables/pacchetto.png");
//        pacchetto.resize(CHICKEN_SIZE, CHICKEN_SIZE);
//    }
    
//}
. */
package casa;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import processing.core.PApplet;
import static processing.core.PApplet.radians;
import static processing.core.PConstants.CENTER;
import static processing.core.PConstants.CORNER;
import processing.core.PImage;
import processing.core.PVector;

public class Main extends PApplet {
    
public static int [] consumiValori = {
    918,
    975,
    993,
    1054,
    1065,
    1154,
    1204,
    1247,
    1270,
    1316,
};

public static String [] consumiNomi = {
    "Campobasso",
    "Benevento",
    "Cosenza",
    "Frosinone",
    "Crotone",
    "Lodi",
    "Cremona",
    "Siena",
    "Pavia",
    "Mantova"
};
    
    
public static final int GAME_WIDTH = 700;

public static boolean lightning = false;

//roba sull'albero
public static int ALBERO_Y = 600;
public static int ALBERO_X = 0;
public static int STROKE = 20;
public static double time;
public static float crescita = 0;
public static float maxLevel = 1;

public static double MOUSE_AFFECTION_X = 4;
public static double MOUSE_AFFECTION_Y = 15;
public static double MOUSE_MAX_DISTANCE = 1;

public static double TEMPO_DI_CRESCITA = .2;
public static double START_TIME_CRESCITA = 0;

public static int LIGHT_RADIUS = 70;

public static int GROWTH_RATE = 40;


public static Nodo nodoinit = new Nodo(GAME_WIDTH/2, 600);

public static int [][] numRamiChePartonoDaQui = new int[12][1000];
public static int [] numRamiIndex = new int[12];
public static double [][] valoriRandom = new double[12][100];
public static int[] valoriRandomIndex = new int[12];

public static int[] blackPixels = new int[GAME_WIDTH*GAME_WIDTH];

public static int[] contaRami = new int[12];
public static int[] numRamiPerLivello = new int[12];

public PImage solonero;

public static PImage shadowmap, sfondo;


public ParticleSystem ps;

    public static void main(String args[]) {
        PApplet.main(new String[]{casa.Main.class.getName()});
    }
    
    
    public void setup() {
        size(GAME_WIDTH, GAME_WIDTH);
        background(100);
        frameRate(200);
        noSmooth();
        
        crescita = 0;
        maxLevel = 1;
        
        solonero = loadImage("casa/pappa2.png");
        
        START_TIME_CRESCITA = System.currentTimeMillis();
        
        randomizzaNumeroRamiDaQui();
        randomizzaNumeriDaQui();
        
        for (int i = 0; i < 12; i++) {
            valoriRandomIndex[i] = 0;
            numRamiIndex[i] = 0;
            numRamiPerLivello[i] = 0;
            contaRami[i] = 0;
        }
        
        
        contaRami(1);
        trovaLivello(10);
        
//        System.out.println(k);
//        aggiustaNumeroRami(k, 10);
        for (int i = 0; i < 12; i++) {
            valoriRandomIndex[i] = 0;
            numRamiIndex[i] = 0;
            contaRami[i] = 0;
        }
        //loadImages();
        ps = new ParticleSystem(new PVector(width/2,650));
        Roba.applet = this;
        sfondo = loadImage("casa/sfondoalbero2.png");
        //riempiAlbero(nodoinit);
        //fillTree();
        
//        startGame();
        
        createBackground();
        
        shadowmap = new PImage(GAME_WIDTH, GAME_WIDTH);
    }
    
    public void draw() {
        text("consumo: ", 0, 0);
        background(sfondo);
        time = Roba.applet.millis()/1000;
        
        if (lightning) {
            shadowMap();
        }
        
        
        stroke(255, 1);
        ps.addParticle();
        ps.run();
        
        noFill();
        stroke(255);
        strokeWeight(1);
        bezier(380 + mouseX/30, 540 +mouseY/30,  390 +mouseX/20, 540+mouseY/20,  420, 580,  300, 650);
        noStroke();
        
        fill(255);
        ellipse(380+mouseX/30, 540+mouseY/30, 10, 10);
        ellipse(375+mouseX/30, 550+mouseY/30, 7, 7);
        ellipse(383+mouseX/30, 549+mouseY/30, 4, 4);
        //rect(0, 600, 700, 200);
        //filter(BLUR, 1);
        //ellipse(350,350,400,400);  
        
        cresci();
        smooth();
        
        for (int i = 0; i < 12; i++) {
            valoriRandomIndex[i] = 0;
            numRamiIndex[i] = 0;
            contaRami[i] = 0;
        }
        
        
        Scritte.clear();
        
        jumping = 0; jumping7 = 0; jumping8 = 0; jumping9 = 0;
        centri.clear();
        centri.add(new Centro(350, 570, 100));
        drawTree(350, 600, 120, radians(90), 10, 1);
        if (lightning) {
            image(shadowmap, 0, 0);
        }
        
        for (int i = 0; i < Scritte.size(); i++) {
            Scritte.get(i).draw(this);
        }
        
        image(solonero, 0, 0);
    }        
        
    public void drawTree (double x0, double y0, double len, double angle, int big, int currentLevel) {
        if(currentLevel <= maxLevel) {
            
               if (currentLevel == maxLevel) {
                   len *= crescita;
               }
               double x1 = x0 + len * Math.cos(angle);
               double y1 = y0 - len * Math.sin(angle);
               
               if (currentLevel == 10){
                   if (numRamiIndex[currentLevel]==1){
                       textSize(10);
                      // Scritte.add(new Testo("• User", (int) x0, (int) y0));
                      // Scritte.add(new Testo("consumo: " + (int)(x0+y0), (int) x0, (int) y0 + 12));
                   }
               }
                    
               x1 = muoviConMouseX(x1, (mouseX - x1));
               y1 = muoviConMouseY(y1, (mouseY - y1));
               
               
               aggiungiCentro(x1, y1, currentLevel);
               
               stroke(255, 255/((float)currentLevel / 2) + 30);
               
               if (big <= 0) big = 1;
               strokeWeight(big);
               
               numRamiIndex[currentLevel]++;
               if (numRamiIndex[currentLevel] >= 99) {
                   numRamiIndex[currentLevel] = 0;
               }
  
               Roba.applet.line((int)x0, (int)y0, (int)x1, (int)y1);
                /*if (Math.sqrt(Math.pow((mouseY-y0),2)+Math.pow((mouseX-x0),2))>100) {
                    filter(BLUR);
                }*/
            
               
               for (int i = 0; i < numRamiChePartonoDaQui[currentLevel][numRamiIndex[currentLevel]]; i++) {
//                   System.out.println(numRamiChePartonoDaQui[currentLevel][numRamiIndex[currentLevel]]);
                   valoriRandomIndex[currentLevel]++;
                   if (valoriRandomIndex[currentLevel] == 99) {valoriRandomIndex[currentLevel] = 0;}
                   drawTree(x1, y1, len * 0.75, angle + radians(90) * valoriRandom[currentLevel][valoriRandomIndex[currentLevel]], big-2, currentLevel + 1);
               }
               
        }
    }
    
    public static int jumping = 0, jumping7 = 0, jumping8 = 0, jumping9 = 0;
    public static int radius = 0;
    public static void aggiungiCentro (double x1, double y1, int currentLevel) {
        
        switch (currentLevel) {
            case 1: radius = 100;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 2: radius = 90;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 3: radius = 75;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 4: radius = 60;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 5: radius = 50;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 6: radius = 35;
                centri.add(new Centro((int)x1, (int)y1, radius));
                break;
            case 7: radius = 25;
                if (jumping7 >= 4) {
                    centri.add(new Centro((int)x1, (int)y1, radius)); jumping7 = 0;
                }
                else {
                    jumping7++;
                }
            
                break;
            case 8: radius = 12;
                if (jumping8 >= 6) {
                    centri.add(new Centro((int)x1, (int)y1, radius)); jumping8 = 0;
                }
                else {
                    jumping8++;
                }
                break;
            case 9: radius = 4;
                if (jumping9 >= 10) {
                    centri.add(new Centro((int)x1, (int)y1, radius)); jumping9 = 0;
                }
                else {
                    jumping9++;
                }
                break;
            default: radius = 0;
                break;
        }
    }
    
    public static void cresci() {
        if (maxLevel < 11) {
            crescita = (float) ((System.currentTimeMillis() - START_TIME_CRESCITA) /1000 / TEMPO_DI_CRESCITA);
            if (crescita >= 1) {
                aumentaLivelloMax();
            }
        }
    }
    
    public static void aumentaLivelloMax() {
            maxLevel += 1;
            crescita = 0;
            START_TIME_CRESCITA = System.currentTimeMillis();
    }
    
    
    
    
    
    
    public static double muoviConMouseX(double x, double distanceX) {
        if (distanceX > 0) {
                   x = x + (Math.sqrt(distanceX)) / MOUSE_AFFECTION_X;
               }
               else {
                   x = x + (-Math.sqrt(Math.abs(distanceX))) / MOUSE_AFFECTION_X;
               }
        return x;
    }
    
    public static double muoviConMouseY(double y, double distanceY) {
        if (distanceY > 0) {
                   y = y + (Math.sqrt(distanceY)) / MOUSE_AFFECTION_Y;
               }
               else {
                   y = y + (-Math.sqrt(Math.abs(distanceY))) / MOUSE_AFFECTION_Y;
               }
        return y;
      }
    
    
    public void randomizzaNumeroRamiDaQui () {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < Math.pow(2, i) + 1; j++) {
                numRamiChePartonoDaQui[i][j] = (int)(Math.random()*3) +1;
            }
        }
    } 
    
    public void randomizzaNumeriDaQui () {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 100; j++) {
                valoriRandom[i][j] = (Math.random() - .5);
                
                if (j != 0) {
                while (Math.abs(valoriRandom[i][j] - valoriRandom[i][j-1]) < 0.3) {
                    valoriRandom[i][j] = (Math.random() - .5);
                }
                }
            }
        }
    }
    
    public void trovaLivello(int numRami) {
        int rami = 0;
        int livelloDesiderato = 0;
        
        for (int i = 1; i < 10; i++) {
            if (numRamiPerLivello[i] >= numRami) {
                aggiustaNumeroRami(i, numRami);
                System.out.println(i);
                break;
            }
        }
    }
    
    public static void contaRami (int currentLevel) {
        
        if (currentLevel >= 10) {return;}
        
        numRamiPerLivello[currentLevel]++;
        
        
        numRamiIndex[currentLevel]++;
               if (numRamiIndex[currentLevel] >= 99) {
                   numRamiIndex[currentLevel] = 0;
               }
               
        if (currentLevel == 1) {
            System.out.println("Level 1 : " + numRamiChePartonoDaQui[currentLevel][numRamiIndex[currentLevel]]);
        }
        
        for (int i = 0; i < numRamiChePartonoDaQui[currentLevel][numRamiIndex[currentLevel]]; i++) {
                   contaRami(currentLevel + 1);
        }
        
        
    }
    
    public void aggiustaNumeroRami (int livello, int numRami) {
        int rami = 0;
        
        System.out.println("Il livello da operare e'" + (livello));
        
        //conta il numero dei rami
        rami = numRamiPerLivello[livello];
        System.out.print("Il numero di rami su quel livello e' " + rami);
        
        while (rami > numRami) {
        
        for (int i = 0; i < Math.pow(2, livello - 1); i++) {
            if (numRamiChePartonoDaQui[livello - 1][i] > 1 && rami > numRami) {
                numRamiChePartonoDaQui[livello - 1][i] -= 1;
                rami--;
            }
            
        }
        
        
        }
        
        System.out.println("ciao");
    }
    
    public static ArrayList<Testo> Scritte = new ArrayList<Testo>();
    public static ArrayList<Centro> centri = new ArrayList<Centro>();
    public static int px, py;
    public int background = color(0,0,0,200);
    public int pixelBeforeColor;
    public int temp1, temp2;
    public double distanceSquared, distance;
    public void shadowMap () {
        shadowmap.loadPixels();
                System.out.println(centri.size());
        
        shadowmap.pixels = blackPixels.clone();
                
                
        for (int k = 0; k < centri.size(); k++) {
            for (int i = centri.get(k).y - centri.get(k).radius; i < centri.get(k).y + centri.get(k).radius; i++) {
                for (int j = centri.get(k).x - centri.get(k).radius; j < centri.get(k).x + centri.get(k).radius; j++) {
                    distanceSquared = (((i - centri.get(k).y) * (i - centri.get(k).y)) + (j - centri.get(k).x) * (j - centri.get(k).x));
                    if (distanceSquared < centri.get(k).radius*centri.get(k).radius) {
                        distance = Math.sqrt(distanceSquared);
                        if ((int)((shadowmap.pixels[i * GAME_WIDTH + j] >> 24) & 0xFF) > (230*distance/centri.get(k).radius)) {
                            shadowmap.pixels[i * GAME_WIDTH + j] = 230*(int)distance/centri.get(k).radius << 24;
                        }
                    }
                }
            }
        }
        shadowmap.format = ARGB;
        shadowmap.updatePixels();
    }
    
    public void createBackground() {
        for (int i = 0; i < GAME_WIDTH; i++) {
            for (int j = 0; j < GAME_WIDTH; j++) {
                py = GAME_WIDTH * i; px = j;
                
                blackPixels[py + px] = color(0,0,0,230);
            }
        }
    }
    
   public void keyPressed() {
        if (key == ' ') {
            lightning = !lightning;
        } else if (key == 'r')
                {
                    setup();
        }
      }
    
    
    
    
    
    
    
    
    
    }
    
