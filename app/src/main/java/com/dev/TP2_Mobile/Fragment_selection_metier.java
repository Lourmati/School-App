package com.dev.TP2_Mobile;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import static android.app.Activity.RESULT_OK;

public class Fragment_selection_metier extends Fragment implements View.OnClickListener{

    private static final int REQUEST_IMAGE_CAPTURE = 101;
    ImageView imageView;
    //Button button;

    private String question;
    private String reponse;

    public Fragment_selection_metier(String question, String reponse) {
        this.question = question;
        this.reponse = reponse;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_home, container, false);

        TextView tvQuestion = view.findViewById(R.id.QuestionFrag1);
        tvQuestion.setText(question);


        imageView = view.findViewById(R.id.imageViewPhoto);
        Button button = view.findViewById(R.id.buttonAddPhoto);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture(v);
            }
        });

        return view;


    }


    public void takePicture(View view){
        Intent imageTakeIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (imageTakeIntent.resolveActivity(getActivity().getPackageManager()) != null){
            startActivityForResult(imageTakeIntent,REQUEST_IMAGE_CAPTURE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
        //super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void onClick(View v) {

    }
}
