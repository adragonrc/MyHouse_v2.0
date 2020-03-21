package com.alquilerapp.myapplication.VerCuarto;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.cardview.widget.CardView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alquilerapp.myapplication.ActivityShowImage;
import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.ListAlquileres.ListAlquileresActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.ViewPdfActivity;
import com.alquilerapp.myapplication.tableActivity.TableActivity;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.verUsuario.VerUsuario;
import com.alquilerapp.myapplication.agregarInquilino.AgregarInquilino;
import com.alquilerapp.myapplication.historialUserPakage.HistorialUsuarioActivity;
import com.alquilerapp.myapplication.mydialog.DialogConfirm;
import com.alquilerapp.myapplication.mydialog.DialogImput;
import com.alquilerapp.myapplication.mydialog.DialogInterfaz;
import com.alquilerapp.myapplication.mydialog.PresenterDialogImput;

import java.io.File;

public class VerCuartoActivity extends BaseActivity<Interface.Presenter> implements Interface.view {

    private TextView tvNumeroCuarto;
    private TextView tvNombres;
    private TextView tvMensualidad;
    private TextView tvDni;
    private TextView tvDetalles;
    private TextView tvFechaC;

    private EditText etMensualidad;
    private EditText etDetalles;

    private ImageView ivAlert;
    private ImageView ivPerfil;

    private CardView cvDetallesAlquiler;
    private CardView cvMensaje;


    private LinearLayout llBtns;

    private LinearLayout llPagoMensual;
    private LinearLayout llEditorMensualidad;
    private LinearLayout llShowMensualidad;
    private LinearLayout llEditorDetalles;
    private LinearLayout llShowDetalles;

    private DialogInterfaz.DialogImputPresenter dip;

    private DialogConfirm dialogConfirm;

    private DialogInterfaz.DialogConfirmPresenter dcp;

    private View.OnClickListener listener;
    private View.OnClickListener listener2;

    private int iMenu;

    private String URIPerfil;

    private Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(this.iMenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.iVerPagos:{
                String idAlquiler = presenter.getDatosAlquiler().getAsString(TAlquiler.ID);
                Intent i = new Intent(this, TableActivity.class);
                i.putExtra(TAlquiler.ID, idAlquiler);
                startActivity(i);
                break;
            }
            case R.id.iAgregarInquilino:{
                String numeroCuarto = tvNumeroCuarto.getText().toString();
                Intent i = new Intent(this, AgregarInquilino.class);
                i.putExtra(TCuarto.NUMERO, numeroCuarto);
                startActivity(i);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.mostrarDetalles();
    }

    @Override
    protected void iniciarComandos() {

        dip = new PresenterDialogImput(this, "ALERTA") {
            @Override
            public void positiveButtonListener(@Nullable String s) {
                presenter.deshacerContrato(s);
                menu.removeItem(R.id.iVerPagos);
                getMenuInflater().inflate(R.menu.menu_cuarto_no_alquilado, menu);

            }
        };
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogConfirm.showDiaglog(getSupportFragmentManager(),null,dcp);
            }
        };
        listener2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
        iMenu = R.menu.menu_cuarto_no_alquilado;

