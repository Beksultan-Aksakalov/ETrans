package com.beksultan.e_trans.ui.stockman.history;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.model.stockman.get.WaybillCount;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class WaybillHistoryPresenter extends MvpPresenter<WaybillHistoryView> {

    private int page = 1;

    public void getWaybillHistory(boolean isLoadMore) {

        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }

        App.getInstance().getApiService().getWaybillHistory(String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((v) -> showLoadingIndicator(true, isLoadMore))
                .doAfterTerminate(() -> showLoadingIndicator(false, isLoadMore))
                .subscribe(new SingleObserver<WaybillCount>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(WaybillCount waybillCount) {
                        if (isLoadMore) {
                            getViewState().addWaybillList(waybillCount.waybillList);
                        } else {
                            getViewState().onSetWaybillList(waybillCount.waybillList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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
