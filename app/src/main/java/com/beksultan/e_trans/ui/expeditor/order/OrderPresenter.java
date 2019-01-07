package com.beksultan.e_trans.ui.expeditor.order;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.model.expeditor.get.OrderCount;
import com.beksultan.e_trans.model.expeditor.put.StatusParam;
import com.beksultan.e_trans.model.stockman.get.WaybillCount;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class OrderPresenter extends MvpPresenter<OrderView> {

    private int page = 1;

    public void getOrder(boolean isLoadMore) {

        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }

        App.getInstance().getApiService().getOrder(String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> showLoadingIndicator(true, isLoadMore))
                .doAfterTerminate(() -> showLoadingIndicator(false, isLoadMore))
                .subscribe(new SingleObserver<OrderCount>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(OrderCount orderCount) {
                        if (orderCount != null) {
                            if (isLoadMore) {
                                getViewState().addOrderList(orderCount.orderList);
                            } else {
                                getViewState().onSetOrderList(orderCount.orderList);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });
    }

    private void showLoadingIndicator(boolean isShow, boolean isLoadMore) {
        if (isLoadMore) {
            getViewState().onShowProgressBarLoadMore(isShow);
        } else {
            getViewState().onShowProgressBar(isShow);
        }
    }

    public void putOrderOrStock(String id, StatusParam status) {
        App.getInstance().getApiService().putOrder(id, status)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OrderCount>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(OrderCount orderCount) {
                        getViewState().onSuccess(orderCount.orderList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onError(e.getMessage());
                    }
                });

    }

}
