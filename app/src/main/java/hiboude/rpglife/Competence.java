package hiboude.rpglife;

import android.media.Image;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Competence extends Xp {

    private String nom;
    private Image img;

    public Competence(String s, Image i){
        nom = s;
        img = i;
    }

    public String getNom() {
        return nom;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image i) {
        img = i;
    }
}
