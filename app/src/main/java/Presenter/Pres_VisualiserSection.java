package Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import com.dev.TP2_Mobile.R;

import java.util.ArrayList;

import Model.Mod_DBHelper;
import Model.Mod_VisualiserSection;
import View.View_VisualisationSection;


public class Pres_VisualiserSection extends AppCompatActivity {

    View_VisualisationSection view;
    Mod_VisualiserSection model;

    Mod_DBHelper dataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiser_section);

        dataBase = new Mod_DBHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);

        model = new Mod_VisualiserSection();
        view = new View_VisualisationSection(this);

        int idSection = getIntent().getIntExtra("idSection", -1);
        view.updateTitreSection(idSection);

        // Stocker les questions de la section dans le model et faire afficher la premiere question dans le fragment
        int cursor = 1;
        String questionRaw = dataBase.GetData(Mod_DBHelper.Table.QUESTIONS_DEFAULT, String.valueOf(cursor));

        while (!questionRaw.equals("not_found")){
            String[] firstSplit = questionRaw.split(";");
            String section_id = firstSplit[4].split("=")[1];

            if (Integer.parseInt(section_id) == idSection) {
                String question = firstSplit[1].split("=")[1];
                model.getQuestions().add(question);
            }

            cursor++;
            questionRaw = dataBase.GetData(Mod_DBHelper.Table.QUESTIONS_DEFAULT, String.valueOf(cursor));
        }

        Fragment frag = new VisualiserSectionFrag(model.getCurrentQuestion(), "Reponse 1");
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frag).commit();

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getCurrentQuestionIdx() != 0) {
                    model.decCurrentQuestionIdx();
                    Fragment frag = new VisualiserSectionFrag(model.getCurrentQuestion(), "Reponse arbitraire");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frag).commit();
                }
            }
        });

        Button btnFoward = findViewById(R.id.btnFoward);
        btnFoward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (model.getCurrentQuestionIdx() + 1 < model.getQuestions().size()) {
                    model.incCurrentQuestionIdx();
                    Fragment frag = new VisualiserSectionFrag(model.getCurrentQuestion(), "Reponse arbitraire");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, frag).commit();
                }
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.logout:
                Intent intent = new Intent(getApplicationContext(),
                        Pres_LoginPage.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }


}
