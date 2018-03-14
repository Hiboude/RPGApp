package hiboude.rpglife.QueteView;

import java.util.ArrayList;

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
        quetes.add(new Quete("n0","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n1","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n2","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n3","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n4","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n5","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n6","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n7","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n8","d", R.mipmap.ic_launcher));
        quetes.add(new Quete("n9","d", R.mipmap.ic_launcher));
    }
}
