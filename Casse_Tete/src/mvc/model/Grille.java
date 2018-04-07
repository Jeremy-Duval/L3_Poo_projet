/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import mvc.model.enumeration.Symboles;

/**
 *
 * @author Alexis
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
        _contenu[0][0].setSymbole(Symboles.COW);
        _contenu[4][2].setSymbole(Symboles.COW);
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
     * @author Jérémy
     */
    public Case getCase(int ligne, int colonne){
        return _contenu[ligne][colonne];
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
