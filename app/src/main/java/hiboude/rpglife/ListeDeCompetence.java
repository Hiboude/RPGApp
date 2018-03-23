package hiboude.rpglife;

import android.content.Context;

import java.util.ArrayList;



/**
 * Created by Alexa on 23/03/2018.
 */

public class ListeDeCompetence {

    private ArrayList<Competence> competences;

    public ListeDeCompetence(Context context) {
        competences = new ArrayList<Competence>();
        createList(context);
    }

    public ArrayList<Competence> getCompetences() {
        return competences;
    }

    public Competence get(int pos) {
        return competences.get(pos);
    }

    public int size() {
        return competences.size();
    }

    private void createList(Context context) {
        CompetenceManager competenceManager = new CompetenceManager(context);
        competenceManager.open();
        competences = competenceManager.getCompetences(context);
        competenceManager.close();
    }

    public void addQuete(Competence q)
    {
        competences.add(q);
    }
}

