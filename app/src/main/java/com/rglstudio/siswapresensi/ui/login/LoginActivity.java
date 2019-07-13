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
import com.rglstudio.siswapresensi.model.ResponLogin;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.ui.register.RegisterActivity;
import com.rglstudio.siswapresensi.ui.wali.MenuWaliActivity;
import com.rglstudio.siswapresensi.util.DialogUtil;
import com.rglstudio.siswapresensi.util.MyPref;
import com.rglstudio.siswapresensi.util.TextRequired;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView {
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

    private MyPref pref;
    private LoginPresenter presenter;
    private String loginAs = "wali";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initView();
        checkLogin();
        initEvent();
    }

    private void checkLogin() {
        if (pref.getKeyIsLogin()){
            if (pref.getKeyLoginAs().equals("wali")) {
                Intent intent = new Intent(LoginActivity.this, MenuWaliActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
            else if (pref.getKeyLoginAs().equals("guru")) {
                Intent intent = new Intent(LoginActivity.this, MenuWaliActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }
    }

    private void initEvent() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbGuru){
                    loginAs = "guru";
                }
                else if (checkedId==R.id.rbWali){
                    loginAs = "wali";
                }
            }
        });

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()){
                    DialogUtil.showProgressDialog(LoginActivity.this, "Proses, mohon tunggu...");
                    presenter.login(API.LOGIN, username.getText().toString(),
                            pass.getText().toString(), loginAs);
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
        pref = new MyPref(this);
        presenter = new LoginPresenter(this);
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

    @Override
    public void onSuccess(ResponLogin responLogin) {
        DialogUtil.dialogDismiss();
        pref.setKeyIsLogin(true);
        if (loginAs.equals("guru")){
            pref.setKeyLoginAs("guru");
            pref.setKeyUserId(responLogin.getData().getGuru().getId());
            pref.setKeyUserKdMapel(responLogin.getData().getGuru().getKdMapel());
        }
        else if (loginAs.equals("wali")){
            pref.setKeyLoginAs("wali");
            pref.setKeyUserId(responLogin.getData().getWali().getId());
            pref.setKeyUserNis(responLogin.getData().getWali().getNis());
            pref.setKeyUserSiswaName(responLogin.getData().getWali().getNamaSiswa());
            pref.setKeyUserSiswaKelas(responLogin.getData().getWali().getKdKelas());

            Intent intent = new Intent(LoginActivity.this, MenuWaliActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    }

    @Override
    public void onFailed(String msg) {
        DialogUtil.dialogDismiss();
        DialogUtil.showAlert(this, msg);
    }
}
