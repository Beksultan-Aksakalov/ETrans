package com.beksultan.e_trans.ui.expeditor.order.history;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.base.EndlessScrollListener;
import com.beksultan.e_trans.model.expeditor.get.Order;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderHistoryActivity extends MvpAppCompatActivity implements OrderHistoryView {

    @InjectPresenter
    OrderHistoryPresenter mvpPresenter;

    private OrderHistoryAdapter adapter;

    @BindView(R.id.ll_layout)
    LinearLayout ll_layout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
        onSetParam();
        onSetUpRecyclerView(false);
    }

    private void onSetParam() {
        adapter = new OrderHistoryAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private void onSetUpRecyclerView(boolean isLoadMore) {
        mvpPresenter.getOrderHistory(isLoadMore);
        recyclerView.addOnScrollListener(new EndlessScrollListener(recyclerView) {
            @Override
            public void onRequestMoreItems() {
                if (!adapter.isLoading()) {
                    mvpPresenter.getOrderHistory(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSetOrderList(ArrayList<Order> orderList) {
        adapter.clearItems();
        adapter.addList(orderList);
    }

    @Override
    public void addOrderList(ArrayList<Order> orderList) {
        adapter.addList(orderList);
    }

    @Override
    public void onError(String message) {
        Snackbar.make(ll_layout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onShowProgressBar(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onShowProgressBarLoadMore(boolean isShow) {
        adapter.setLoading(isShow);
    }

    @OnClick(R.id.img_back)
    public void onClickBack(){
       finish();
    }
}
