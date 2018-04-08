/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.Iterator;
import mvc.model.enumeration.Liens;
import mvc.model.enumeration.Symboles;

/**
 *
 * @author Alexis, modifiée et debuguer par Jérémy
 */
public class Grille {
    private final int _hauteur;
    private final int _largeur;
    private Case[][] _contenu;
    
    /**
     * Constructeur de Grille
     * @param hauteur hauteur(verticale) de la grille en nombre de cases
     * @param largeur largeur (horizontale) de la grille en nombre de cases
     */
    public Grille(int hauteur, int largeur){
        _hauteur = hauteur;
        _largeur = largeur;
        _contenu = new Case[_hauteur][_largeur];
        for(int i=0;i<_hauteur;i++){
            for(int j=0;j<_largeur;j++){
                _contenu[i][j] = new Case(i,j);
            }
        }
    }

    /**
     * @return int : hauteur de la grille en nombre de cases 
     */
    public int getHauteur() {
        return _hauteur;
    }

    /**
     * @return int : largeur de la grille en nombre de cases 
     */
    public int getLargeur() {
        return _largeur;
    }

    /**
     * @return Case[][] Contenu
     */
    public Case[][] getContenu() {
        return _contenu;
    }
    
    /**
     * Retourne une case de la grille
     * @param ligne int : ligne sur laquelle se trouve la case
     * @param colonne int : colonne sur laquelle se trouve la case
     * @return Case : la case situé à l'emplacement donné
     */
    public Case getCase(int ligne, int colonne){
        return _contenu[ligne][colonne];
    }
    
    /**
     * 
     * @param _contenu Case[][] : nouvelle grille
     */
    public void setContenu(Case[][] _contenu) {
        this._contenu = _contenu;
    }
    
    /**
     * Détermine si la grille est pleine.
     * @return boolean : true si la grille est pleine, false sinon
     */
    public boolean isFull(){
        int column, row;
        boolean isFull;
        
        isFull = true;
        column = 0;
        while((column < _largeur)&&(isFull)){
            row = 0;
            while((row < _hauteur)&&(isFull)){
                //si la case n'a pas de symbole et de lien, c'est que la grille n'est pas vide
                if((_contenu[row][column].getSymbole()==Symboles.VIDE)&&(_contenu[row][column].getLien()==Liens.VIDE)){
                    isFull=false;
                }
                row++;
            }
            column++;
        }
        return isFull;
    }
    
    /**
     * Reinitialise la grille à vide (efface les Symboles et les Liens).
     */
    public void setGrilleVide(){
        int column, row;
        
        column = 0;
        while(column < _largeur){
            row = 0;
            while(row < _hauteur){
                _contenu[row][column].setLien(Liens.VIDE);
                _contenu[row][column].setSymbole(Symboles.VIDE);
                row++;
            }
            column++;
        }
    }
}
