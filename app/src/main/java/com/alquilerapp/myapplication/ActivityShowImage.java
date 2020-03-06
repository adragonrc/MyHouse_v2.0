package com.alquilerapp.myapplication;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;

import uk.co.senab.photoview.PhotoView;

public class ActivityShowImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);
        ChangeBounds fade =  new ChangeBounds();
        //Fade fade = new Fade();
        View decor = getWindow().getDecorView();
        fade.excludeTarget(decor.findViewById(R.id.action_bar_container), true);
        fade.excludeTarget(android.R.id.statusBarBackground, true);
        fade.excludeTarget(android.R.id.navigationBarBackground, true);

        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

        Intent intent = getIntent();
        String uri = intent.getStringExtra("bitMap");
        PhotoView photoView =  findViewById(R.id.ivImage);

        if(uri != null && !uri.equals("")) {
            photoView.setImageBitmap(BitmapFactory.decodeFile(uri));

        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