        dcp = new DialogInterfaz.DialogConfirmPresenter() {
            @Override
            public void positiveButtonListener() {

                presenter.realizarPago();

            }
        };
        dialogConfirm = new DialogConfirm();
    }
    @Override
    protected int getLayout() {
        return R.layout.activity_perfil_cuarto;
    }

    @NonNull
    @Override
    protected Presentador createPresenter() {
        return new Presentador(this, getIntent().getStringExtra(TCuarto.NUMERO));
    }

    @Override
    public void showCuartoAlquilado(ContentValues cuarto, ContentValues usuario, String mensualidad){
        String nombes = usuario.getAsString(TUsuario.NOMBRES) + ", "  + usuario.getAsString(TUsuario.APELLIDO_PAT) + " " + usuario.getAsString(TUsuario.APELLIDO_MAT) + ".";
        tvNumeroCuarto.setText(cuarto.getAsString(TCuarto.NUMERO));
        tvDni.setText(usuario.getAsString(TUsuario.DNI));
        tvNombres.setText(nombes);
        tvMensualidad.setText(mensualidad);
        tvDetalles.setText(cuarto.getAsString(TCuarto.DETALLES));
        //AdministradorCamara.setPic(ivPerfil, usuario.getAsString(TUsuario.URI));
        URIPerfil =  cuarto.getAsString(TCuarto.URL);
        if(URIPerfil != null && !URIPerfil.equals("")) {
            Bitmap bm = BitmapFactory.decodeFile(URIPerfil);
            ivPerfil.setImageBitmap(bm);
        }
        cvMensaje.setVisibility(View.GONE);
        tvFechaC.setText(presenter.getDatosAlquiler().getAsString(TAlquiler.FECHA_C));
        iMenu = R.menu.menu_cuarto;
        cvDetallesAlquiler.setVisibility(View.VISIBLE);
    }

    public void showCuartolibre(ContentValues cuarto){
        cvDetallesAlquiler.setVisibility(View.GONE);
        llBtns.setVisibility(View.GONE);
        cvMensaje.setVisibility(View.VISIBLE);
        tvNumeroCuarto.setText(cuarto.getAsString(TCuarto.NUMERO));
        tvDetalles.setText(cuarto.getAsString(TCuarto.DETALLES));
        iMenu = R.menu.menu_cuarto_no_alquilado;
        tvDetalles.setText(cuarto.getAsString(TCuarto.DETALLES));
        //AdministradorCamara.setPic(ivPerfil, cuarto.getAsString(TCuarto.URL));
        URIPerfil =  cuarto.getAsString(TCuarto.URL);
        if(URIPerfil == null) return;
        if(URIPerfil.equals("")) return;
        Bitmap bm = BitmapFactory.decodeFile(URIPerfil);
        ivPerfil.setImageBitmap(bm);

        cvMensaje.setVisibility(View.GONE);

    }

    @Override
    public void onClickVerInquilino(View view){
        Intent i = new Intent(this, VerUsuario.class);
        i.putExtra(TUsuario.DNI, Integer.parseInt(tvDni.getText().toString()));
        startActivity(i);
    }

    @Override
    public void onClickTerminarAlquiler(View view) {
        DialogImput imput = new DialogImput();
        imput.showDiaglog(getSupportFragmentManager(),"d", dip);
        dip.setHintView("Motivo");
    }

    @Override
    public void onClickVermas(View view) {
        Intent i = new Intent(this, HistorialUsuarioActivity.class);
        i.putExtra(TUsuario.DNI, tvDni.getText().toString());
        startActivity(i);
    }

    @Override
    public void onClickEditarMensualidad(View view) {
        llEditorMensualidad.setVisibility(View.VISIBLE);
        llShowMensualidad.setVisibility(View.GONE);
        etMensualidad.setText(tvMensualidad.getText().toString());
        etMensualidad.requestFocus();
    }

    @Override
    public void onClickEditarDetalles(View view) {
        llEditorDetalles.setVisibility(View.VISIBLE);
        llShowDetalles.setVisibility(View.GONE);
        etDetalles.setText(tvDetalles.getText().toString());
        etDetalles.requestFocus();
    }

    @Override
    public void onClickConfirMensualidad(View view) {
        llEditorMensualidad.setVisibility(View.GONE);
        llShowMensualidad.setVisibility(View.VISIBLE);
        presenter.actualizarMensualidad(etMensualidad.getText().toString());
    }

    @Override
    public void onClickConfirDetalles(View view) {
        llEditorDetalles.setVisibility(View.GONE);
        llShowDetalles.setVisibility(View.VISIBLE);
        presenter.actualizarDetalles(etDetalles.getText().toString());
    }

    @Override
    public void mostrarPDF (File pdfFile, ContentValues datosUsuario){
        Intent intent = new Intent(this, ViewPdfActivity.class);
        intent.putExtra("path", pdfFile.getAbsolutePath());
        intent.putExtra(TUsuario.NUMERO_TEL, datosUsuario.getAsString(TUsuario.NUMERO_TEL));
        intent.putExtra(TUsuario.CORREO, datosUsuario.getAsString(TUsuario.CORREO));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    @Override
    public void onClickPhoto(View view) {
        Intent intent = new Intent(this, ActivityShowImage.class);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, ivPerfil, ViewCompat.getTransitionName(ivPerfil));


        intent.putExtra(ActivityShowImage.DATA_IMAGE, URIPerfil);
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onClickVerAlquileres(View view) {
        Intent i = new Intent(this, ListAlquileresActivity.class);
        i.putExtra(TCuarto.NUMERO,tvNumeroCuarto.getText().toString());
        startActivity(i);
    }

    @Override
    public void actualizarMensualidad(String mensualidad) {
        tvMensualidad.setText(mensualidad);
    }

    @Override
    public void actualizarDetalles(String detalles) {
        tvDetalles.setText(detalles);
    }

    @Override
    public void onBackPressed() {
        View currentFocus = getCurrentFocus();
        if (currentFocus != null)
        if (currentFocus.equals(etDetalles)){
            llEditorDetalles.setVisibility(View.GONE);
            llShowDetalles.setVisibility(View.VISIBLE);
            return;
        }else{
            if (currentFocus.equals(etMensualidad)){
                llEditorMensualidad.setVisibility(View.GONE);
                llShowMensualidad.setVisibility(View.VISIBLE);
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public void noPago() {
        ivAlert.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_alert_black_24dp));
        llPagoMensual.setOnClickListener(listener);
        findViewById(R.id.tvRealizarPago).setVisibility(View.VISIBLE);
    }

    @Override
    public void pago() {
        findViewById(R.id.tvRealizarPago).setVisibility(View.GONE);
        ivAlert.setImageDrawable(getResources().getDrawable(R.drawable.ic_mood_black_24dp));
        llPagoMensual.setOnClickListener(listener2);
    }

    @Override
    protected void iniciarViews(){
        modificarTransicion();
        tvNumeroCuarto = findViewById(R.id.tvNumeroCuarto);
        tvNombres = findViewById(R.id.tvNombres);
        tvMensualidad = findViewById(R.id.vcTvMensualidad);
        tvDni = findViewById(R.id.tvDni);
        tvDetalles = findViewById(R.id.tvDetalles);
        tvFechaC = findViewById(R.id.tvFechaDePago);

        etDetalles = findViewById(R.id.etDetalles);
        etMensualidad = findViewById(R.id.etMensualidad);

        cvDetallesAlquiler = findViewById(R.id.cvDetallesAlquiler);
        cvMensaje = findViewById(R.id.cvMensaje);

        llBtns = findViewById(R.id.llBtns);
        llEditorDetalles = findViewById(R.id.llEditarDetalles);
        llEditorMensualidad = findViewById(R.id.llEditMensualidad);
        llShowDetalles = findViewById(R.id.llMostrarDetalles);
        llShowMensualidad = findViewById(R.id.llShowMensualidad);
        llPagoMensual = findViewById(R.id.llPagoMensual);

        ivAlert = findViewById(R.id.ivAlert);
        ivPerfil = findViewById(R.id.ivPerfil);
    }
}