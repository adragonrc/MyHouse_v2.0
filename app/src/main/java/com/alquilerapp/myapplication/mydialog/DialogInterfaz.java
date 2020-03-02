package com.alquilerapp.myapplication.mydialog;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.View;

public interface DialogInterfaz {
    interface DialogBaseView{
        String getTitle();
        String getMensaje();
        View getmView();
    }
    interface DialogConfirmPresenter{
        void positiveButtonListener();
    }
    interface DialogImputPresenter extends DialogBaseView{
        int getImputType();
        void setImputType(int imputTipe);
        void setHintView(String hint);
        String getHint();
        void positiveButtonListener(@Nullable String s);

    }
    interface DialogOptionPresenter {
        void OnClickOption1();
        void OnClickOption2();
        void OnClickOption3();
    }
}
