/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casa;

import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
/**
 *
 * @author Sara
 */
public class Nodo {
    


    
    
    
    
public Nodo(int x, int y){
    this.x = x;
    this.y = y;
}

public Nodo(int x, int y, int livello) {
    
}

int x, y;
int livello;

public ArrayList<Nodo> nodiFigli = new ArrayList<Nodo>();
    
    
    public void addFiglio() {
        //da mettere l'algoritmo della posizione del nuovo nodo
        Nodo figlio = new Nodo(0, 0, this.livello + 1);
        addFiglio(figlio);
    }

    public void addFiglio(Nodo figlio){
        nodiFigli.add(figlio);
        Roba.nodi.add(figlio);
    }
    
    public void draw(){
        if (nodiFigli.size()==0) return;
        for (int i=0; i<nodiFigli.size(); i++){
            Roba.applet.stroke(255);
            Roba.applet.line(this.x, this.y, nodiFigli.get(i).x, nodiFigli.get(i).y);
            nodiFigli.get(i).draw();
        }
    }
    
    
    
}
