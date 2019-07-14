package com.rglstudio.siswapresensi.ui.register;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.rglstudio.siswapresensi.R;
import com.rglstudio.siswapresensi.model.ResponRegister;
import com.rglstudio.siswapresensi.service.API;
import com.rglstudio.siswapresensi.util.DialogUtil;
import com.rglstudio.siswapresensi.util.TextRequired;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements RegisterView{
    @BindView(R.id.rgGender)
    RadioGroup rgGender;

    @BindView(R.id.nameLay)
    TextInputLayout nameLay;
    @BindView(R.id.name)
    TextInputEditText name;

    @BindView(R.id.nisLay)
    TextInputLayout nisLay;
    @BindView(R.id.nis)
    TextInputEditText nis;

    @BindView(R.id.addressLay)
    TextInputLayout addressLay;
    @BindView(R.id.address)
    TextInputEditText address;

    @BindView(R.id.phoneLay)
    TextInputLayout phoneLay;
    @BindView(R.id.phone)
    TextInputEditText phone;

    @BindView(R.id.usernameLay)
    TextInputLayout usernameLay;
    @BindView(R.id.username)
    TextInputEditText username;

    @BindView(R.id.passLay)
    TextInputLayout pasLay;
    @BindView(R.id.pass)
    TextInputEditText pass;

    @BindView(R.id.btRegis)
    Button btRegis;

    private RegisterPresenter presenter;
    private String gender = "laki-laki";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        initView();
        initEvent();
    }

    private void initView() {
        presenter = new RegisterPresenter(this);
        name.addTextChangedListener(new TextRequired(nameLay));
        nis.addTextChangedListener(new TextRequired(nisLay));
        address.addTextChangedListener(new TextRequired(addressLay));
        phone.addTextChangedListener(new TextRequired(phoneLay));
        username.addTextChangedListener(new TextRequired(usernameLay));
        pass.addTextChangedListener(new TextRequired(pasLay));
    }

    private void initEvent() {
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId==R.id.rbLaki){
                    gender = "laki-laki";
                }
                else if (checkedId==R.id.rbPerempuan){
                    gender = "perempuan";
                }
            }
        });

        btRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidate()){
                    DialogUtil.showProgressDialog(RegisterActivity.this, "Proses, mohon tunggu...");
                    presenter.register(API.REGIS_WALI, name.getText().toString(), nis.getText().toString(),
                            address.getText().toString(), phone.getText().toString(), gender,
                            username.getText().toString(), pass.getText().toString());
                }
            }
        });
    }

    private boolean isValidate() {

        if (TextUtils.isEmpty(name.getText())) {
            nameLay.setErrorEnabled(true);
            nameLay.setError("Wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(nis.getText())) {
            nisLay.setErrorEnabled(true);
            nisLay.setError("Wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(address.getText())) {
            addressLay.setErrorEnabled(true);
            addressLay.setError("Wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(phone.getText())) {
            phoneLay.setErrorEnabled(true);
            phoneLay.setError("Wajib diisi");
            return false;
        }

        if (phone.length()>11){
            phoneLay.setErrorEnabled(true);
            phoneLay.setError("Maksimal 11 angka");
            return false;
        }

        if (TextUtils.isEmpty(username.getText())) {
            usernameLay.setErrorEnabled(true);
            usernameLay.setError("Wajib diisi");
            return false;
        }

        if (TextUtils.isEmpty(pass.getText())) {
            pasLay.setErrorEnabled(true);
            pasLay.setError("Wajib diisi");
            return false;
        }

        return true;
    }

    @Override
    public void onSuccess(ResponRegister responRegister) {
        DialogUtil.dialogDismiss();
        DialogUtil.showToast(this, responRegister.getMessage());
        finish();
    }

    @Override
    public void onFailed(String msg) {
        DialogUtil.dialogDismiss();
        DialogUtil.showAlert(this, msg);
    }
}
