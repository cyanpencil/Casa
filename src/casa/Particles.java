/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package casa;

import processing.core.PVector;

/**
 *
 * @author Sara
 */
public class Particles {
      PVector location;
  PVector velocity;
  PVector acceleration;
  float lifespan;
  
public double startTime = 0;
  
public int startX = 0, startY = 300;
public double initialSpeedX = 0, accelerationX = 0;
public double initialSpeedY = 0, accelerationY = 0;
public int getAwayX = 600, getAwayY = 0;
public int timeToGetAway = 4000;
public double pulsation = .001;
public int ray = 70;

  Particles(PVector l) {
    acceleration = new PVector((float) (Roba.applet.random(-1,1)*0.006), (float) -0.005);
    velocity = new PVector((float) (Roba.applet.random(0,0)*0.001),Roba.applet.random(0,0));
    startTime =  System.currentTimeMillis();
    
    location = l.get();
    lifespan = (float) 9800.0;
    
    
    startX = (int) (Main.GAME_WIDTH/2 + (Math.random() - .5) * 50);
    startY = 650;
    
    getAwayX = (int) (Math.random() * Main.GAME_WIDTH);
    
    initialSpeedY = .033;
    
    initialSpeedX = (2 * (getAwayX - l.x) / (timeToGetAway));
    accelerationX = - initialSpeedX / timeToGetAway;
    
    pulsation += (Math.random() - .5) * .0004;
    ray += (Math.random() - .5) * 50;
  }

  void run() {
    update();
    display();
}
    // Method to update location
  double time;
  void update() {
      
      time = System.currentTimeMillis() - startTime;
      
      if (time < timeToGetAway) {
          double x = (startX + initialSpeedX * time + accelerationX * time * time / 2);
          location.set(new PVector((float)x, (float) (startY - initialSpeedY*time)));
      }
      else {
          //zag, zig
          double x = getAwayX + ray * Math.sin(pulsation*time);
          location.set(new PVector((float)x, (float) (startY - initialSpeedY*time)));
          lifespan = (float) (800 - .045 * time);
      }
  }

  // Method to display
  void display() {
      int randomOpacity = (int) (Roba.applet.random(-20, 20) + lifespan);
    //Roba.applet.stroke(255, 40 + randomOpacity);
    Roba.applet.fill(255, 20 + randomOpacity);
    Roba.applet.ellipse(location.x,location.y, Roba.applet.random(0,3),Roba.applet.random(0,3));
  }
  
  // Is the particle still useful?
  boolean isDead() {
    if (lifespan < 0.0) {
      return true;
    } else {
      return false;
    }
  }
}
