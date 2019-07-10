package com.rglstudio.siswapresensi.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.ui.register.RegisterActivity;
import com.rglstudio.siswapresensi.ui.wali.MenuWaliActivity;
import com.rglstudio.siswapresensi.util.TextRequired;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.rgMasuk)
    RadioGroup radioGroup;
    @BindView(R.id.usernameLay)
    TextInputLayout usernameLay;
    @BindView(R.id.username)
    TextInputEditText username;
    @BindView(R.id.passLay)
    TextInputLayout passLay;
    @BindView(R.id.pass)
    TextInputEditText pass;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.btMakeAcc)
    TextView btMakeAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();
        initEvent();
    }

    private void initEvent() {
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()){
                    startActivity(new Intent(LoginActivity.this, MenuWaliActivity.class));
                }
            }
        });

        btMakeAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    private void initView() {
        username.addTextChangedListener(new TextRequired(usernameLay));
        pass.addTextChangedListener(new TextRequired(passLay));
    }

    private boolean isValidate() {

        if (TextUtils.isEmpty(username.getText())) {
            usernameLay.setErrorEnabled(true);
            usernameLay.setError("username wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(pass.getText())) {
            passLay.setErrorEnabled(true);
            passLay.setError("password wajib diisi");
            return false;
        }

        return true;
    }
}
