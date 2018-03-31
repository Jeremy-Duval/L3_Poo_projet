/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import mvc.model.enumeration.Symboles;

/**
 *
 * @author p1503252
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

    public void setContenu(Case[][] _contenu) {
        this._contenu = _contenu;
    }
    
    
    public boolean isFull(){
        boolean isFull = true;
        for(int i=0;i<_hauteur;i++){
            for(int j=0;j<_largeur;j++){
                if(_contenu[i][j].getSymbole() == Symboles.VIDE ){
                    isFull = false;
                }
            }
        }
        return isFull;
    }
}
