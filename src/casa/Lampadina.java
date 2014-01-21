package casa;

import processing.core.PApplet;
import processing.core.PImage;

public class Lampadina {


public float x, y, width, height, speedX, speedY, startX, startY, distanceX, distanceY, centerX, centerY, angle = 0, circleSpeed, pointRadius;
public double whenSpawned, delay;
public PImage sprite;
public int quantoMuovi = 0;
double randomTokenX = 0;
double randomTokenY = 0;
public int animationState = 0;
public int scaleFactor = 1;

public boolean delaying = false;

String descrizione = "Brown chicken";


    public Lampadina(float x, float y) {
        this(x, y, 0);
    }
    
    public Lampadina(float x, float y, double delay) {
        this.x = x;
        this.y = y;
        this.whenSpawned = Roba.system_time;
        
        
        aggiungiAgliArray();
        
    }
    
    public Lampadina(float x, float y, float width, float height) {
        
    }
    
    public void draw(PApplet grafica) {
        
    }
    
    public void update() {
        //controllo se il mouse e' sopra la robetta
        checkForMouse();
    }
    
    
    
    public void aggiungiAgliArray() {
//        Roba.animali.add(this);
    }
    
    
    public boolean checkForMouse() {
        if (Roba.applet.mouseX > this.x - this.width/2 && Roba.applet.mouseX < this.x + this.width/2
                && Roba.applet.mouseY > this.y - this.height/2 && Roba.applet.mouseY < this.y + this.height/2) {
            //se il mouse e' qui sopra
        }
        return false;
    }
    
    public void die() {
//        Roba.animali.remove(this);
    }
    
    public void setAnimationState(int i) {
        this.animationState = i;
    }

    
    
    
}
