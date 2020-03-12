package com.alquilerapp.myapplication.viewForTable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alquilerapp.myapplication.BaseView.BaseView;
import com.alquilerapp.myapplication.BaseView.MvBasePresenter;
import com.alquilerapp.myapplication.R;

public class ViewFilaOfPagos extends BaseView {
    TextView tvid;
    TextView tvfecha;
    TextView tvMonto;

    public ViewFilaOfPagos(LayoutInflater inflater, Context mContext,  ViewGroup vg) {
        super(inflater, mContext, null, vg);
    }

    @Override
    protected MvBasePresenter getPresenter() {
        return null;
    }

    @Override
    public View createView(String... attrs) {
        tvid.setText(attrs[0]);
        tvfecha.setText(attrs[1]);
        tvMonto.setText(attrs[2]);
        return view;
    }

    @Override
    protected void iniciar() {
        tvid = view.findViewById(R.id.tvId);
        tvfecha = view.findViewById(R.id.tvFecha);
        tvMonto = view.findViewById(R.id.tvMonto);
    }

    @Override
    protected int getLayout() {
        return R.layout.view_fila_of_pagos;
    }
}
