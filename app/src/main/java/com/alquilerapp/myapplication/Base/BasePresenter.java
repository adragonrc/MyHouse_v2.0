package com.alquilerapp.myapplication.Base;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.alquilerapp.myapplication.DataBaseAdmin;

public abstract class BasePresenter<V extends BaseView> implements IBasePresenter{
    private V mMvpView;
    protected V view;
    protected DataBaseAdmin db;
    protected SharedPreferences sp;
    public BasePresenter(V view){
        this.view = view;
        db = new DataBaseAdmin(view.getContext(),null,1);
        db.getWritableDatabase();
        sp = PreferenceManager.getDefaultSharedPreferences(view.getContext());
    }

    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }

    public void detachView() {
        mMvpView = null;
    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

}
