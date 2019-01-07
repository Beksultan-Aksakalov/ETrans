package com.beksultan.e_trans.ui.expeditor.order.history;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.base.BaseRViewAdapter;
import com.beksultan.e_trans.model.expeditor.get.Order;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderHistoryAdapter extends BaseRViewAdapter{

    private static final int LAYOUT_ID = R.layout.item_order_history;

    public OrderHistoryAdapter() {
    }

    @Override
    public void setLoading(boolean loading) {
        super.setLoading(loading);
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.item_order_history;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case LAYOUT_ID:
                return new HistoryHolder(inflate(parent, LAYOUT_ID));
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
                ((HistoryHolder) holder).bind((Order) data.get(position));
                break;
        }
    }

   public class HistoryHolder extends MainViewHolder{

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

       public HistoryHolder(View v) {
           super(v);
           ButterKnife.bind(this,v);
       }

       public void bind(Order order){
           tv_fullName.setText(order.full_name);
           tv_sender_address.setText(order.sender_address);
           tv_sender_phone.setText(order.sender_phone);
           tv_direction.setText(order.direction);
           tv_company.setText(order.company_name);
           tv_seat_count.setText(order.seat_count);
           tv_good_ready.setText(order.good_ready);
       }
   }
}
