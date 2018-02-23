package hiboude.rpglife;

import java.util.Date;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Quete {

    private String nom;
    private String description;
    private float complexite;
    private float apprehension;
    private int repetitionJour;
    private int duree;
    private Date dateDebut;


    public Quete(String n, String desc, float c, float a, int r, int dur, Date date)
    {
        nom = n;
        description = desc;
        complexite = c;
        apprehension = a;
        repetitionJour = r;
        duree = dur;
        dateDebut = date;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public float getComplexite() {
        return complexite;
    }

    public float getApprehension() {
        return apprehension;
    }

    public int getRepetitionJour() {
        return repetitionJour;
    }

    public int getDuree() {
        return duree;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

}
