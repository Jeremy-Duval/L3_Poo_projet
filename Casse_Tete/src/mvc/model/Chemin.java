/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.util.ArrayList;

/**
 * Chemin pour connecter une paire de symbole. Hérite de l'objet ArrayList.
 *
 * @author Jeremy
 */
public class Chemin extends ArrayList {

    /**
     * Constructeur par défaut.
     */
    public Chemin() {
        super();
    }

    /**
     * Ajoute une case en fin de chemin.
     *
     * @param e Case : object de type Case à ajouter au chemin
     * @return boolean : true (comme pour une ArrayList)
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est
     * pas de type Case
     */
    @Override
    public boolean add(Object e) throws IllegalArgumentException {
        if (e instanceof Case) {
            Case c = (Case) e;
            return super.add(c);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Ajoute une case à l'index spécifié de ce chemin.
     *
     * @param i int : index de l'emplacement où ajouter la case en paramètre
     * @param e Case : object de type Case à ajouter au chemin
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est
     * pas de type Case
     */
    @Override
    public void add(int i, Object e) throws IllegalArgumentException {
        if (e instanceof Case) {
            Case c = (Case) e;
            super.add(i, c);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Supprime la première occurence de la case en paramètre.
     *
     * @param e Case : object de type Case à ajouter au chemin
     * @return boolean : true si l'objet était contenue dans la liste
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est
     * pas de type Case
     */
    @Override
    public boolean remove(Object e) throws IllegalArgumentException {
        if (e instanceof Case) {
            Case c = (Case) e;
            return super.remove(c);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Supprime la première occurence de la case en paramètre.
     *
     * @param i int : index de l'emplacement de la case à supprimer
     * @return boolean : true si l'objet était contenue dans la liste
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est
     * pas de type Case
     */
    @Override
    public Case remove(int i) throws IllegalArgumentException {
        return (Case) super.remove(i);
    }

    /**
     * Vérifie si ce chemin est vide.
     *
     * @return boolean : true si ce chemin est vide.
     */
    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    /**
     * Vide le chemin en retirant toutes les cases.
     */
    @Override
    public void clear() {
        super.clear();
    }

    /**
     * Renvoie la case se trouvant à l'index passé en paramètre.
     *
     * @param i int : index de l'emplacement de la case à renvoyer
     * @return Case : la case à l'index passé en paramètre
     */
    @Override
    public Case get(int i) {
        return (Case) super.get(i);
    }

    /**
     * Remplace une case à l'index passé en pramètre par la case passé en
     * pramètre.
     *
     * @param i int : index de l'emplacement de la case à remplacer par celle
     * passé en paramètre
     * @param e Case : object de type Case à ajouter au chemin
     * @return Case : la case qui viens d'être remplacé
     * @throws IllegalArgumentException Exception soulevé si le paramètre n'est
     * pas de type Case
     */
    @Override
    public Case set(int i, Object e) throws IllegalArgumentException {
        if (e instanceof Case) {
            Case c = (Case) e;
            return (Case) super.set(i, c);
        } else {
            throw new IllegalArgumentException();
        }
    }

}
