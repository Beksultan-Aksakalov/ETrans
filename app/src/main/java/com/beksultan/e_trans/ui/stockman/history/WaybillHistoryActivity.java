package com.beksultan.e_trans.ui.stockman.history;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.beksultan.e_trans.model.stockman.get.Waybill;
import com.beksultan.e_trans.ui.stockman.StockManAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaybillHistoryActivity extends MvpAppCompatActivity implements WaybillHistoryView {

    @InjectPresenter
    WaybillHistoryPresenter mvpPresenter;

    private WaybillHistoryAdapter adapter;

    @BindView(R.id.ll_layout)
    LinearLayout ll_layout;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waybill_history);
        ButterKnife.bind(this);
        onSetParam();
        onSetUpRecyclerView(false);
    }

    private void onSetParam() {
        adapter = new WaybillHistoryAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
    }

    private void onSetUpRecyclerView(boolean isLoadMore) {
        mvpPresenter.getWaybillHistory(isLoadMore);
        recyclerView.addOnScrollListener(new EndlessScrollListener(recyclerView) {
            @Override
            public void onRequestMoreItems() {
                if (!adapter.isLoading()) {
                    mvpPresenter.getWaybillHistory(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSetWaybillList(ArrayList<Waybill> waybillArrayList) {
        adapter.clearItems();
        adapter.addList(waybillArrayList);
    }

    @Override
    public void addWaybillList(ArrayList<Waybill> waybillArrayList) {
        adapter.addList(waybillArrayList);
    }

    @Override
    public void onError(String message) {
        Snackbar.make(ll_layout,message,Snackbar.LENGTH_LONG).show();
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
