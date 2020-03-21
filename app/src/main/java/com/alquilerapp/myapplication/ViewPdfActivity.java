package com.alquilerapp.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPdfActivity extends AppCompatActivity {

    private PDFView pdfView;
    private File file;
    private String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView = findViewById(R.id.pdfView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            file = new File(bundle.getString("path", ""));
            pdfView.fromFile(file)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .enableAntialiasing(true)
                    .load();
        }
    }
    public void onclick(View view){
        Send.sendForGMail(this, file.getName());
    }
    public void onclick2(View view){
        Send.sendForWhatsapp(this, file.getName());
    }
}

