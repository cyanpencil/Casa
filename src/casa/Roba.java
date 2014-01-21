package casa;

import java.util.ArrayList;
import processing.core.PApplet;

public class Roba {
    
//VARIABILI CHE SERVONO
public static PApplet applet;


//cose che servono al motore
public static int tick = 16;
public static double t_tick = tick;
public static boolean playing = true;
public static int system_time = 0;

//Roba che serve sul serio
public static ArrayList<Lampadina> lampadine = new ArrayList<Lampadina>();
public static ArrayList<Nodo> nodi = new ArrayList<Nodo>();












    /** Motore logico principale del gioco. Per fermarlo basta mettere "playing" su false. */
    public static Runnable Engine = new Runnable() {
        public void run() {
            synchronized(this) {
               long t = System.currentTimeMillis(); //qui
               long t_sleep;
               while (playing) {
                   try {
                        //tempo del gioco
                        system_time += tick;
                        //motore
                        tick();
                        //loop
                        t += t_tick;
                        t_sleep = t-System.currentTimeMillis(); //qui
                        if (t_sleep > 0) wait(t_sleep);
                   }catch(InterruptedException e ){}
               }
            }
    }};
    
    /** E' il metodo che viene chiamato dall'eng 60 volte al secondo e che deve chiamare tutti gli altri. */
    public static void tick() {
        
    }




    
}
