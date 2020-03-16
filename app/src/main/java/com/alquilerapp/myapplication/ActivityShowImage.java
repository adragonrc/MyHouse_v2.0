package com.alquilerapp.myapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.View;
import android.widget.ImageView;



public class ActivityShowImage extends AppCompatActivity {
    public static final String DATA_IMAGE = "BIT_MAP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        setTitle("Photo View");
        Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        Intent intent = getIntent();
        String uri = intent.getStringExtra(DATA_IMAGE);
        ImageView photoView =  findViewById(R.id.photo);
        if(uri != null && !uri.equals("")) {
            photoView.setImageBitmap(BitmapFactory.decodeFile(uri));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
