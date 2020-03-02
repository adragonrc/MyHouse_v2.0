package com.alquilerapp.myapplication.tableActivity;

import android.support.annotation.NonNull;
import android.widget.LinearLayout;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.ViewMensualidad;
import com.alquilerapp.myapplication.tableActivity.Interfaz;
import com.alquilerapp.myapplication.tableActivity.Presenter;
import com.alquilerapp.myapplication.viewForTable.ViewFilaOfPagos;


public class TableActivity extends BaseActivity<Presenter> implements Interfaz.view {
    private LinearLayout llPagos;

    @Override
    protected void iniciarComandos() {

    }

    @Override
    protected int getLayout() {
        return R.layout.activity_table;
    }

    @NonNull
    @Override
    protected Presenter createPresenter() {
        return new Presenter(this,getIntent().getStringExtra(TAlquiler.ID));
    }

    @Override
    protected void iniciarViews() {
        llPagos = findViewById(R.id.llPagos);
    }

    @Override
    public void addRow(String ...atts) {
        ViewFilaOfPagos vfp = new ViewFilaOfPagos(getLayoutInflater(),this, null);
        llPagos.addView(vfp.createView(atts));
    }

    @Override
    public void addTitleMensualidad(String s, String s1) {
        ViewMensualidad mtv = new ViewMensualidad(getLayoutInflater(),this,null);
        llPagos.addView(mtv.createView(s, s1));
    }
}

