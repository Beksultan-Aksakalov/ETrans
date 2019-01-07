package com.beksultan.e_trans.ui.expeditor.order;

import com.arellomobile.mvp.MvpView;
import com.beksultan.e_trans.model.expeditor.get.Order;

import java.util.ArrayList;

public interface OrderView extends MvpView {

    void onSetOrderList(ArrayList<Order> orderList);

    void addOrderList(ArrayList<Order> orderList);

    void onError(String message);

    void onShowProgressBar(boolean isShow);

    void onShowProgressBarLoadMore(boolean isShow);

    void onSuccess(ArrayList<Order> orderList);
}
