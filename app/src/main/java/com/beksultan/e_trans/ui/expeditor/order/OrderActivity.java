package com.beksultan.e_trans.ui.expeditor.order;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.base.EndlessScrollListener;
import com.beksultan.e_trans.db.UserPreferences;
import com.beksultan.e_trans.model.expeditor.get.Order;
import com.beksultan.e_trans.model.expeditor.put.StatusParam;
import com.beksultan.e_trans.ui.authorization.AuthorizationActivity;
import com.beksultan.e_trans.ui.expeditor.order.history.OrderHistoryActivity;
import com.beksultan.e_trans.ui.expeditor.waybill.WaybillActivity;
import com.beksultan.e_trans.ui.info.AdditionalInfoActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends MvpAppCompatActivity implements OrderView, NavigationView.OnNavigationItemSelectedListener {

    @InjectPresenter
    OrderPresenter mvpPresenter;

    private OrderAdapter adapter;

    public ActionBarDrawerToggle mToggle;

    @BindView(R.id.drawer)
    DrawerLayout drawerLayout;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.ll_layout)
    LinearLayout ll_layout;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expeditor);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        mToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        onSetParam();
        onSetUpRecyclerView(false);
    }

    private void onSetParam() {
        adapter = new OrderAdapter();
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(adapter);
        adapter.setCallback(new OrderAdapter.Callback() {
            @Override
            public void getOrder(Order order) {
                if (order.status.equals("новый")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(OrderActivity.this).create();
                    alertDialog.setMessage("Вы действительно хотите взять заявку в работу ?");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ДА", ((dialog, which) -> {

                        StatusParam param = new StatusParam("2");
                        mvpPresenter.putOrderOrStock(order.id, param);

                    }));
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", ((dialog, which) -> alertDialog.dismiss()));
                    alertDialog.show();
                }
            }

            @Override
            public void toStock(Order order) {
                if (order.status.equals("В процессе")) {
                    AlertDialog alertDialog = new AlertDialog.Builder(OrderActivity.this).create();
                    alertDialog.setMessage("Вы действительно отвезли груз на склад ?");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "ДА", ((dialog, which) -> {

                        StatusParam param = new StatusParam("3");
                        mvpPresenter.putOrderOrStock(order.id, param);

                    }));
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancel", ((dialog, which) -> alertDialog.dismiss()));
                    alertDialog.show();
                }
            }

            @Override
            public void callSenderPhone(Order order) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + order.sender_phone));
                startActivity(callIntent);
            }
        });
    }

    private void onSetUpRecyclerView(boolean isLoadMore) {
        mvpPresenter.getOrder(isLoadMore);
        recyclerView.addOnScrollListener(new EndlessScrollListener(recyclerView) {
            @Override
            public void onRequestMoreItems() {
                if (!adapter.isLoading()) {
                    mvpPresenter.getOrder(true);
                }
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onSetOrderList(ArrayList<Order> orderList) {
        adapter.clearItems();
        adapter.refresh(orderList);
    }

    @Override
    public void addOrderList(ArrayList<Order> orderList) {
        adapter.refresh(orderList);
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

    @Override
    public void onSuccess(ArrayList<Order> orderList) {
        adapter.clearItems();
        adapter.refresh(orderList);
    }

    public void onLogOut() {
        UserPreferences.clear();
        startActivity(new Intent(OrderActivity.this, AuthorizationActivity.class));
        finish();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_order_history:
                startActivity(new Intent(OrderActivity.this, OrderHistoryActivity.class));
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        displaySelectedItem(id);
        return true;
    }

    private void displaySelectedItem(int id) {
        switch (id) {
            case R.id.item_order:
                break;

            case R.id.item_waybill:
                startActivity(new Intent(OrderActivity.this, WaybillActivity.class));
                break;

            case R.id.item_info:
                startActivity(new Intent(OrderActivity.this, AdditionalInfoActivity.class));
                break;

            case R.id.item_logout:
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Действительно хотите выйти из E-Trans?")
                        .setPositiveButton("Да", ((dialog, which) -> onLogOut()))
                        .setNegativeButton("Нет", ((dialog, which) -> {
                        }))
                        .show();
                break;
            default:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
    }
}
