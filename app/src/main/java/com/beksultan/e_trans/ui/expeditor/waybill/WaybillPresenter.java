package com.beksultan.e_trans.ui.expeditor.waybill;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.model.expeditor.options.City;
import com.beksultan.e_trans.model.expeditor.send.OrderParam;
import com.beksultan.e_trans.model.expeditor.send.OrderParamReturn;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@InjectViewState
public class WaybillPresenter extends MvpPresenter<WaybillView> {

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
                        getViewState().onSetReceiverCity(city.actions.POST.receiver_city.choices);
                        getViewState().onSetWeightType(city.actions.POST.weight_type.choices);
                        getViewState().onSetPayer(city.actions.POST.payer.choices);
                        getViewState().onSetPayType(city.actions.POST.pay_type.choices);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void setOrder(OrderParam param) {
        App.getInstance().getApiService().postWaybill(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<OrderParamReturn>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(OrderParamReturn orderParamReturn) {
                        getViewState().onSuccess("Успешно !");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    public void getKrizh(String receiver_city, String sender_city) {
        App.getInstance().getApiService().getKrizh(receiver_city, sender_city)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String request = response.body();

                        if (response.code() == 200) {
                            if (request != null && !request.trim().isEmpty()) {
                                getViewState().onSuccessKrizh(request);
                            } else {
                                getViewState().onErrorKrizh("По этому направлению до этого не было крыжов !");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        getViewState().onErrorKrizh("По этому направлению до этого не было крыжов !");
                    }
                });
    }
}
