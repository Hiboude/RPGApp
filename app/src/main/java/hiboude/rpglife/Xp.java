package hiboude.rpglife;

import android.graphics.Color;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Xp {

    private int xpRequis;
    private int xpActuel;
    private int niveau;
    private int color;

    public void setColor(int color) {

        this.color = color;
    }

    public void setXpActuel(int xpActuel) {
        this.xpActuel = xpActuel;
    }

    public Xp(int xpR, int xpA, int lvl, int c){
        xpRequis = xpR;
        xpActuel = xpA;
        niveau = lvl;
        color = c;


    }

    public Xp() {
    }

    public int getXpActuel() {
        return xpActuel;
    }

    public int getColor() {
        return color;
    }

    public int getXpRequis() {
        return xpRequis;
    }

    public void setXpRequis(int xpR) {
        xpRequis = xpR;
    }

    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int lvl) {
        niveau = lvl;
    }

    public void addXp(int xp) {
        xpActuel+= xp;
        if(xpActuel>=xpRequis) {
            xpActuel -= xpRequis;
            //incXpRequis();
            niveau++;
        }
    }

    public void incXpRequis(){
        xpRequis+= 50;
    }

}
