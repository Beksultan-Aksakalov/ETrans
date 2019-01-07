package com.beksultan.e_trans.ui.stockman;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.base.EndlessScrollListener;
import com.beksultan.e_trans.db.UserPreferences;
import com.beksultan.e_trans.model.expeditor.options.Choice;
import com.beksultan.e_trans.model.stockman.get.Waybill;
import com.beksultan.e_trans.ui.authorization.AuthorizationActivity;
import com.beksultan.e_trans.ui.stockman.history.WaybillHistoryActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class StockManActivity extends MvpAppCompatActivity implements StockManView {

    @InjectPresenter
    StockManPresenter mvpPresenter;

    private StockManAdapter adapter;

    private String city_id;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ll_layout)
    LinearLayout ll_layout;

    @BindView(R.id.spinner_city)
    Spinner spinner_city;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stockman);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        mvpPresenter.getCity();
        onSetParam();
        onSetUpRecyclerView(false);
    }

    private void onSetParam() {
        adapter = new StockManAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        adapter.setCallback(waybill -> {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setMessage("Вы действительно хотите отправить накладной ?");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ДА", ((dialog, which) -> {
                mvpPresenter.putWaybill(waybill.id);
            }));
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", ((dialog, which) -> alertDialog.dismiss()));
            alertDialog.show();
        });
    }

    private void onSetUpRecyclerView(boolean isLoadMore) {
        mvpPresenter.getWaybill(city_id, isLoadMore);
        recyclerView.addOnScrollListener(new EndlessScrollListener(recyclerView) {
            @Override
            public void onRequestMoreItems() {
                if (!adapter.isLoading()) {
                    mvpPresenter.getWaybill(city_id, true);
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
    public void onShowProgressBar(boolean isShow) {
        progressBar.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onShowProgressBarLoadMore(boolean isShow) {
        adapter.setLoading(isShow);
    }

    @Override
    public void onSetSenderCity(List<Choice> choiceList) {
        choiceList.add(0, new Choice("Город", null));
        ArrayList<String> cityNames = new ArrayList<>();
        for (Choice item : choiceList) {
            cityNames.add(item.display_name);
        }

        ArrayAdapter<String> regionArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cityNames);
        regionArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_city.setAdapter(regionArrayAdapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                city_id = choiceList.get(position).value;
                mvpPresenter.getWaybill(city_id, false);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };
        spinner_city.setOnItemSelectedListener(itemSelectedListener);
    }

    @Override
    public void onSuccess(ArrayList<Waybill> waybillArrayList) {
        adapter.clearItems();
        adapter.addList(waybillArrayList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String message) {
        Snackbar.make(ll_layout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stockman_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_waybill_history:
                startActivity(new Intent(StockManActivity.this, WaybillHistoryActivity.class));
                return true;
            case R.id.item_exit:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Действительно хотите выйти из E-Trans?")
                        .setPositiveButton("Да", ((dialog, which) -> onLogOut()))
                        .setNegativeButton("Нет", ((dialog, which) -> {
                        }))
                        .show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onLogOut() {
        UserPreferences.clear();
        startActivity(new Intent(StockManActivity.this, AuthorizationActivity.class));
        finish();
    }
}
