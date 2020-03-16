package com.alquilerapp.myapplication.historialUserPakage;

import android.content.ContentValues;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alquilerapp.myapplication.ActivityShowImage;
import com.alquilerapp.myapplication.AlquilerUsuario.AlquilerUsuarioActivity;
import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;


public class HistorialUsuarioActivity extends BaseActivity<Interfaz.presenter> implements Interfaz.view {
    private TextView tvDNI;
    private TextView tvNombres;
    private TextView tvApellidoPat;
    private TextView tvApellidoMat;
    private TextView tvNumAlquiler;

    private EditText etNombres;
    private EditText etApellidoPat;
    private EditText etApellidoMat;

    private LinearLayout llEditarNombres;
    private LinearLayout llEditarApePat;
    private LinearLayout llEditarApeMat;
    private LinearLayout llConfirNombres;
    private LinearLayout llConfirApePat;
    private LinearLayout llConfirApeMat;

    private ImageView imPhoto;

    private String uriPhoto;

    @Override
    protected void iniciarComandos() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_perfil_usuario;
    }

    @NonNull
    @Override
    protected Interfaz.presenter createPresenter() {
        return new Presenter(this, getIntent().getStringExtra(TUsuario.DNI));
    }


    @Override
    public void mostrarAlerta() {
       // imCalificativo.setImageDrawable(getResources().getDrawable(R.drawable.ic_warning_black_24dp));
    }

    @Override
    public void noMostrarAlerta() {
       // imCalificativo.setImageDrawable(getResources().getDrawable(R.drawable.ic_offline_pin_black_24dp));
    }

    @Override
    public void mostrarDatosUsuario(ContentValues datos, String i) {
        tvDNI.setText(datos.getAsString(TUsuario.DNI));
        tvNombres.setText(datos.getAsString(TUsuario.NOMBRES));
        tvApellidoPat.setText(datos.getAsString(TUsuario.APELLIDO_PAT));
        tvApellidoMat.setText(datos.getAsString(TUsuario.APELLIDO_MAT));
        uriPhoto = datos.getAsString(TUsuario.URI);
        presenter.setImage(imPhoto, uriPhoto);
        tvNumAlquiler.setText(i);
    }

    @Override
    public void modoError(String error){
        tvDNI.setText(error);
        tvNombres.setText(error);
        tvApellidoPat.setText(error);
        tvApellidoMat.setText(error);
    }

    @Override
    public void onBackPressed() {
        View view = getCurrentFocus();
        if (view != null)
        if (view.equals(etApellidoMat)){
            llConfirApeMat.setVisibility(View.GONE);
            llEditarApeMat.setVisibility(View.VISIBLE);
            return;
        }else {
            if (view.equals(etApellidoPat)){
                llConfirApePat.setVisibility(View.GONE);
                llEditarApePat.setVisibility(View.VISIBLE);
                return;
            }else{
                if (view.equals(etNombres)){
                    llConfirNombres.setVisibility(View.GONE);
                    llEditarNombres.setVisibility(View.VISIBLE);
                    return;
                }
            }
        }
        super.onBackPressed();
    }

    @Override
    public void ocEditarNombres(View view) {
        etNombres.setText(tvNombres.getText().toString());
        llEditarNombres.setVisibility(View.GONE);
        llConfirNombres.setVisibility(View.VISIBLE);
        etNombres.requestFocus();
    }

    @Override
    public void ocEditarApePat(View view) {
        etApellidoPat.setText(tvApellidoPat.getText().toString());
        llEditarApePat.setVisibility(View.GONE);
        llConfirApePat.setVisibility(View.VISIBLE);
        etApellidoPat.requestFocus();
    }

    @Override
    public void ocEditarApeMat(View view) {
        etApellidoMat.setText(tvApellidoMat.getText().toString());
        llEditarApeMat.setVisibility(View.GONE);
        llConfirApeMat.setVisibility(View.VISIBLE);
        etApellidoMat.requestFocus();
    }

    @Override
    public void ocConfirNombres(View view) {
        llEditarNombres.setVisibility(View.VISIBLE);
        llConfirNombres.setVisibility(View.GONE);
        presenter.actualizarNombres(etNombres.getText().toString());
    }

    @Override
    public void ocConfirApePat(View view) {
        llEditarApePat.setVisibility(View.VISIBLE);
        llConfirApePat.setVisibility(View.GONE);
        presenter.actualizarApePat(etApellidoPat.getText().toString());
    }

    @Override
    public void ocConfirApeMat(View view) {
        llEditarApeMat.setVisibility(View.VISIBLE);
        llConfirApeMat.setVisibility(View.GONE);
        presenter.actualizarApeMat(etApellidoMat.getText().toString());
    }

    public void onClickPhoto(View view){
        Intent intent = new Intent(this, ActivityShowImage.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imPhoto, ViewCompat.getTransitionName(imPhoto));

        intent.putExtra(ActivityShowImage.DATA_IMAGE, uriPhoto);
        startActivity(intent, options.toBundle());
    }

    @Override
    public void ocVerMas(View view){
        Intent i = new Intent(this, AlquilerUsuarioActivity.class);
        i.putExtra(AlquilerUsuarioActivity.EXTRA_DNI, Integer.valueOf(tvDNI.getText().toString()));
        startActivity(i);
    }

    @Override
    public void actualizarNombres(String nombres) {
        tvNombres.setText(nombres);
    }

    @Override
    public void actualizarApePat(String apellidPaterno) {
        tvApellidoPat.setText(apellidPaterno);
    }

    @Override
    public void actualizarApeMat(String apellidMaterno) {
        tvApellidoMat.setText(apellidMaterno);
    }
    @Override
    public void salir(){
        onBackPressed();
    }

    @Override
    protected void iniciarViews() {
        modificarTransicion();
        tvDNI = findViewById(R.id.hutvDNI);
        tvNombres = findViewById(R.id.hutvNombres);
        tvApellidoPat = findViewById(R.id.hutvApePat);
        tvApellidoMat = findViewById(R.id.hutvApeMat);
        tvNumAlquiler = findViewById(R.id.tvNumAlquiler);

        imPhoto = findViewById(R.id.imPhoto);

        etNombres = findViewById(R.id.etNombres);
        etApellidoPat = findViewById(R.id.etApePat);
        etApellidoMat = findViewById(R.id.etApeMat);

        llEditarNombres = findViewById(R.id.llEditarNombres);
        llEditarApePat = findViewById(R.id.llEditarApellidoPat);
        llEditarApeMat = findViewById(R.id.llEditarMat);
        llConfirNombres = findViewById(R.id.llConfirmNombres);
        llConfirApePat = findViewById(R.id.llConfirmApePat);
        llConfirApeMat = findViewById(R.id.llConfirmApeMat);

    }
}
