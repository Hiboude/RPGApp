package hiboude.rpglife.QueteView;


import android.support.constraint.ConstraintLayout;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.view.*;
import android.graphics.*;
import android.view.View.*;


import hiboude.rpglife.R;

/**
 * Created by Rosa on 03/02/2015.
 */
public class QueteAdapter extends BaseAdapter {

    private ListeDeQuete mListP;

    //Le contexte dans lequel est présent notre adapter
    private Context mContext;

    //Un mécanisme pour gérer l'affichage graphique depuis un layout XML
    private LayoutInflater mInflater;


    public QueteAdapter(Context context, ListeDeQuete aListP) {
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
            layoutItem = (ConstraintLayout) mInflater.inflate(R.layout.quete_layout, parent, false);
        } else {
            layoutItem = (ConstraintLayout) convertView;
        }

        //(2) : Récupération des TextView de notre layout
        TextView tv_Nom = (TextView)layoutItem.findViewById(R.id.nomQuete);
        TextView tv_Xp = (TextView)layoutItem.findViewById(R.id.nombreXp);
        TextView tv_Piece = (TextView)layoutItem.findViewById(R.id.nombrePiece);
        ImageView img = layoutItem.findViewById(R.id.imageQuete);

        //(3) : Renseignement des valeurs
        tv_Nom.setText(mListP.get(position).getNom());
        tv_Xp.setText(String.valueOf(mListP.get(position).calculXp()));
        tv_Piece.setText(String.valueOf(mListP.get(position).calculPiece()));
        img.setImageResource(mListP.get(position).getImage());

        tv_Nom.setTag(position);
        tv_Nom.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Integer position = (Integer)v.getTag();
                sendListener(mListP.get(position), position);

            }

        });
        //On retourne l'item créé.
        return layoutItem;
    }

    //abonnement pour click sur le nom...
    private ArrayList<QueteAdapterListener> mListListener = new ArrayList<QueteAdapterListener>();
    public void addListener(QueteAdapterListener aListener) {
        mListListener.add(aListener);
    }
    private void sendListener(Quete item, int position) {
        for(int i = mListListener.size()-1; i >= 0; i--) {
            mListListener.get(i).onClickNom(item, position);
        }
    }

    /**
     * Interface pour écouter les évènements sur le nom d'une personne
     */
    public interface QueteAdapterListener {
        public void onClickNom(Quete item, int position);
    }

}
