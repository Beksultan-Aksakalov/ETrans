package com.beksultan.e_trans.ui.expeditor.waybill;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.model.expeditor.options.Choice;
import com.beksultan.e_trans.model.expeditor.send.OrderParam;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaybillActivity extends MvpAppCompatActivity implements WaybillView {

    private String senderCity_id;

    private String receiverCity_id;

    private String weightType_id;

    private String payer_id;

    private String payType_id;

    @InjectPresenter
    WaybillPresenter mvpPresenter;

    @BindView(R.id.ll_layout)
    LinearLayout ll_layout;

    @BindView(R.id.edt_sender_fullName)
    EditText edt_sender_fullName;

    @BindView(R.id.edt_sender_address)
    EditText edt_sender_address;

    @BindView(R.id.edt_sender_phone)
    EditText edt_sender_phone;

    @BindView(R.id.edt_seat_count)
    EditText edt_seat_count;

    @BindView(R.id.edt_sender_company)
    EditText edt_sender_company;

    @BindView(R.id.edt_receiver_fullName)
    EditText edt_receiver_fullName;

    @BindView(R.id.edt_receiver_company)
    EditText edt_receiver_company;

    @BindView(R.id.edt_receiver_address)
    EditText edt_receiver_address;

    @BindView(R.id.edt_receiver_phone)
    EditText edt_receiver_phone;

    @BindView(R.id.edt_waybill_number)
    EditText edt_waybill_number;

    @BindView(R.id.edt_cost)
    EditText edt_cost;

    @BindView(R.id.edt_weight)
    EditText edt_weight;

    @BindView(R.id.txt_krizh)
    TextView txt_krizh;

    @BindView(R.id.edt_krizh)
    EditText edt_krizh;

    @BindView(R.id.btn_send)
    Button btn_send;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.spinner_sender_city)
    Spinner spinner_sender_city;

    @BindView(R.id.spinner_receiver_city)
    Spinner spinner_receiver_city;

    @BindView(R.id.spinner_weight_type)
    Spinner spinner_weight_type;

    @BindView(R.id.spinner_payer)
    Spinner spinner_payer;

    @BindView(R.id.spinner_pay_type)
    Spinner spinner_pay_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybill);
        ButterKnife.bind(this);
        mvpPresenter.getCity();
    }

    @OnClick(R.id.img_back)
    public void onClickBack() {
        finish();
    }

    @Override
    public void onSetSenderCity(List<Choice> senderCityList) {
        senderCityList.add(0, new Choice("Город отправителя", null));
        ArrayList<String> cityName = new ArrayList<>();
        for (Choice item : senderCityList) {
            cityName.add(item.display_name);
        }

        ArrayAdapter<String> regionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityName);
        regionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_sender_city.setAdapter(regionArrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                senderCity_id = senderCityList.get(position).value;
                getCurrentKrizh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_sender_city.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public void onSetReceiverCity(List<Choice> receiverCityList) {
        receiverCityList.add(0, new Choice("Город получателя", null));
        ArrayList<String> cityName = new ArrayList<>();
        for (Choice item : receiverCityList) {
            cityName.add(item.display_name);
        }

        ArrayAdapter<String> regionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityName);
        regionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_receiver_city.setAdapter(regionArrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                receiverCity_id = receiverCityList.get(position).value;
                getCurrentKrizh();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_receiver_city.setOnItemSelectedListener(itemSelectedListener);
    }

    public void getCurrentKrizh() {
        if ((senderCity_id != null && !senderCity_id.trim().isEmpty()) && (receiverCity_id != null && !receiverCity_id.trim().isEmpty())) {
            mvpPresenter.getKrizh(receiverCity_id, senderCity_id);
            txt_krizh.setText(null);
        } else {
            txt_krizh.setText(null);
            Snackbar.make(ll_layout, "Выберите город отправителя и получателя если хотите получить заново крыж !", Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSetWeightType(List<Choice> weightTypeList) {
        weightTypeList.add(0, new Choice("Тип веса", null));
        ArrayList<String> weightTypeName = new ArrayList<>();
        for (Choice item : weightTypeList) {
            weightTypeName.add(item.display_name);
        }

        ArrayAdapter<String> regionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, weightTypeName);
        regionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_weight_type.setAdapter(regionArrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                weightType_id = weightTypeList.get(position).value;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_weight_type.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public void onSetPayer(List<Choice> payerList) {
        payerList.add(0, new Choice("Кто оплачивает", null));
        ArrayList<String> payerName = new ArrayList<>();
        for (Choice item : payerList) {
            payerName.add(item.display_name);
        }

        ArrayAdapter<String> regionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payerName);
        regionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_payer.setAdapter(regionArrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payer_id = payerList.get(position).value;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_payer.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public void onSetPayType(List<Choice> payTypeList) {
        payTypeList.add(0, new Choice("Способ оплаты", null));
        ArrayList<String> payTypeName = new ArrayList<>();
        for (Choice item : payTypeList) {
            payTypeName.add(item.display_name);
        }

        ArrayAdapter<String> regionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payTypeName);
        regionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_pay_type.setAdapter(regionArrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payType_id = payTypeList.get(position).value;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_pay_type.setOnItemSelectedListener(itemSelectedListener);
    }

    @OnClick(R.id.btn_send)
    public void onClickSend() {
        OrderParam param = new OrderParam(senderCity_id,
                receiverCity_id,
                edt_sender_fullName.getText().toString().trim(),
                edt_sender_address.getText().toString().trim(),
                edt_sender_phone.getText().toString().trim(),
                edt_seat_count.getText().toString().trim(),
                edt_sender_company.getText().toString().trim(),
                edt_receiver_address.getText().toString().trim(),
                edt_receiver_phone.getText().toString().trim(),
                edt_receiver_fullName.getText().toString().trim(),
                edt_receiver_company.getText().toString().trim(),
                edt_waybill_number.getText().toString().trim(),
                edt_cost.getText().toString().trim(),
                edt_weight.getText().toString().trim(),
                weightType_id,
                edt_krizh.getText().toString().trim(),
                payer_id,
                payType_id);

        if (param.isValidSenderFullName()
                && param.isValidSenderAddress()
                && param.isValidSenderPhone()
                && param.isValidSenderCompany()
                && param.isValidSenderCity()
                && param.isValidReceiverFullName()
                && param.isValidReceiverCity()
                && param.isValidReceiverAddress()
                && param.isValidReceiverCompany()
                && param.isValidReceiverPhone()
                && param.isValidWaybilNumber()
                && param.isValidCost()
                && param.isValidSeatCount()
                && param.isValidWeight()
                && param.isValidWeightType()
                && param.isValidKrizh()
                && param.isValidPayer()
                && param.isValidPayType()) {

            mvpPresenter.setOrder(param);

        } else {
            if (!param.isValidSenderFullName()) {
                edt_sender_fullName.setError("Please, enter your full name again!");
            }
            if (!param.isValidSenderAddress()) {
                edt_sender_address.setError("Please, enter your sender address again!");
            }
            if (!param.isValidSenderPhone()) {
                edt_sender_phone.setError("Please, enter your sender phone again!");
            }
            if (!param.isValidSenderCompany()) {
                edt_sender_company.setError("Please, enter your company name again!");
            }
            if (!param.isValidReceiverFullName()) {
                edt_receiver_fullName.setError("Please, enter your full name again!");
            }
            if (!param.isValidReceiverAddress()) {
                edt_receiver_address.setError("Please, enter your receiver address again!");
            }
            if (!param.isValidReceiverCompany()) {
                edt_receiver_company.setError("Please, enter your receiver company name!");
            }
            if (!param.isValidReceiverPhone()) {
                edt_receiver_phone.setError("Please, enter your receiver phone again!");
            }
            if (!param.isValidWaybilNumber()) {
                edt_waybill_number.setError("Please, enter your waybill number again!");
            }
            if (!param.isValidCost()) {
                edt_cost.setError("Please, enter your cost again!");
            }
            if (!param.isValidSeatCount()) {
                edt_seat_count.setError("Please, enter your seat count again!");
            }
            if (!param.isValidWeight()) {
                edt_weight.setError("Please, enter your weight again!");
            }
            if (!param.isValidKrizh()) {
                edt_krizh.setError("Please, enter your krizh again!");
            }
        }
    }

    @Override
    public void onSuccess(String message) {
        spinner_receiver_city.setSelection(0);
        spinner_sender_city.setSelection(0);
        edt_weight.setText(null);
        edt_sender_company.setText(null);
        edt_sender_phone.setText(null);
        edt_sender_address.setText(null);
        txt_krizh.setText(null);
        edt_waybill_number.setText(null);
        edt_seat_count.setText(null);
        edt_sender_fullName.setText(null);
        edt_receiver_fullName.setText(null);
        edt_receiver_company.setText(null);
        edt_receiver_address.setText(null);
        edt_receiver_phone.setText(null);
        edt_cost.setText(null);
        weightType_id = null;
        senderCity_id = null;
        receiverCity_id = null;
        txt_krizh.setText(null);
        edt_krizh.setText(null);
        Snackbar.make(ll_layout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSuccessKrizh(String message) {
        txt_krizh.setText(null);
        txt_krizh.setText("Предыдущий был крыж: " + " " + message);
    }

    @Override
    public void onError(String message) {
        txt_krizh.setText(null);
        Snackbar.make(ll_layout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onErrorKrizh(String message) {
        txt_krizh.setText(null);
        Snackbar.make(ll_layout, message, Snackbar.LENGTH_LONG).show();
    }
}
