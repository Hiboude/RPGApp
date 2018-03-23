package hiboude.rpglife;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.SeekBar;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import hiboude.rpglife.QueteView.ListeDeQuete;



public class FormulaireQ extends AppCompatActivity {

    EditText Nom;
    EditText Description;
    AutoCompleteTextView Competence;
    EditText DateDebut;
    EditText Duree;

    NumberPicker npL ;
    NumberPicker npMa ;
    NumberPicker npMe ;
    NumberPicker npJ ;
    NumberPicker npV ;
    NumberPicker npS ;
    NumberPicker npD ;

    SeekBar Complexite ;
    SeekBar Importance ;
    SeekBar Apprehension;

    ImageButton validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_q);

        //Initialisation des edits texts

        Nom = (EditText)findViewById(R.id.queteFormNom);
        Description = (EditText)findViewById(R.id.queteFormDescription);

        Competence = (AutoCompleteTextView)findViewById(R.id.queteFormCompetence);
        CompetenceManager cm = new CompetenceManager(this);
        cm.open();
        final HashMap<String,Integer> competencesById = cm.competencesById();
        ArrayList<String> nomCompetences = new ArrayList<>(competencesById.keySet());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,nomCompetences);
        Competence.setThreshold(1);
        Competence.setAdapter(adapter);
        DateDebut = (EditText)findViewById(R.id.queteFormDateDebut);
        Duree = (EditText)findViewById(R.id.queteFormDuree);

        //Initialisation des numberpickers

        npL = (NumberPicker)findViewById(R.id.queteFormL);
        npL.setMinValue(0);
        npL.setMaxValue(10);

        npMa = (NumberPicker)findViewById(R.id.queteFormMa);
        npMa.setMinValue(0);
        npMa.setMaxValue(10);

        npMe = (NumberPicker)findViewById(R.id.queteFormMe);
        npMe.setMinValue(0);
        npMe.setMaxValue(10);

        npJ = (NumberPicker)findViewById(R.id.queteFormJ);
        npJ.setMinValue(0);
        npJ.setMaxValue(10);

        npV = (NumberPicker)findViewById(R.id.queteFormV);
        npV.setMinValue(0);
        npV.setMaxValue(10);

        npS = (NumberPicker)findViewById(R.id.queteFormS);
        npS.setMinValue(0);
        npS.setMaxValue(10);

        npD = (NumberPicker)findViewById(R.id.queteFormD);
        npD.setMinValue(0);
        npD.setMaxValue(10);

        //Initialisation des seekbars

        Complexite = (SeekBar)findViewById(R.id.seekBarComplexite);
        Importance = (SeekBar)findViewById(R.id.seekBarImportance);
        Apprehension = (SeekBar)findViewById(R.id.seekBarApprehension);

        //Initialisation bouton validé

        validation = (ImageButton)findViewById(R.id.queteFormValid);


        validation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //A changer
                int id = 156;
                String NomText = String.valueOf(Nom.getText());
                String DescriptionText = String.valueOf(Description.getText());

                int ComplexiteText = Complexite.getProgress();
                int ApprehensionText = Apprehension.getProgress();
                int ImportanceText = Importance.getProgress();
                int DureeText = Integer.valueOf(String.valueOf(Duree.getText()));
                HashMap<String, Integer> RepetitionText = null;
                Date DateDebutText = null;
                try {
                    DateDebutText = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE).parse(String.valueOf(DateDebut.getText()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Quete nouvelle = new Quete(id,competencesById.get(String.valueOf(Competence.getText())),NomText,DescriptionText,ComplexiteText,ImportanceText,ApprehensionText,DureeText,RepetitionText,DateDebutText);
                Intent i = new Intent();
                i.putExtra("NouvQuete",nouvelle);
                FormulaireQ.this.setResult(1,i);
                FormulaireQ.this.finish();
            }
        });

    }

}
