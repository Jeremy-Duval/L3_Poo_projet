/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.Model;

import mvc.Model.Enumerations.Liens;
import mvc.Model.Enumerations.Symboles;

/**
 * Case de la grille.
 * @author Jeremy
 */
public class Case {
    final protected int ligne;
    final protected int colonne;
    final Symboles symbole;
    Liens lien;
    
    /**
     * Constructeur de base.
     * @param _ligne int : n� de ligne de la case
     * @param _colonne int : n� de ligne de la case
     */
    public Case (int _ligne, int _colonne){
        ligne = _ligne;
        colonne = _colonne;
        symbole = Symboles.VIDE;
        lien = Liens.VIDE;
    }
    
    /**
     * Constructeur avec symbole (pour les cases � relier)
     * @param _ligne int : n� de ligne de la case
     * @param _colonne int : n� de ligne de la case
     * @param _symbole SYMBOLES (enumeration) : Type de symbole de la case
     */
    public Case (int _ligne, int _colonne, Symboles _symbole){
        ligne = _ligne;
        colonne = _colonne;
        symbole = _symbole;
        lien = Liens.VIDE;
    }
    
    /**
     * 
     * @return int : n� de ligne de la case
     */
    public int getLigne(){
        return ligne;
    }

    /**
     * 
     * @return int : n� de colonne de la case
     */
    public int getColonne() {
        return colonne;
    }
    
    /**
     * 
     * @return SYMBOLES (enumeration) : le type de symbole de la case
     */
    public Symboles getSymbole() {
        return symbole;
    }

    /**
     * 
     * @return LIENS (enumeration) : le type de lien de la case
     */
    public Liens getLien() {
        return lien;
    }

    /**
     * 
     * @param lien LIENS (enumeration) : le type de lien de la case
     */
    public void setLien(Liens lien) {
        this.lien = lien;
    }
    
    
    
}
