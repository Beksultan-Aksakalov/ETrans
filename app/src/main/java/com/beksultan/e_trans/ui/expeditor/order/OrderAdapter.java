package com.beksultan.e_trans.ui.expeditor.order;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beksultan.e_trans.R;
import com.beksultan.e_trans.base.BaseRViewAdapter;
import com.beksultan.e_trans.model.expeditor.get.Order;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderAdapter extends BaseRViewAdapter {

    private static final int LAYOUT_ID = R.layout.item_order_order;

    private Callback callback;

    OrderAdapter() {
    }

    @Override
    public void setLoading(boolean loading) {
        super.setLoading(loading);
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LAYOUT_ID:
                return new ExpeditorHolder(inflate(parent, LAYOUT_ID));
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
                ((ExpeditorHolder) holder).bind((Order) data.get(position));
                break;
        }
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_order_order;
    }

    public class ExpeditorHolder extends MainViewHolder {

        @BindView(R.id.tv_fullName)
        TextView tv_fullName;

        @BindView(R.id.tv_sender_address)
        TextView tv_sender_address;

        @BindView(R.id.tv_sender_phone)
        TextView tv_sender_phone;

        @BindView(R.id.tv_direction)
        TextView tv_direction;

        @BindView(R.id.tv_company)
        TextView tv_company;

        @BindView(R.id.tv_seat_count)
        TextView tv_seat_count;

        @BindView(R.id.tv_good_ready)
        TextView tv_good_ready;

        @BindView(R.id.imgView_order)
        ImageView imgView_order;

        @BindView(R.id.imgView_stock)
        ImageView imgView_stock;

        @BindView(R.id.ll_getOrder)
        LinearLayout ll_getOrder;

        @BindView(R.id.ll_ToStock)
        LinearLayout ll_ToStock;

        Order bindOrder;

        private ExpeditorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Order order) {
            bindOrder = order;
            tv_fullName.setText(order.full_name);
            tv_sender_address.setText(order.sender_address);
            tv_sender_phone.setText(order.sender_phone);
            tv_direction.setText(order.direction);
            tv_company.setText(order.company_name);
            tv_seat_count.setText(order.seat_count);
            tv_good_ready.setText(order.good_ready);

            if (order.status.equals("новый")) {
                imgView_stock.setBackgroundResource(0);
                imgView_order.setBackgroundResource(R.drawable.ic_get_order);
            }
            if (order.status.equals("В процессе")) {
                imgView_order.setBackgroundResource(0);
                imgView_stock.setBackgroundResource(R.drawable.ic_done);
            }
        }

        @OnClick(R.id.tv_sender_phone)
        public void onClickSenderPhone() {
            callback.callSenderPhone(bindOrder);
        }

        @OnClick(R.id.ll_getOrder)
        public void onClickOrder() {
            callback.getOrder(bindOrder);
        }

        @OnClick(R.id.ll_ToStock)
        public void onClickStock() {
            callback.toStock(bindOrder);
        }
    }

    public interface Callback {
        void getOrder(Order order);

        void toStock(Order order);

        void callSenderPhone(Order order);
    }
}
