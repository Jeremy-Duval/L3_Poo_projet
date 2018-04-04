/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.Observable;


/**
 *
 * @author fred
 */
public class Controller extends Observable {
    
    int lastC, lastR;
    
    public void startDD(int c, int r) {
        // TODO
        System.out.println("startDD : " + r + "-" + c);
        setChanged();
        notifyObservers();
    }
    
    public void stopDD(int c, int r) {
        // TODO
        
        // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
        
        System.out.println("stopDD : " + r + "-" + c + " -> " + lastC + "-" + lastR);
        setChanged();
        notifyObservers();
    }
    
    public void parcoursDD(int c, int r) {
        // TODO
        lastC = c;
        lastR = r;
        System.out.println("parcoursDD : " + r + "-" + c);
        setChanged();
        notifyObservers();
    }
   

}