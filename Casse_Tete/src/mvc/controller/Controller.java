/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Set;
import mvc.model.Case;
import mvc.model.Chemin;
import mvc.model.enumeration.Symboles;


/**
 *
 * @author fred
 */
public class Controller extends Observable {
    Hashtable<Symboles,Chemin> pathList;
    int lastC, lastR;
    Symboles lastSymb;

    public Controller() {
        this.pathList = new Hashtable<Symboles,Chemin>();
        lastSymb = Symboles.VIDE;
    }
    
    public void startDD(int c, int r, Case cell) {
        ListIterator<Chemin> it;
        boolean registered;
        
        System.out.println("startDD : " + r + "-" + c + " Symbole : " + cell.getSymbole());
        //On test si le symbole de la case en paramètre n'est pas déjà dans un chemin
        registered = false;
        Set keys = pathList.keySet();
        Iterator itr = keys.iterator();
        while((itr.hasNext())&&(!registered)){
            if((Symboles)itr.next()==cell.getSymbole()){
                registered = true;
            }
        }
        
        if(!registered){
            Chemin ch = new Chemin();
            ch.add(cell);
            pathList.put(cell.getSymbole(), ch);
            lastSymb=cell.getSymbole();
            System.out.println("Key : "+cell.getSymbole());
        }
        
        setChanged();
        notifyObservers();
    }
    
    public void stopDD(int c, int r) {
        Chemin chemin;
        Case lastCase;
        // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
        System.out.println("stopDD : " + r + "-" + c + " -> " + lastR + "-" + lastC);
        
        //si l'on s'arrête sur une case vide, de mauvais symbole, ou sur la case d'origine : on supprime le chemin
        chemin = pathList.get(lastSymb);
        lastCase = chemin.get(chemin.size()-1);
        if((lastCase.getSymbole()==Symboles.VIDE)||(lastCase.getSymbole()!=lastSymb)||(lastCase==chemin.get(0))){
            pathList.remove(lastSymb);
            System.out.println("Erase");
        }
        
        //******************Test : affichage de toutes les cases de tout les chemins********************
        Set keys = pathList.keySet();
        Iterator itr = keys.iterator();
        while((itr.hasNext())){
            Symboles symb = (Symboles) itr.next();
            Chemin ch = pathList.get(symb);
            System.out.println("Chemin "+symb+" : ");
            Iterator itr2 = ch.iterator();
            while (itr2.hasNext()) {
                System.out.println(itr2.next());
            }
        }
        
        setChanged();
        notifyObservers();
    }
    
    public void parcoursDD(int c, int r, Case cell) {
        // TODO
        lastC = c;
        lastR = r;
        System.out.println("parcoursDD : " + r + "-" + c + " Symbole : " + cell.getSymbole());
        setChanged();
        notifyObservers();
    }
   

}