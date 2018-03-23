package hiboude.rpglife.QueteView;

import java.util.ArrayList;

import hiboude.rpglife.Quete;
import hiboude.rpglife.R;

/**
 * Created by Alexa on 07/03/2018.
 */

public class ListeDeQuete {
    private ArrayList<Quete> quetes;

    public ListeDeQuete() {
        quetes = new ArrayList<Quete>();
        createList();
    }

    public ArrayList<Quete> getQuetes() {
        return quetes;
    }

    public Quete get(int pos) {
        return quetes.get(pos);
    }

    public int size() {
        return quetes.size();
    }

    private void createList() {

    }

    public void addQuete(Quete q) {
        getQuetes().add(q);
    }

}
