/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.enumeration;

/**
 * Enumération des images correspondant aux différents liens.
 *
 * @author jeremy
 */
public enum Liens {
    VIDE("assets/img/empty.png"),
    HORIZONTAL("assets/img/liens/l_hor.png"),
    VERTICAL("assets/img/liens/l_ver.png"),
    ANGLE_INF_DROIT("assets/img/liens/l_adb.png"),
    ANGLE_INF_GAUCHE("assets/img/liens/l_agb.png"),
    ANGLE_SUP_DROIT("assets/img/liens/l_adh.png"),
    ANGLE_SUP_GAUCHE("assets/img/liens/l_agh.png");

    private String imgPath = "";

    /**
     * Constructeur.
     *
     * @param path String : path contenu dans le symbole
     */
    Liens(String path) {
        this.imgPath = path;
    }

    /**
     *
     * @return String : le chemin de l'image correspondant au lien.
     */
    public String getImgPath() {
        return this.imgPath;
    }
}
