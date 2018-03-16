package hiboude.rpglife;

import android.graphics.Color;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Utilisateur extends Xp{

    private int uId;
    private String uPseudo;
    private int uPiece;

    public Utilisateur(String n, int p )
    {
        super(100,0,1,100);
        uId=1;
        uPseudo = n;
        uPiece = p ;
    }

    public void setuPseudo(String uPseudo) {
        this.uPseudo = uPseudo;
    }

    public void setuPiece(int uPiece) {
        this.uPiece = uPiece;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getuId() {
        return uId;
    }

    public Utilisateur()

    {

        super(100,0,1, Color.rgb(200, 0,0));
        uId=0;
        uPseudo = "";
        uPiece = 0 ;
    }

    public String getPseudo() {
        return uPseudo;
    }

    public int getPiece() {
        return uPiece;
    }


    public void addPiece(int nb) {
        uPiece += nb;
    }
}
