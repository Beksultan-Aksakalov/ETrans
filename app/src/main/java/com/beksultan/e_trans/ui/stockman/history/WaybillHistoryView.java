package com.beksultan.e_trans.ui.stockman.history;

import com.arellomobile.mvp.MvpView;
import com.beksultan.e_trans.model.stockman.get.Waybill;

import java.util.ArrayList;

public interface WaybillHistoryView extends MvpView {

    void onSetWaybillList(ArrayList<Waybill> waybillArrayList);

    void addWaybillList(ArrayList<Waybill> waybillArrayList);

    void onError(String message);

    void onShowProgressBar(boolean isShow);

    void onShowProgressBarLoadMore(boolean isShow);

}
