package hiboude.rpglife.QueteView;

import android.content.Context;

import java.util.ArrayList;

import hiboude.rpglife.CompetenceManager;
import hiboude.rpglife.Quete;
import hiboude.rpglife.QueteManager;
import hiboude.rpglife.R;

/**
 * Created by Alexa on 07/03/2018.
 */

public class ListeDeQuete {
    private ArrayList<Quete> quetes;

    public ListeDeQuete(Context context) {
        quetes = new ArrayList<Quete>();
        createList(context);
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

    private void createList(Context context) {
        QueteManager qm = new QueteManager(context);
        qm.open();
        quetes = qm.getQuetes();
        qm.close();

    }

    public void addQuete(Quete q) {
        getQuetes().add(q);
    }

}
