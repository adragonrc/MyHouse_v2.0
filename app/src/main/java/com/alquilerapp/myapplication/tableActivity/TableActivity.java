package com.alquilerapp.myapplication.tableActivity;

import androidx.annotation.NonNull;
import android.widget.LinearLayout;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.Base.IBasePresenter;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.ViewMensualidad;
import com.alquilerapp.myapplication.viewForTable.ViewFilaOfPagos;


public class TableActivity extends BaseActivity<IBasePresenter> implements Interfaz.view {
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
    protected IBasePresenter createPresenter() {
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

