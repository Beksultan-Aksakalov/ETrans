package com.beksultan.e_trans.ui.expeditor.order.history;

import com.arellomobile.mvp.MvpView;
import com.beksultan.e_trans.model.expeditor.get.Order;

import java.util.ArrayList;

public interface OrderHistoryView extends MvpView {

    void onSetOrderList(ArrayList<Order> orderList);

    void addOrderList(ArrayList<Order> orderList);

    void onError(String message);

    void onShowProgressBar(boolean isShow);

    void onShowProgressBarLoadMore(boolean isShow);

}
