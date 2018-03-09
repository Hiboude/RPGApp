package hiboude.rpglife.QueteView;

import java.util.Date;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Quete {

    private String nom;
    private String description;
    private int image;
    private float complexite;
    private float apprehension;
    private int repetitionJour;
    private int duree;
    private Date dateDebut;


    public Quete(String n, String desc, int img, float c, float a, int r, int dur, Date date)
    {
        nom = n;
        description = desc;
        image = img;
        complexite = c;
        apprehension = a;
        repetitionJour = r;
        duree = dur;
        dateDebut = date;
    }

    public Quete(String n, String desc, int img)
    {
        nom = n;
        description = desc;
        image = img;
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

    public int getImage() { return image; }

    public int calculXp(){
        return 10;
    }

    public int calculPiece(){
        return 5;
    }

}
