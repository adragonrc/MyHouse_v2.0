package com.alquilerapp.myapplication.verUsuario;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.UTILIDADES.Mensualidad;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.mydialog.DialogConfirm;
import com.alquilerapp.myapplication.mydialog.DialogImput;
import com.alquilerapp.myapplication.mydialog.DialogInterfaz;
import com.alquilerapp.myapplication.mydialog.PresenterDialogImput;
import com.alquilerapp.myapplication.verUsuario.Interfaz;
import com.alquilerapp.myapplication.verUsuario.Presentador;

public class VerUsuario extends BaseActivity<Presentador> implements Interfaz.View{
    private TextView tvDni;
    private TextView tvNombres;
    private TextView tvApellidoPat;
    private TextView tvApellidoMat;
    private TextView tvCuarto;
    private TextView tvFecha;
    private TextView tvFechaDePago;
    private TextView tvMonto;
    private ImageView imEstado;

    private DialogImput myDialog;
    private DialogConfirm dialogConfirm;

    private Button cancelar;

    private DialogInterfaz.DialogImputPresenter dip;


    private DialogInterfaz.DialogConfirmPresenter dcp;
    {
        dcp = new DialogInterfaz.DialogConfirmPresenter() {
            @Override
            public void positiveButtonListener() {
                presenter.crearPago();
            }
        };
    }

    @Override
    protected void iniciarComandos() {
        dip = new PresenterDialogImput(getContext(),"Cambiar") {
            @Override
            public void positiveButtonListener(@Nullable String s) {
                presenter.positive(s, myDialog.getTag());
            }
        };
        myDialog = new DialogImput();
        dialogConfirm = new DialogConfirm();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_ver_alquiler;
    }

    @NonNull
    @Override
    protected Presentador createPresenter() {
        return new Presentador(this, getIntent().getIntExtra(TUsuario.DNI,0));
    }

    @Override
    public void modoEdicion(){
        tvDni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dip.setImputType(EditorInfo.TYPE_CLASS_NUMBER);
                myDialog.showDiaglog(getSupportFragmentManager(),TUsuario.DNI, dip);
            }
        });
        tvNombres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dip.setImputType(EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES);
                myDialog.showDiaglog(getSupportFragmentManager(),TUsuario.NOMBRES, dip);
            }
        });
        tvApellidoPat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dip.setImputType(EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES);
                myDialog.showDiaglog(getSupportFragmentManager(),TUsuario.APELLIDO_PAT, dip);
            }
        });

        tvApellidoMat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dip.setImputType(EditorInfo.TYPE_TEXT_FLAG_CAP_SENTENCES);
                myDialog.showDiaglog(getSupportFragmentManager(),TUsuario.APELLIDO_MAT, dip);
            }
        });
        tvMonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dip.setImputType(EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
                myDialog.showDiaglog(getSupportFragmentManager(), Mensualidad.COSTO, dip);
            }
        });
        tvCuarto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dip.setImputType(EditorInfo.TYPE_CLASS_NUMBER);
                myDialog.showDiaglog(getSupportFragmentManager(), TCuarto.NUMERO, dip);
            }
        });
    }
    @Override
    public void onClickPagarMensualidad(View view){
        dialogConfirm.showDiaglog(getSupportFragmentManager(),null,dcp);
    }
    @Override
    public void showNoPago(){
        imEstado.setImageDrawable(getResources().getDrawable(R.drawable.ic_mood_bad_black_24dp));
    }

    @Override
    public void showPago(){
        imEstado.setImageDrawable(getResources().getDrawable(R.drawable.ic_mood_black_24dp));
        cancelar.setVisibility(View.GONE);
    }

    @Override
    public void setTextOfTV(ContentValues du, ContentValues dC, ContentValues dA, ContentValues dM) {
        tvDni.setText(du.getAsString(TUsuario.DNI));
        tvNombres.setText(du.getAsString(TUsuario.NOMBRES));
        tvApellidoPat.setText(du.getAsString(TUsuario.APELLIDO_PAT));
        tvApellidoMat.setText(du.getAsString(TUsuario.APELLIDO_MAT));
        tvCuarto.setText(dC.getAsString(TCuarto.NUMERO));
        tvFecha.setText(dA.getAsString(TAlquiler.FECHA));
        tvFechaDePago.setText(dA.getAsString(TAlquiler.FECHA_C));
        tvMonto.setText(dM.getAsString(Mensualidad.COSTO));
    }

    protected void iniciarViews() {
        tvDni = findViewById(R.id.tvDNI);
        tvNombres = findViewById(R.id.tvNombre);
        tvApellidoPat = findViewById(R.id.tvApellidoPat);
        tvApellidoMat = findViewById(R.id.tvApellidoMat);
        tvCuarto = findViewById(R.id.tvCuarto);
        tvFecha = findViewById(R.id.tvFecha1);
        tvFechaDePago = findViewById(R.id.tvFechaDePago);
        tvMonto = findViewById(R.id.tvPago);
        imEstado = findViewById(R.id.vuIvEstado);
        cancelar = findViewById(R.id.vuBtCacelar);
    }

}
