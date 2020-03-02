package com.alquilerapp.myapplication.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alquilerapp.myapplication.AlquilerUsuario.Item;
import com.alquilerapp.myapplication.R;

import java.util.ArrayList;

public class RecyclercViewAdapter extends RecyclerView.Adapter<RecyclercViewAdapter.Holder> {
    private Context mContext;
    private ArrayList<Item> list;
    private View.OnClickListener onClickItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        }
    };

    public void setOnClickItemListener(View.OnClickListener onClickItemListener) {
        this.onClickItemListener = onClickItemListener;
    }

    public RecyclercViewAdapter(Context mContext, ArrayList<Item> list){
        this.mContext = mContext;
        this.list = list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.alquiler_description,viewGroup,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        Item item  = list.get(i);
        String title = item.getIdAlquiler();
        holder.tvAlquiler.setText(title);
        holder.tvMotivoSalida.setText(item.getMotivoDeSalida());
    }
    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Holder extends RecyclerView.ViewHolder{
        private TextView tvAlquiler;
        private TextView tvMotivoSalida;
        public Holder(@NonNull View itemView) {
            super(itemView);
            tvAlquiler = itemView.findViewById(R.id.tvAlquiler);
            tvMotivoSalida = itemView.findViewById(R.id.tvMotivoSalida);
            itemView.setOnClickListener(onClickItemListener);
        }
    }
}
