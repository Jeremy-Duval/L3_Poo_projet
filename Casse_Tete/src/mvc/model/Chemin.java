/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Chemin pour connecter une paire de symbole.
 * H�rite de l'objet ArrayList.
 * @author Jeremy
 */
public class Chemin extends ArrayList{
    protected List <Case> path;
    
    /**
     * Constructeur par d�faut.
     */
    public Chemin(){
        super();
    }
    
    /**
     * Ajoute une case en fin de chemin.
     * @param e Case : object de type Case � ajouter au chemin
     * @return boolean : true (comme pour une ArrayList)
     * @throws IllegalArgumentException Exception soulev� si le param�tre n'est pas de type Case
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
     * Ajoute une case � l'index sp�cifi� de ce chemin.
     * @param i int : index de l'emplacement o� ajouter la case en param�tre
     * @param e Case : object de type Case � ajouter au chemin
     * @throws IllegalArgumentException Exception soulev� si le param�tre n'est pas de type Case
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
     * Supprime la premi�re occurence de la case en param�tre.
     * @param e Case : object de type Case � ajouter au chemin
     * @return boolean : true si l'objet �tait contenue dans la liste
     * @throws IllegalArgumentException Exception soulev� si le param�tre n'est pas de type Case
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
     * Supprime la premi�re occurence de la case en param�tre.
     * @param i int : index de l'emplacement de la case � supprimer
     * @return boolean : true si l'objet �tait contenue dans la liste
     * @throws IllegalArgumentException Exception soulev� si le param�tre n'est pas de type Case
     */
    @Override
    public Case remove(int i) throws IllegalArgumentException {
      return (Case) super.remove(i);
    }
    
    /**
     * V�rifie si ce chemin est vide.
     * @return boolean : true si ce chemin est vide.
     */
    @Override
    public boolean isEmpty(){
        return super.isEmpty();
    }
    
    /**
     * Vide le chemin en retirant toutes les cases.
     */
    @Override
    public void clear(){
        super.clear();
    }

    /**
     * Renvoie la case se trouvant � l'index pass� en param�tre.
     * @param i int : index de l'emplacement de la case � renvoyer
     * @return Case : la case � l'index pass� en param�tre
     */
    @Override
    public Case get(int i) {
        return (Case) super.get(i); 
    }

    /**
     * Remplace une case � l'index pass� en pram�tre par la case pass� en pram�tre.
     * @param i int : index de l'emplacement de la case � remplacer par celle pass� en param�tre
     * @param e Case : object de type Case � ajouter au chemin
     * @return Case : la case qui viens d'�tre remplac�
     * @throws IllegalArgumentException Exception soulev� si le param�tre n'est pas de type Case
     */
    @Override
    public Case set(int i, Object e) throws IllegalArgumentException {
        if(e instanceof Case){
          Case c = (Case) e;
          return (Case) super.set(i, c);
      } else {
          throw new IllegalArgumentException();
      }
    }
    
}
