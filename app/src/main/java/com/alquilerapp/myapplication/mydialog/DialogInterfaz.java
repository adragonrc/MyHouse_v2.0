package com.alquilerapp.myapplication.mydialog;

import androidx.annotation.Nullable;

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
