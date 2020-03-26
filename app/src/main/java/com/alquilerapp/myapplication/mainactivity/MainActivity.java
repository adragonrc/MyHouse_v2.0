package com.alquilerapp.myapplication.mainactivity;

import android.content.Intent;
import androidx.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.alquilerapp.myapplication.Base.BaseActivity;
import com.alquilerapp.myapplication.registrarcasa.RegistrarCasaActivity;
import com.alquilerapp.myapplication.menuPrincipal.MenuPricipal;
import com.alquilerapp.myapplication.R;

public class MainActivity extends BaseActivity<Interface.presenter> implements Interface.view {
    private EditText etUser;
    private EditText etPass;
    @Override
    protected void iniciarComandos() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        etUser.setText(presenter.getUser());
        etPass.setText("");
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @NonNull
    @Override
    protected Interface.presenter createPresenter() {
        return new Presenter(this);
    }

    protected void iniciarViews(){
        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
    }

    public void onClickIngresar(View view) {
        presenter.ingresar(etUser.getText().toString(), etPass.getText().toString());
    }
    public void onClikCambiarContraseña(View view){
        startActivity(new Intent(this, RegistrarCasaActivity.class));
    }

    @Override
    public void ingresar() {
        startActivity(new Intent(this, MenuPricipal.class));
    }

    @Override
    public void negarIngreso() {
    }

    @Override
    public void setID(String s){
        etUser.setText(s);
    }
}

/*
public class MusicIntentReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG))
        {
            int state = intent.getIntExtra("state", -1);
            switch (state)
            {
                case 0:
                    Utilidades.mostrarToastText(context, "Auricular conectado");
                    break;
                case 1:
                    Utilidades.mostrarToastText(context, "Auricular desconectado");
                    break;
                default:
                    Utilidades.mostrarToastText(context, "Estado desconocido");
                    break;
            }
        }
    }
}*/