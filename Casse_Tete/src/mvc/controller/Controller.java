/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Observable;
import java.util.Set;
import mvc.model.Case;
import mvc.model.Chemin;
import mvc.model.Grille;
import mvc.model.enumeration.Liens;
import mvc.model.enumeration.Symboles;


/**
 *
 * @author fred (base), modifié par Jérémy
 */
public class Controller extends Observable {
    private Hashtable<Symboles,Chemin> pathList;
    private int lastC, lastR;
    private Symboles lastSymb;
    private int lastLevel;

    /**
     * Constructeur.
     */
    public Controller() {
        this.pathList = new Hashtable<Symboles,Chemin>();
        lastSymb = Symboles.VIDE;
        lastLevel = 0;
    }
    
    /**
     * Initialise le chemin et notify du début du drag an drop.
     * @param c int : colonne de la case
     * @param r int : ligne de la case
     * @param cell Case : case de départ
     */
    public void startDD(int c, int r, Case cell) {
        ListIterator<Chemin> it;
        boolean registered;
        
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
            lastC = c;
            lastR = r;
        }
        
        setChanged();
        notifyObservers(false);
    }
    
    /**
     * Supprime le chemin si celui ci est incorect et notify de la fin du drag an drop.
     * @param c int : colonne de la case
     * @param r int : ligne de la case
     */
    public void stopDD(int c, int r) {
        Chemin chemin;
        Case lastCell;
        // mémoriser le dernier objet renvoyé par parcoursDD pour connaitre la case de relachement
        
        //si l'on s'arrête sur une case vide, de mauvais symbole, ou sur la case d'origine : on supprime le chemin
        chemin = pathList.get(lastSymb);
        lastCell = chemin.get(chemin.size()-1);
        if((lastCell.getSymbole()==Symboles.VIDE)||(lastCell.getSymbole()!=lastSymb)||(lastCell==chemin.get(0))){
            this.reInitPathLink(chemin);
            pathList.remove(lastSymb);
        }
        
        setChanged();
        notifyObservers(true);
    }
    
    /**
     * Actualise le chemin et notify du changement dans le drag an drop.
     * @param c int : colonne de la case
     * @param r int : ligne de la case
     * @param cell Case : case courante
     */
    public void parcoursDD(int c, int r, Case cell) {
        Chemin chemin;
        Case lastCell;
        int absDiffCol, absDiffLig;
        
        chemin = pathList.get(lastSymb);
        lastCell = chemin.get(chemin.size()-1);
        /*on vérifie que :
            - la case n'a pas de symbole ou son symbole == lastSymb
            - la précédente n'a pas de symbole (pour ne pas traverser un case == lastSymb) sauf si c'est la premiere
        */
        if(((cell.getSymbole()==Symboles.VIDE)||(cell.getSymbole()==lastSymb))&&((lastCell.getSymbole()==Symboles.VIDE)||(chemin.size()<=1)))
        {
            //on vérifie que la case touche précédente (une seule coordonnée ++ ou --)
            absDiffCol = Math.abs(cell.getColonne()-lastCell.getColonne());
            absDiffLig = Math.abs(cell.getLigne()-lastCell.getLigne());
            if(((absDiffCol==1)&&(absDiffLig==0))||((absDiffCol==0)&&(absDiffLig==1))){
                //on vérifie que la case n'est pas déjà dans un autre chemin (et donc n'a pas déjà un lien)
                if(cell.getLien()==Liens.VIDE){
                    //on met a jour le symbole de la case précédente si il doit se changer en angle
                    if(lastCell.getLien()==Liens.HORIZONTAL){
                        if(cell.getLigne()-lastCell.getLigne()==1){
                            if(chemin.get(chemin.size()-2).getColonne()-lastCell.getColonne()==1){
                                lastCell.setLien(Liens.ANGLE_INF_DROIT);
                            } else {
                                lastCell.setLien(Liens.ANGLE_INF_GAUCHE);
                            }
                        } else if(cell.getLigne()-lastCell.getLigne()==-1){
                            if(chemin.get(chemin.size()-2).getColonne()-lastCell.getColonne()==1){
                                lastCell.setLien(Liens.ANGLE_SUP_DROIT);
                            } else {
                                lastCell.setLien(Liens.ANGLE_SUP_GAUCHE);
                            }
                        }
                        
                    } else if(lastCell.getLien()==Liens.VERTICAL){
                        if(cell.getColonne()-lastCell.getColonne()==1){
                            if(chemin.get(chemin.size()-2).getLigne()-lastCell.getLigne()==1){
                                lastCell.setLien(Liens.ANGLE_INF_DROIT);
                            } else {
                                lastCell.setLien(Liens.ANGLE_SUP_DROIT);
                            }
                        } else if(cell.getColonne()-lastCell.getColonne()==-1){
                            if(chemin.get(chemin.size()-2).getLigne()-lastCell.getLigne()==1){
                                lastCell.setLien(Liens.ANGLE_INF_GAUCHE);
                            } else {
                                lastCell.setLien(Liens.ANGLE_SUP_GAUCHE);
                            }
                        }
                        
                    }
                    
                    if(absDiffCol==1){
                        cell.setLien(Liens.HORIZONTAL);
                    } else {
                        cell.setLien(Liens.VERTICAL);
                    }
                    chemin.add(cell);
                    
                    lastC = c;
                    lastR = r;
                }
            }
        }
        
        setChanged();
        notifyObservers(false);
    }
   
    /**
     * Reinitialise les liens des case du chemin à VIDE
     * @param path Chemin : chemin pour lequel réinitialiser le lien des cases à VIDE.
     * @author Jérémy
     */
    private void reInitPathLink(Chemin path){
        Iterator itr = path.iterator();
        while (itr.hasNext()) {
            Case cell = (Case) itr.next();
            cell.setLien(Liens.VIDE);
        }
    }
    
    public boolean victory(Grille grid, int largeurGrille, int longueurGrille){
        boolean gameWin;
        int column, row, nbSymboles;
        
        nbSymboles = 0;
        gameWin = true;
        column = 0;
        //On vérifie que la grille est pleine
        while((column < largeurGrille)&&(gameWin)){
            row = 0;
            while((row < longueurGrille)&&(gameWin)){
                //si la case n'a pas de symbole et de lien, c'est que la grille n'est pas vide
                if((grid.getCase(row, column).getSymbole()==Symboles.VIDE)&&(grid.getCase(row, column).getLien()==Liens.VIDE)){
                    gameWin=false;
                } else {
                    //On compte le nombre de symboles
                    if(grid.getCase(row, column).getSymbole()!=Symboles.VIDE){
                        nbSymboles++;
                    }
                }
                row++;
            }
            column++;
        }
        //on vérifie qu'il y a autant de chemins que de paires de symboles
        if(gameWin){
            if(pathList.size()!=nbSymboles/2){
                gameWin=false;
            }
        }
        
        return gameWin;
    }
    
    public int nextLevel(Grille grid){
        grid.setGrilleVide();
        pathList.clear();
        
        switch(this.lastLevel){
            case 1:
                grid.getCase(0, 0).setSymbole(Symboles.COW);
                grid.getCase(4, 2).setSymbole(Symboles.COW);
                grid.getCase(0, 4).setSymbole(Symboles.LEAF);
                grid.getCase(4, 3).setSymbole(Symboles.LEAF);
                break;
            case 2:
                grid.getCase(0, 0).setSymbole(Symboles.COW);
                grid.getCase(4, 0).setSymbole(Symboles.COW);
                grid.getCase(1, 0).setSymbole(Symboles.CAT);
                grid.getCase(3, 3).setSymbole(Symboles.CAT);
                break;
            case 3:
                grid.getCase(4, 0).setSymbole(Symboles.COW);
                grid.getCase(2, 2).setSymbole(Symboles.COW);
                grid.getCase(4, 1).setSymbole(Symboles.CAT);
                grid.getCase(3, 3).setSymbole(Symboles.CAT);
                break;
            case 4:
                grid.getCase(0, 0).setSymbole(Symboles.COW);
                grid.getCase(3, 2).setSymbole(Symboles.COW);
                grid.getCase(2, 2).setSymbole(Symboles.LEAF);
                grid.getCase(3, 3).setSymbole(Symboles.LEAF);
                grid.getCase(1, 0).setSymbole(Symboles.PINE);
                grid.getCase(4, 0).setSymbole(Symboles.PINE);
                break;
            case 5:
                grid.getCase(0, 0).setSymbole(Symboles.COW);
                grid.getCase(0, 4).setSymbole(Symboles.COW);
                grid.getCase(4, 0).setSymbole(Symboles.LEAF);
                grid.getCase(2, 3).setSymbole(Symboles.LEAF);
                grid.getCase(2, 2).setSymbole(Symboles.PINE);
                grid.getCase(3, 3).setSymbole(Symboles.PINE);
                grid.getCase(4, 1).setSymbole(Symboles.CAT);
                grid.getCase(1, 4).setSymbole(Symboles.CAT);
                break;
            default:
                grid.getCase(0, 0).setSymbole(Symboles.COW);
                grid.getCase(4, 2).setSymbole(Symboles.COW);
                break;
        }
        
        this.lastLevel++;
        if(this.lastLevel>5){
            this.lastLevel=0;
        }
        
        return this.lastLevel;
    }
    
    public void actualize(){
        setChanged();
        notifyObservers(false);
    }

}