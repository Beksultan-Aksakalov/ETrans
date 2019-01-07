package com.beksultan.e_trans.ui.expeditor.order.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.model.expeditor.get.OrderCount;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class OrderHistoryPresenter extends MvpPresenter<OrderHistoryView>{

    private int page = 1;

    public void getOrderHistory(boolean isLoadMore) {

        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }

        App.getInstance().getApiService().getOrderHistory(String.valueOf(page))
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

}
