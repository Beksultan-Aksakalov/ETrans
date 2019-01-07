package com.beksultan.e_trans.ui.stockman.history;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.base.BaseRViewAdapter;
import com.beksultan.e_trans.model.stockman.get.Waybill;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WaybillHistoryAdapter extends BaseRViewAdapter {

    private static final int LAYOUT_ID = R.layout.item_order_stockman_history;

    @Override
    public void setLoading(boolean loading) {
        super.setLoading(loading);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_order_stockman_history;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LAYOUT_ID:
                return new WaybillHistoryAdapter.WaybillHistory(inflate(parent, LAYOUT_ID));
            case PROGRESS_BAR_LAYOUT_ID:
                return new SimpleViewHolder(inflate(parent, PROGRESS_BAR_LAYOUT_ID));
            default:
                throw incorrectOnCreateViewHolder();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case LAYOUT_ID:
                ((WaybillHistoryAdapter.WaybillHistory) holder).bind((Waybill) data.get(position));
                break;
        }
    }

    public class WaybillHistory extends MainViewHolder{

        @BindView(R.id.tv_krizh)
        TextView tv_krizh;

        @BindView(R.id.tv_fullName)
        TextView tv_fullName;

        @BindView(R.id.tv_company)
        TextView tv_companyName;

        @BindView(R.id.tv_sender_city)
        TextView tv_sender_city;

        @BindView(R.id.tv_sender_address)
        TextView tv_sender_address;

        @BindView(R.id.tv_sender_phone)
        TextView tv_sender_phone;

        @BindView(R.id.tv_receiver_city)
        TextView tv_receiver_city;

        @BindView(R.id.tv_receiver_address)
        TextView tv_receiver_address;

        @BindView(R.id.tv_receiver_phone)
        TextView tv_receiver_phone;

        @BindView(R.id.tv_seat_count)
        TextView tv_seat_count;

        @BindView(R.id.tv_waybill_number)
        TextView tv_waybill_number;

        @BindView(R.id.tv_cost)
        TextView tv_cost;

        @BindView(R.id.tv_weight)
        TextView tv_weight;

        @BindView(R.id.tv_weight_type)
        TextView tv_weight_type;

        public WaybillHistory(View v) {
            super(v);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Waybill waybill) {
            tv_krizh.setText(waybill.krizh);
            tv_fullName.setText(waybill.full_name);
            tv_companyName.setText(waybill.company_name);
            tv_sender_city.setText(waybill.sender_city);
            tv_sender_address.setText(waybill.sender_address);
            tv_sender_phone.setText(waybill.sender_phone);
            tv_receiver_city.setText(waybill.receiver_city);
            tv_receiver_address.setText(waybill.receiver_address);
            tv_receiver_phone.setText(waybill.receiver_phone);
            tv_seat_count.setText(waybill.seat_count);
            tv_waybill_number.setText(waybill.waybill_number);
            tv_cost.setText(waybill.cost);
            tv_weight.setText(waybill.weight);
            if (waybill.weight_type.equals("1")) {
                tv_weight_type.setText("киллограм");
            } else {
                tv_weight_type.setText("куб");
            }
        }

    }
}
