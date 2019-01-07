package com.beksultan.e_trans.ui.authorization;

import com.arellomobile.mvp.MvpView;

public interface AuthorizationView extends MvpView{

    void onOpenUI(String role);

    void onFailedApiService(String error);
}
