package hiboude.rpglife;

import android.content.Context;
import android.widget.*;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import hiboude.rpglife.QueteView.ListeDeQuete;
import hiboude.rpglife.QueteView.QueteAdapter;


/**
 * Created by Alexa on 23/03/2018.
 */

public class CompetenceAdapter extends BaseAdapter {

    private ListeDeCompetence mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public CompetenceAdapter(Context context, ListeDeCompetence aListP) {
        mContext = context;
        mListP = aListP;
        mInflater = LayoutInflater.from(mContext);
    }

    public int getCount() {
        return mListP.size();
    }

    public Object getItem(int position) {
        return mListP.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ConstraintLayout layoutItem;
        //(1) : Réutilisation des layouts
        if (convertView == null) {
            //Initialisation de notre item à partir du  layout XML "quete_layout.xml"
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.competence_layout, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_Nom = (TextView)layoutItem.findViewById(R.id.nomCompetence);
        ImageView img = layoutItem.findViewById(R.id.imageCompetence);

        //(3) : Renseignement des valeurs
        tv_Nom.setText(mListP.get(position).getcNom());
        img.setImageResource(mListP.get(position).getcIcone());


        //On retourne l'item créé.
        return layoutItem;
    }

    private ArrayList<CompetenceAdapter.CompetenceAdapterListener> mListListener = new ArrayList<CompetenceAdapter.CompetenceAdapterListener>();
    public void addListener(CompetenceAdapter.CompetenceAdapterListener aListener) {
        mListListener.add(aListener);
    }

    /**
     * Interface pour écouter les évènements
     */
    public interface CompetenceAdapterListener {

    }
}
