package hiboude.rpglife;

/**
 * Created by Alexa on 07/02/2018.
 */

public class Utilisateur extends Xp{

    private String pseudo;
    private int piece;
  //  private Xp experience;

    public Utilisateur(String n, int p )
    {
        pseudo = n;
        piece = p ;
    }

    public String getPseudo() {
        return pseudo;
    }

    public int getPiece() {
        return piece;
    }


    public void addPiece(int nb) {
        piece += nb;
    }
}
