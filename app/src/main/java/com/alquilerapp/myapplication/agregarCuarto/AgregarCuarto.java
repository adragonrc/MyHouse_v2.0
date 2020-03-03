package com.alquilerapp.myapplication.agregarCuarto;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alquilerapp.myapplication.AdministradorCamara;
import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.Save;
import com.alquilerapp.myapplication.agregarCuarto.Interfaz;
import com.alquilerapp.myapplication.agregarCuarto.Presenter;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgregarCuarto extends BaseActivity<Interfaz.Presenter> implements Interfaz.View {
    private EditText numeroDeCuarto;
    private EditText precio;
    private EditText detalles;
    private ImageView ivPhoto;
    private TextView tvDescripcion;
    private AdministradorCamara adc;
    private String currentPhotoPath;
    private Bitmap bmGuardar;

    public void onClickAgregar(View view){
        String sNumCuarto = numeroDeCuarto.getText().toString();
        String sPrecio = precio.getText().toString();
        String sDetalles= detalles.getText().toString();
        Save s = new Save();
        //ivPhoto.buildDrawingCache();
        currentPhotoPath = s.SaveImage(this, bmGuardar);
        presenter.insertarCuarto(sNumCuarto,sPrecio,sDetalles, currentPhotoPath);
        Toast toast1 = Toast.makeText(getApplicationContext(), "Toast por defecto", Toast.LENGTH_SHORT);
        toast1.show();
    }

    @Override
    public void onClickCamara(View view) {
        Intent i = adc.dispatchTakePictureIntent();
        startActivityForResult(i, AdministradorCamara.REQUEST_TAKE_PHOTO);
    }

    protected void iniciarViews(){
        numeroDeCuarto = findViewById(R.id.etNumeroCuarto);
        precio = findViewById(R.id.etPrecio);
        detalles = findViewById(R.id.etDetalles);
        ivPhoto = findViewById(R.id.ivPhoto);
        tvDescripcion = findViewById(R.id.tvDescripcionBtCamara);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                bmGuardar = BitmapFactory.decodeFile(result.getUri().getPath());
                ivPhoto.setImageURI(result.getUri());
            }else{
                if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                    Exception e = result.getError();
                    Toast.makeText(this, "Posible Error es: "+ e, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void iniciarComandos() {
        numeroDeCuarto.requestFocus();
        adc = new AdministradorCamara(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_agregar_cuarto;
    }

    @NonNull
    @Override
    protected Interfaz.Presenter createPresenter() {
        return new Presenter(this);
    }

    public void onChooseFile(View v){
        CropImage.activity().setMaxCropResultSize(0,0)
                .setAllowFlipping(true)
                .setAspectRatio(15,10)
                .setMinCropResultSize(1000,750)
                .setMaxCropResultSize(3000,2250)
                .start(this);
    }

}
