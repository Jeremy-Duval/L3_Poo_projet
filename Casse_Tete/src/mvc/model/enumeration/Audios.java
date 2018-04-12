/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.enumeration;

/**
 * Enumération des différents fichiers audios du jeu.
 *
 * @author jeremy
 */
public enum Audios {
    MUSIC("assets/audio/dream.mp3"),
    VICTORY("assets/audio/victory.mp3");

    private String soundPath = "";

    /**
     * Constructeur.
     *
     * @param path String : path contenu dans le symbole
     */
    Audios(String path) {
        this.soundPath = path;
    }

    /**
     *
     * @return String : le chemin du fichier audio correspondant à
     * l'énumération.
     */
    public String getSoundPath() {
        return this.soundPath;
    }
}
