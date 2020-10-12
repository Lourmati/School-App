package Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.dev.TP2_Mobile.R;

import java.util.ArrayList;

import Model.sectionMetier;


public class AdapterSectionMetierProf
        extends RecyclerView.Adapter<AdapterSectionMetierProf.SectionMetierProfViewHolder> {

    private ArrayList<sectionMetier> section;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void checkBox();
        void onEditClick(int position);
    }

    public AdapterSectionMetierProf(ArrayList<sectionMetier> section) {
        this.section = section;
    }

    public static class SectionMetierProfViewHolder extends RecyclerView.ViewHolder{

        public TextView letter;
        public CheckBox checkBoxActif;

        public SectionMetierProfViewHolder(View view, final OnItemClickListener listener){
            super(view);
            letter = view.findViewById(R.id.lettreMetier);
            checkBoxActif = view.findViewById(R.id.checkBoxMetier);

            checkBoxActif.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.checkBox();
                        }
                    }
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = SectionMetierProfViewHolder.this.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditClick(position);
                        }
                    }
                }
            });
        }

    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SectionMetierProfViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_metier_param_prof, parent, false);
        SectionMetierProfViewHolder ivh = new SectionMetierProfViewHolder(v, listener);
        return ivh;
    }

    @Override
    public int getItemCount() {
        return section.size();
    }

    @Override
    public void onBindViewHolder(@NonNull SectionMetierProfViewHolder holder, int position) {
        sectionMetier nouvelleSection = section.get(position);

        holder.letter.setText(String.valueOf(nouvelleSection.getFirstLetter()));
        holder.checkBoxActif.setChecked(nouvelleSection.isActive());
    }


}
