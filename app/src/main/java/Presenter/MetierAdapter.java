package Presenter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.TP2_Mobile.R;

import java.util.ArrayList;

public class MetierAdapter extends RecyclerView.Adapter<MetierAdapter.MetierViewHolder> {
    private ArrayList<String> list;
    private OnItemClickListener listener;


    public interface OnItemClickListener {
        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public class MetierViewHolder extends RecyclerView.ViewHolder {



        public MetierViewHolder(View view, final OnItemClickListener listener) {
            super(view);



            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // vérifier que le listener n'est pas null
                    if (listener != null) {
                        int position = getAdapterPosition();
                        // la position est valide?
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

        }
    }

    public MetierAdapter(ArrayList<String> list) { this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MetierViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_metier_etudiant, parent, false);
        MetierViewHolder ivh = new MetierViewHolder(v, listener);
        return ivh;

    }

    @Override
    public void onBindViewHolder(@NonNull MetierViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
