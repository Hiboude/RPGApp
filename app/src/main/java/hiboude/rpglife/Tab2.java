package hiboude.rpglife;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import hiboude.rpglife.Quete;
import hiboude.rpglife.QueteView.ListeDeQuete;
import hiboude.rpglife.QueteView.QueteAdapter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2 extends Fragment implements QueteAdapter.QueteAdapterListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    //Mes attribus
    private ListView lvQuete;
    private QueteAdapter qAdapter;
    private ImageButton add;
    private ListeDeQuete lq;

    public Tab2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2 newInstance(String param1, String param2) {
        Tab2 fragment = new Tab2();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        lq = new ListeDeQuete(getContext());
        qAdapter = new QueteAdapter(getContext(),lq);

        lvQuete = (ListView)rootView.findViewById(R.id.viewQuete);
        lvQuete.setAdapter(qAdapter);
        qAdapter.addListener(this);
        // Inflate the layout for this fragment
        //Bouton qui lance l'activité
        add = (ImageButton)rootView.findViewById(R.id.addQuete);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formulaireQuete(view);
            }
        });
        return rootView;

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClickNom(Quete item, int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(item.getqNom());

        builder.setMessage(item.getqDescription());
        builder.setPositiveButton("oui", null);
        builder.setNegativeButton("non", null);
        builder.show();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        void addXp(View view);
    }

    public void formulaireQuete(View view)
    {
        Intent i = new Intent(getActivity(),FormulaireQ.class);
        startActivityForResult(i,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if( resultCode==1 ) {
            Quete q = (Quete) data.getSerializableExtra("NouvQuete");
            addQuete(lq,q);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void supprimeQuete(ListeDeQuete lq,int position)
    {
        QueteManager qm = new QueteManager(getContext());
        qm.open();
        qm.supQuete(lq.get(position));
        qm.close();
        lq.getQuetes().remove(position);
        qAdapter.notifyDataSetChanged();
        //Ajout d'xp test
        mListener.addXp(getView());
    }

    public void addQuete(ListeDeQuete lq, Quete q)
    {
        lq.addQuete(q);
        QueteManager qm = new QueteManager(getContext());
        qm.open();
        qm.addQuete(q);
        qm.close();
        qAdapter.notifyDataSetChanged();
    }

}
