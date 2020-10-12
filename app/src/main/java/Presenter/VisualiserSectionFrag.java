package Presenter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.dev.TP2_Mobile.R;

public class VisualiserSectionFrag extends Fragment {

    private String question;
    private String reponse;

    public VisualiserSectionFrag(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualiser_section, container, false);

        TextView tvQuestion = view.findViewById(R.id.tvQuestion);
        tvQuestion.setText(question);

        TextView tvReponse = view.findViewById(R.id.tvReponse);
        tvReponse.setText(reponse);

        return view;
    }
}
