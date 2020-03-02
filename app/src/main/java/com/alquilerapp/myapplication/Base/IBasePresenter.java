package com.alquilerapp.myapplication.Base;

import java.text.ParseException;

public interface IBasePresenter<T extends BaseView>{
    void iniciarComandos() throws ParseException;
    void attachView(T mvpView);
    void detachView();
}
