package com.beksultan.e_trans.ui.stockman;

import com.arellomobile.mvp.MvpView;
import com.beksultan.e_trans.model.expeditor.options.Choice;
import com.beksultan.e_trans.model.stockman.get.Waybill;

import java.util.ArrayList;
import java.util.List;

public interface StockManView extends MvpView {

    void onSetSenderCity(List<Choice> choiceList);

    void onSetWaybillList(ArrayList<Waybill> waybillArrayList);

    void addWaybillList(ArrayList<Waybill> waybillArrayList);

    void onError(String message);

    void onShowProgressBar(boolean isShow);

    void onShowProgressBarLoadMore(boolean isShow);

    void onSuccess(ArrayList<Waybill> orderList);
}
