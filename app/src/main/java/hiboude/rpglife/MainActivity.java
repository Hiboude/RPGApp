package hiboude.rpglife;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
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
        Xp uXp = new Xp(100,0,1,c);
        xpBar.setProgress(uXp.getXpActuel());
        xpBar.setMax(uXp.getXpRequis());

        utilisateur = new Utilisateur("Hiboude",0,uXp);
        level.setText(String.valueOf(uXp.getLevel()));

        //Met la couleur sur la progressbar
        xpBar.getProgressDrawable().setColorFilter(color,android.graphics.PorterDuff.Mode.SRC_IN);


        TabLayout tabLayout = (TabLayout)findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Quête"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
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
        utilisateur.getExperience().addXp(10);
        xpBar.setProgress(utilisateur.getExperience().getXpActuel());
        level.setText(String.valueOf(utilisateur.getExperience().getLevel()));

    }

}