package com.alquilerapp.myapplication.HistorialCasa;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.alquilerapp.myapplication.Adapters.RvAdapterAlquiler;
import com.alquilerapp.myapplication.Adapters.RvAdapterUser;
import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.historialUserPakage.HistorialUsuarioActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.tableActivity.TableActivity;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.mi_casa.Models.ModelAlquilerView;
import com.alquilerapp.myapplication.mi_casa.Models.ModelUserView;

import java.util.ArrayList;

public class HistorialCasaActivity extends BaseActivity<Interface.Presenter> implements Interface.View, RvAdapterUser.Interface, RvAdapterAlquiler.Interface{
    private RecyclerView recyclerView;
    private RvAdapterUser adapterUser;
    private RvAdapterAlquiler adapterAlquiler;
    private RecyclerView.LayoutManager manager;

    private int menu;
    @Override
    protected void iniciarComandos() {
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        menu = R.menu.menu_historial_mi_casa;
    }
    @NonNull
    @Override
    protected Interface.Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mi_casa;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(this.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.iVerAlquileres:{
                presenter.mostrarAlquileres();
                break;
            }
            case R.id.iVerUsuario:{
                presenter.mostrarUsuarios();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void iniciarViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    public void mostarListUsuarios(ArrayList<ModelUserView> list) {
        adapterUser = new RvAdapterUser(this, list);
        recyclerView.setAdapter(adapterUser);
    }

    @Override
    public void mostarListAlquileres(ArrayList<ModelAlquilerView> list) {
        adapterAlquiler = new RvAdapterAlquiler(this, list);
        recyclerView.setAdapter(adapterAlquiler);

    }
    @Override
    public void onClickUsuario(View view) {
        String dni = ((TextView) view).getText().toString();
        Intent intent = new Intent(this, HistorialUsuarioActivity.class);
        intent.putExtra(TUsuario.DNI,dni);
        startActivity(intent);
    }

    @Override
    public void onClickAlquiler(String id) {
        Intent intent = new Intent(this, TableActivity.class);
        intent.putExtra(TAlquiler.ID, id);
        startActivity(intent);
    }
}
