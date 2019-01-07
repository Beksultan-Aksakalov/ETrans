package com.beksultan.e_trans.ui.stockman;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.model.expeditor.options.City;
import com.beksultan.e_trans.model.stockman.get.WaybillCount;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class StockManPresenter extends MvpPresenter<StockManView> {

    private int page = 1;

    public void getWaybill(String city, boolean isLoadMore) {

        if (isLoadMore) {
            page++;
        } else {
            page = 1;
        }

        App.getInstance().getApiService().getWaybill(city, String.valueOf(page))
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

    public void getCity() {
        App.getInstance().getApiService().getCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<City>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(City city) {
                        getViewState().onSetSenderCity(city.actions.POST.sender_city.choices);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void putWaybill(String id){
        App.getInstance().getApiService().putWaybill(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<WaybillCount>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(WaybillCount waybillCount) {
                        getViewState().onSuccess(waybillCount.waybillList);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
}
