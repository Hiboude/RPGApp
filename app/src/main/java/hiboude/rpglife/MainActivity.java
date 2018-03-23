package hiboude.rpglife;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements Tab1.OnFragmentInteractionListener,Tab2.OnFragmentInteractionListener,Tab3.OnFragmentInteractionListener{

    ProgressBar xpBar;
    TextView level;

    int l;
    Utilisateur utilisateur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        Button b = (Button)findViewById(R.id.button);
        xpBar = (ProgressBar)findViewById(R.id.xpBar);
        level = (TextView)findViewById(R.id.level);

        Color c = new Color();
        int color = Color.rgb(0, 240, 200);
     //   Xp uXp = new Xp(100,0,1,c);

        //Initialisation de la base de données
       // initDb();
         UtilisateurManager um = new UtilisateurManager(this);
        um.open();
        utilisateur = um.getUtilisateur();
        um.close();
        xpBar.setProgress(utilisateur.getXpActuel());
        xpBar.setMax(utilisateur.getXpRequis());
        level.setText(String.valueOf(utilisateur.getNiveau()));





        //Met la couleur sur la progressbar
        xpBar.getProgressDrawable().setColorFilter(color, PorterDuff.Mode.SRC_IN);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
        tabLayout.addTab(tabLayout.newTab().setText("Quête"));
        tabLayout.addTab(tabLayout.newTab().setText("Compétence"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
        final hiboude.rpglife.PagerAdapter adapter = new hiboude.rpglife.PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void addXp(View view){
        UtilisateurManager um = new UtilisateurManager(this);
        um.open();
        utilisateur.addXp(10);
        System.out.println("<<<<<<<<<<<<<<<<<<" + um.modUtilisateur(utilisateur));
        um.close();
        xpBar.setProgress(utilisateur.getXpActuel());
        level.setText(String.valueOf(utilisateur.getNiveau()));

    }

    private void initDb()
    {
        UtilisateurManager um = new UtilisateurManager(this);
        Utilisateur u = new Utilisateur("Hiboude",100);
        um.open();
        um.addUtilisateur(u);
        um.close();
        //Ajout dans Caracteristique

        CaracteristiqueManager caracteristiqueManager = new CaracteristiqueManager(this);
        caracteristiqueManager.open();

        Caracteristique force = new Caracteristique(1,"Force",R.mipmap.biceps);
        caracteristiqueManager.addCaracteristique(force);

        caracteristiqueManager.close();

        CompetenceManager competenceManager = new CompetenceManager(this);
        competenceManager.open();

        Competence musculation = new Competence(1,1,"Musculation",R.mipmap.weight);
        competenceManager.addCompetence(musculation);

        competenceManager.close();

    }

}
