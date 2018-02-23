package hiboude.rpglife;

import android.graphics.Color;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Xp {

    private int xpRequis;
    private int xpActuel;
    private int level;
    private Color couleur;

    public Xp(int xpR, int xpA, int lvl, Color c){
        xpRequis = xpR;
        xpActuel = xpA;
        level = lvl;
        couleur = c;
    }

    public Xp() {
    }

    public int getXpActuel() {
        return xpActuel;
    }

    public int getXpRequis() {
        return xpRequis;
    }

    public void setXpRequis(int xpR) {
        xpRequis = xpR;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int lvl) {
        level = lvl;
    }

    public void addXp(int xp) {
        xpActuel+= xp;
        if(xpActuel>=xpRequis) {
            xpActuel -= xpRequis;
            //incXpRequis();
            level++;
        }
    }

    public void incXpRequis(){
        xpRequis+= 50;
    }

}
