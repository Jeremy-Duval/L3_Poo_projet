/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.ArrayList;
import java.util.List;
import java.lang.IllegalArgumentException;

/**
 * Chemin pour connecter une paire de symbole.
 * @author Jeremy
 */
public class Chemin extends ArrayList{
    protected List <Case> path;
    
    /**
     * Constructeur par défaut.
     */
    public Chemin(){
        path = new ArrayList<Case>();
    }
    
    /**
     * Ajoute une case en fin de chemin.
     * @param e Case : object de type Case à ajouter au chemin
     * @return boolean : true (comme pour une ArrayList)
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est pas de type Case
     */
    @Override
    public boolean add(Object e) throws IllegalArgumentException {
      if(e instanceof Case){
          Case c = (Case) e;
          return super.add(c);
      } else {
          throw new IllegalArgumentException();
      }
    }
    
    /**
     * Ajoute une case à l'index spécifié du chemin.
     * @param i int : index de l'emplacement où ajouter le paramètre e
     * @param e Case : object de type Case à ajouter au chemin
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est pas de type Case
     */
    @Override
    public void add(int i, Object e) throws IllegalArgumentException {
      if(e instanceof Case){
          Case c = (Case) e;
          super.add(i,c);
      } else {
          throw new IllegalArgumentException();
      }
    }
    
    /**
     * Supprime la première occurence de la case en paramètre.
     * @param e Case : object de type Case à ajouter au chemin
     * @return boolean : true si l'objet était contenue dans la liste
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est pas de type Case
     */
    @Override
    public boolean remove(Object e) throws IllegalArgumentException {
      if(e instanceof Case){
          Case c = (Case) e;
          return super.remove(c);
      } else {
          throw new IllegalArgumentException();
      }
    }
    
    /**
     * Supprime la première occurence de la case en paramètre.
     * @param e Case : object de type Case à ajouter au chemin
     * @return boolean : true si l'objet était contenue dans la liste
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est pas de type Case
     */
    @Override
    public Case remove(int i) throws IllegalArgumentException {
      return (Case) super.remove(i);
    }
    
    /* TODO :
        - Override :
            - isEmpty
            - Clear
            - Get
            (comme ci-dessus)
            - set
    */
    
}
