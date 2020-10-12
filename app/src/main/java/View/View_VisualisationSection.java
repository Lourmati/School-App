package View;


import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.dev.TP2_Mobile.R;

public class View_VisualisationSection {

    AppCompatActivity activity;
    TextView titreSection;

    public View_VisualisationSection(AppCompatActivity activity) {
        this.activity = activity;
        titreSection = activity.findViewById(R.id.tvTitreSection);
    }

    public void updateTitreSection(int idSection) {
        switch (idSection) {
            case -1:
                titreSection.setText("Erreur: Pas de titre");   // remove hardcoded string
                break;
            case 1:
                titreSection.setText(R.string.matiere_et_produits);
                break;
            case 2:
                titreSection.setText(R.string.equipements);
                break;
            case 3:
                titreSection.setText(R.string.tache_et_exigences);
                break;
            case 4:
                titreSection.setText(R.string.individu);
                break;
            case 5:
                titreSection.setText(R.string.env_de_travail);
                break;
            case 6:
                titreSection.setText(R.string.res_humaines);
                break;
        }
    }
}
