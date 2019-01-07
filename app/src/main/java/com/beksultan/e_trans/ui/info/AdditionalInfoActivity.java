package com.beksultan.e_trans.ui.info;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import com.beksultan.e_trans.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdditionalInfoActivity extends AppCompatActivity {

    @BindView(R.id.img_back)
    ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.img_back)
    public void onClickback() {
        finish();
    }
}
