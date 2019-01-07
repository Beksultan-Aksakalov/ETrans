package com.beksultan.e_trans.ui.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.R;
import com.beksultan.e_trans.model.user.AuthParam;
import com.beksultan.e_trans.ui.expeditor.order.OrderActivity;
import com.beksultan.e_trans.ui.stockman.StockManActivity;
import com.beksultan.e_trans.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.beksultan.e_trans.Constant.KEY;
import static com.beksultan.e_trans.Constant.USER_IS_LOGGED;

public class AuthorizationActivity extends MvpAppCompatActivity implements AuthorizationView {

    @InjectPresenter
    AuthorizationPresenter mvpPresenter;

    @BindView(R.id.mainLayout)
    LinearLayout mainLayout;

    @BindView(R.id.edt_username)
    EditText edt_username;

    @BindView(R.id.edt_password)
    EditText edt_password;

    @BindView(R.id.btn_sign)
    Button btn_sign;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkRole(App.getInstance().getPreferences().getString(KEY, ""));
        setContentView(R.layout.activity_authorization);
        ButterKnife.bind(this);
    }

    public void checkRole(String token) {
        if(StringUtils.isOk(token)){
            mvpPresenter.checkRole();
        }
    }

    @OnClick(R.id.btn_sign)
    public void onClickSignIn() {
        AuthParam authParam = new AuthParam(edt_username.getText().toString().trim(), edt_password.getText().toString().trim());
        if (authParam.isValidUsername() && authParam.isValidPassword()) {
            mvpPresenter.signIn(authParam);
        } else {
            if (!authParam.isValidUsername()) {
                edt_username.setError("Please, enter your username again!");
            }
            if (!authParam.isValidPassword()) {
                edt_password.setError("Please, enter your password again!");
            }
        }
    }

    @Override
    public void onOpenUI(String role) {
        if (App.getInstance().getPreferences().getBoolean(USER_IS_LOGGED, false)) {
            if (role.equals("stockman")) {
                startActivity(new Intent(AuthorizationActivity.this, StockManActivity.class));
                finish();
            }
            if (role.equals("expeditor")) {
                startActivity(new Intent(AuthorizationActivity.this, OrderActivity.class));
                finish();
            }
        }
    }

    @Override
    public void onFailedApiService(String error) {
        Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show();
    }
}
