package com.beksultan.e_trans.ui.expeditor.waybill;

import com.arellomobile.mvp.MvpView;
import com.beksultan.e_trans.model.expeditor.options.Choice;

import java.util.List;

public interface WaybillView extends MvpView {

    void onSetSenderCity(List<Choice> choiceList);

    void onSetReceiverCity(List<Choice> choiceList);

    void onSetWeightType(List<Choice> choiceList);

    void onSetPayer(List<Choice> choiceList);

    void onSetPayType(List<Choice> choiceList);

    void onSuccess(String message);

    void onSuccessKrizh(String message);

    void onError(String message);

    void onErrorKrizh(String message);
}
