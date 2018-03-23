package hiboude.rpglife;

import android.graphics.Color;
import android.media.Image;

import java.util.ArrayList;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Competence extends Xp {

    private int cId;
    private int cCaId;
    private String cNom;
    private int cIcone;
    private ArrayList<Quete> cQuetes;

    public Competence(int cId,int cCaId, String cNom, int cIconeicone) {
        super(100,0,1,100);
        cQuetes = new ArrayList<>();
        this.cId = cId;
        this.cCaId = cCaId;
        this.cNom = cNom;
        this.cIcone = cIconeicone;
    }
    public Competence() {
        super(100,0,1,100);
        this.cId = 0;
        this.cCaId = 0;
        this.cNom = "";
        this.cIcone = 0;
    }

    public int getcId() {
        return cId;
    }

    public int getcCaId() {
        return cCaId;
    }

    public void setcCaId(int cCaId) {
        this.cCaId = cCaId;
    }

    public String getcNom() {

        return cNom;

    }

    public void setcQuetes(ArrayList<Quete> cQuetes) {
        this.cQuetes = cQuetes;
    }

    public int getcIcone() {
        return cIcone;
    }

    public ArrayList<Quete> getcQuetes() {
        return cQuetes;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setcNom(String cNom) {
        this.cNom = cNom;
    }

    public void setcIcone(int cIcone) {
        this.cIcone = cIcone;
    }
}
