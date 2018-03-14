package hiboude.rpglife;

import android.graphics.Color;
import android.media.Image;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Competence extends Xp {

    private int cId;
    private String cNom;
    private int cIconeicone;

    public Competence(int cId, String cNom, int cIconeicone) {
        super(100,0,1,100);
        this.cId = cId;
        this.cNom = cNom;
        this.cIconeicone = cIconeicone;
    }
}
