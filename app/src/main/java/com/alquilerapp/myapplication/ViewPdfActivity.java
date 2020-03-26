package com.alquilerapp.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.vercuarto.VerCuartoActivity;
import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class ViewPdfActivity extends AppCompatActivity {
    public static final String EXTRA_PATH_PDF = "path_pdf";
    private PDFView pdfView;
    private File file;
    private String correo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Voucher");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pdf);
        pdfView = findViewById(R.id.pdfView);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            file = new File(bundle.getString(EXTRA_PATH_PDF, ""));
            pdfView.fromFile(file)
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .enableAntialiasing(true)
                    .load();
        }
    }
    public void onclick(View view){
        Send.sendForGMail(this, file.getName(), getIntent().getStringExtra(TUsuario.CORREO));
    }
    public void onclick2(View view){
        Send.sendForWhatsapp(this, file.getName(), getIntent().getStringExtra(TUsuario.NUMERO_TEL));
    }
}

