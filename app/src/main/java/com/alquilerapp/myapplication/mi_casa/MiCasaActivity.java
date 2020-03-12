package com.alquilerapp.myapplication.mi_casa;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.tableActivity.TableActivity;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TCuarto;
import com.alquilerapp.myapplication.Adapters.RvAdapterCuartos;
import com.alquilerapp.myapplication.VerCuarto.VerCuartoActivity;
import com.alquilerapp.myapplication.mi_casa.Models.ModelCuartoView;
import com.alquilerapp.myapplication.mydialog.DialogImput;
import com.alquilerapp.myapplication.mydialog.DialogInterfaz;
import com.alquilerapp.myapplication.mydialog.DialogOptions;
import com.alquilerapp.myapplication.mydialog.PresenterDialogImput;

import java.util.ArrayList;

public class MiCasaActivity extends BaseActivity<Interface.Presenter> implements Interface.View, RvAdapterCuartos.Interface{
    private RecyclerView rv;
    private DialogOptions dop;
    private DialogInterfaz.DialogOptionPresenter dialogOptionPresenter;
    private DialogImput imput ;
    private DialogInterfaz.DialogImputPresenter dialogImputPresenter;
    private RvAdapterCuartos adapterCuartos;
    private RecyclerView.LayoutManager manager;
    private byte opcion;
    @Override
    protected void iniciarComandos() {
        opcion = 0;
        dop = new DialogOptions();
        imput = new DialogImput();
        dialogOptionPresenter = new PresenterDialogOptions(dop);
        dialogImputPresenter =  new PresenterDialogImput(getContext(), "ALERTA") {
            @Override
            public void positiveButtonListener(@Nullable String s) {
                presenter.terminarAlquiler(s, imput.getTag());

            }
        };
        manager = new LinearLayoutManager(this);
        rv.setLayoutManager(manager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (opcion){
            case 0:{
                presenter.verTodos();
                break;
            }
            case 1:{
                presenter.verCuartosAlquilados();
                break;
            }
            case 2:{
                presenter.verCuartosLibres();
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mi_casa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.iTodos:{
                opcion = 0;
                presenter.verTodos();
                break;
            }
            case R.id.iCuartosAlquilados:{
                opcion = 1;
                presenter.verCuartosAlquilados();
                break;
            }
            case R.id.iCuartosLibres:{
                opcion = 2;
                presenter.verCuartosLibres();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_mi_casa;
    }

    @NonNull
    @Override
    protected Interface.Presenter createPresenter() {
        return new Presenter(this);
    }

    @Override
    protected void iniciarViews() {
        rv = findViewById(R.id.recyclerView);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        /*if (adapterUsuarios.getViewSelect().equals(item.getActionView())){
            Toast.makeText(this, "Usar getAction", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Usar getAction", Toast.LENGTH_SHORT).show();
        }
        int id = item.getItemId();
        switch (id){
            case R.id.opVerUsuario: {
                break;
            }
            case R.id.opVerPagos: {
                Intent i = new Intent(getContext(), TableActivity.class);
                i.putExtra(TAlquiler.ID, adapterUsuarios.getDniSelect());
                getContext().startActivity(i);
                break;
            }
            case R.id.opTerminarA: {
                showDialogImput(adapterUsuarios.getDniSelect());
                break;
            }
            default:
                Toast.makeText(this, "Caso no encontrado", Toast.LENGTH_SHORT).show();
        }
        */
        return super.onContextItemSelected(item);
    }

    @Override
    public void mostratCuartos(ArrayList<ModelCuartoView> items) {
        adapterCuartos = new RvAdapterCuartos(this, items);
        rv.setAdapter(adapterCuartos);
    }

    /*

        @Override
        public void mostrarUsuarios(ArrayList<ModelUserView> items) {
            adapterUsuarios = new RvAdapterUser(this, items);
            rv.setAdapter(adapterUsuarios);
            //registerForCoxtMenu(rv);
        }

        @Override
        public void mostrarAlquileres(ArrayList<ModelAlquilerView> alquilerViews) {
            adapterAlquiler = new RvAdapterAlquiler(this, alquilerViews);
            rv.setAdapter(adapterAlquiler);
            // registerForContextMenu(rv);
        }

        @Override
        public void onClickAlquiler(View view) {
            int dni  =Integer.valueOf(((TextView) view).getText().toString());
            Intent i = new Intent(this,VerUsuario.class);
            i.putExtra(TUsuario.DNI, dni);
            startActivity(i);
        }
        */
    @Override
    public void showDialogOptions(String idAlquiler) {
        dop.showDiaglog(getSupportFragmentManager(), idAlquiler, dialogOptionPresenter);
    }
    public void showDialogImput(final String idAlquiler){
        imput = new DialogImput();
        imput.showDiaglog(getSupportFragmentManager(), idAlquiler,dialogImputPresenter);
    }

    @Override
    public void onClickCuarto(View view) {
        String numero = ((TextView)view).getText().toString();
        Intent i = new Intent(this, VerCuartoActivity.class);
        i.putExtra(TCuarto.NUMERO, numero);
        startActivity(i);
    }

    private class PresenterDialogOptions implements DialogInterfaz.DialogOptionPresenter {
        private DialogOptions dop;
        private PresenterDialogOptions(DialogOptions dop){
            this.dop = dop;
        }
        @Override
        public void OnClickOption1() {
            Toast.makeText(getContext(), "Op1", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void OnClickOption2() {
            Intent i = new Intent(getContext(), TableActivity.class);
            i.putExtra(TAlquiler.ID, dop.getTag());
            getContext().startActivity(i);
        }

        @Override
        public void OnClickOption3() {
            showDialogImput(dop.getTag());
        }
    }
}
