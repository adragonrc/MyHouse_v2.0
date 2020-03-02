package com.alquilerapp.myapplication.verInquilinos;
import android.content.ContentValues;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

public interface Interface {
    interface Presenter<V extends BaseView> extends IBasePresenter<V> {
        void agregarFrag();
    }
    interface View extends BaseView {
        void agregarFragmento(String idAlquiler);
        void showError();
    }
}
