package com.alquilerapp.myapplication.listalquileres;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alquilerapp.myapplication.Adapters.RvAdapterAlquiler;
import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.Base.IBasePresenter;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.tableActivity.TableActivity;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;

import java.util.ArrayList;

public class ListAlquileresActivity extends BaseActivity<IBasePresenter> implements Interface.Vista, RvAdapterAlquiler.Interface{
    private RvAdapterAlquiler adapterAlquiler;
    private RecyclerView recyclerView;

    @Override
    protected void iniciarComandos() {
        setTitle("Lista Alquileres");
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_list_alquileres;
    }

    @NonNull
    @Override
    protected IBasePresenter createPresenter() {
        return new Presentador(this, getIntent().getStringExtra(TCuarto.NUMERO));
    }

    @Override
    protected void iniciarViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void mostarListAlquileres(ArrayList<ModelAlquilerView> list) {
        adapterAlquiler = new RvAdapterAlquiler(this, list);
        recyclerView.setAdapter(adapterAlquiler);
    }

    @Override
    public void onClickAlquiler(String id) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(TAlquiler.ID, id);
        startActivity(intent);
    }
}
