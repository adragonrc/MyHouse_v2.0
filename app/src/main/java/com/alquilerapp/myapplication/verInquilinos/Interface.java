package com.alquilerapp.myapplication.verInquilinos;
import android.content.ContentValues;

import com.alquilerapp.myapplication.Base.BaseView;
import com.alquilerapp.myapplication.Base.IBasePresenter;

public interface Interface {
    interface Presenter extends IBasePresenter {
    }
    interface View extends BaseView {
        void agregarFragmento(String idAlquiler);
        void showError();
    }
}
