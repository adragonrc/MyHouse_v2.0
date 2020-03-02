package com.alquilerapp.myapplication.viewUser;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alquilerapp.myapplication.R;
import com.alquilerapp.myapplication.tableActivity.TableActivity;
import com.alquilerapp.myapplication.UTILIDADES.TAlquiler;
import com.alquilerapp.myapplication.UTILIDADES.TUsuario;
import com.alquilerapp.myapplication.verUsuario.VerUsuario;
import com.alquilerapp.myapplication.mydialog.DialogImput;
import com.alquilerapp.myapplication.mydialog.DialogInterfaz;
import com.alquilerapp.myapplication.mydialog.DialogOptions;
import com.alquilerapp.myapplication.mydialog.PresenterDialogImput;

public class LinearLayoutUser implements Interfaz.View{
    private LayoutInflater inflater;
    private Context mContext;
    private FragmentManager transaction;

    private ImageView imEstado;

    private TextView tvFecha;
    private TextView tvDni;

    private LinearLayout layout;
    private ImageButton ibMasOp;

    private View view;

    private Interfaz.Presenter presenter;

    private DialogOptions dialogOptions;

    private String idAlquiler;

    DialogInterfaz.DialogOptionPresenter dop = new DialogInterfaz.DialogOptionPresenter() {
        @Override
        public void OnClickOption1() {
            Toast.makeText(mContext, "op1", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void OnClickOption2() {
            Intent i = new Intent(mContext,TableActivity.class);
            i.putExtra(TAlquiler.ID, idAlquiler);
            mContext.startActivity(i);
        }

        @Override
        public void OnClickOption3() {
            DialogImput imput = new DialogImput();
            imput.showDiaglog(transaction, "d", new PresenterDialogImput(mContext, "ALERTA") {
                @Override
                public void positiveButtonListener(@Nullable String s) {
                    presenter.terminarAlquiler();
                }
            });
        }
    };

    public  LinearLayoutUser(final LayoutInflater inflater, Context mContext, FragmentManager transaction) {
        this.inflater = inflater;
        this.mContext = mContext;

        this.transaction = transaction;
    }
    public View createView(String idAlquiler){
        this.idAlquiler = idAlquiler;
        view = inflater.inflate(R.layout.view_user_detalles,null);
        presenter = new Presenter(this, idAlquiler);
        iniciar();
        presenter.iniciar();
        return view;
    }
    private void iniciar(){
     /*   imEstado = View.findViewById(R.id.fivEstado);
        tvDni = View.findViewById(R.id.fTvDNI);
        tvFecha = View.findViewById(R.id.fTvFecha);
        ibMasOp = View.findViewById(R.id.fibMoreOp);
        layout = View.findViewById(R.id.layout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClickAlquiler(View v) {
                layoutOnClick();
            }
        });
        ibMasOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClickAlquiler(View v) {
                onClickMoreOptions();
            }
        });
        dialogOptions = new DialogOptions();
        */
    }
    public void layoutOnClick(){
        Intent i = new Intent(mContext, VerUsuario.class);
        i.putExtra(TUsuario.DNI,Integer.parseInt(tvDni.getText().toString()));
        mContext.startActivity(i);
    }
    public void onClickMoreOptions(){
        dialogOptions.showDiaglog(transaction, "option", dop);
    }
    public void doPago(){
        imEstado.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_mood_black_24dp));
    }
    public void doNoPago(){
        imEstado.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_mood_bad_black_24dp));
    }
    public void setAttributes(String dni, String fecha){
        tvFecha.setText(fecha);
        tvDni.setText(dni);
    }
       public Context getContext(){
        return mContext;
    }
}
