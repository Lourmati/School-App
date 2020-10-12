package View;

import android.graphics.drawable.AnimationDrawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.dev.TP2_Mobile.R;


public class View_LoginPage {

    AppCompatActivity activity;

    public View_LoginPage(AppCompatActivity activity) {
        this.activity = activity;
        ConstraintLayout constraintLayout = activity.findViewById(R.id.loginpage_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
    }
}
