/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.enumeration;

/**
 *
 * @author jeremy
 */
public enum Symboles {
    VIDE("assets/img/empty.png"),
    CAT("assets/img/cat_c.png"),
    COW("assets/img/cow_c.png"),
    LEAF("assets/img/leaf_c.png"),
    PINE("assets/img/pine_c.png");

    private String imgPath = "";

    /**
     * Constructeur.
     * @param path String : path contenu dans le symbole
     */
    Symboles(String path) {
        this.imgPath = path;
    }

    public String getImgPath() {
        return this.imgPath;
    }
}
