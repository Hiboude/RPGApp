package hiboude.rpglife;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by Alexa on 07/02/2018.
 * Caracteristique correspond à la force/agi/intel etc...
 */

public class Caracteristique extends Xp {
    private int caId;
    private String caNom;
    private int caIcone;
    //private Xp experience;
    private ArrayList<Competence> caCompetences;

    public String getCaNom() {
        return caNom;
    }

    public int getCaIcone() {
        return caIcone;
    }

    public void setCaId(int caId) {
        this.caId = caId;
    }


    public void setCaNom(String caNom) {
        this.caNom = caNom;
    }

    public void setCaIcone(int caIcone) {
        this.caIcone = caIcone;
    }

    public void setCaCompetences(ArrayList<Competence> caCompetences) {
        this.caCompetences = caCompetences;
    }

    public int getCaId() {
        return caId;
    }

    public ArrayList<Competence> getCaCompetences() {
        return caCompetences;
    }
    public Caracteristique() {
        super(100,0,1,100);
        caCompetences = new ArrayList<>();
        caId=0;
        caNom="";
        caIcone=0;
    }

    public Caracteristique( int caId,String caNom, int caIcone) {
        super(100,0,1,100);
        caCompetences = new ArrayList<>();
        this.caNom = caNom;
        this.caIcone = caIcone;
        this.caId = caId;
    }
}
