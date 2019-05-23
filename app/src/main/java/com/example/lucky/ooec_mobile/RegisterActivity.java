package com.example.lucky.ooec_mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.lucky.ooec_mobile.Adapters.TokenManager;
import com.example.lucky.ooec_mobile.Models.AccessToken;
import com.example.lucky.ooec_mobile.Models.ApiError;
import com.example.lucky.ooec_mobile.Models.Register;
import com.example.lucky.ooec_mobile.Services.LoginServices;
import com.example.lucky.ooec_mobile.Services.RetrofitClient;
import com.example.lucky.ooec_mobile.Services.Utils;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    @BindView(R.id.til_firstname)
    TextInputLayout tilFirstname;
    @BindView(R.id.til_lastname)
    TextInputLayout tilLastname;
    @BindView(R.id.til_email)
    TextInputLayout tilEmail;
    @BindView(R.id.til_password)
    TextInputLayout tilPassword;
    @BindView(R.id.til_location)
    TextInputLayout tilLocation;

    LoginServices service;
    Call<AccessToken> call;
    AwesomeValidation validator;
    TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);

        service = RetrofitClient.createService(LoginServices.class);
        validator = new AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT);
        tokenManager = TokenManager.getInstance(getSharedPreferences("prefs", MODE_PRIVATE));
        setupRules();

      /*  if(tokenManager.getToken().getAccessToken() != null){
            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            finish();
        }*/
    }


    @OnClick(R.id.btn_register)
    void register(){
        Register register=new Register();
        register.setFirstName(tilFirstname.getEditText().getText().toString());
        register.setLastName(tilLastname.getEditText().getText().toString());
        register.setEmail(tilEmail.getEditText().getText().toString());
        register.setPassword(tilPassword.getEditText().getText().toString());
        register.setLocation(tilLocation.getEditText().getText().toString());

        tilFirstname.setError(null);
        tilLastname.setError(null);
        tilEmail.setError(null);
        tilPassword.setError(null);
        tilLocation.setError(null);

        validator.clear();

        if(validator.validate()) {
            call = service.register(register);
            call.enqueue(new Callback<AccessToken>() {
                @Override
                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {

                    Log.w(TAG, "onResponse: " + response);

                    if (response.isSuccessful()) {
                        Log.w(TAG, "onResponse: " + response.body() );
                        tokenManager.saveToken(response.body());
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();
                    } else {
                        handleErrors(response.errorBody());
                    }

                }

                @Override
                public void onFailure(Call<AccessToken> call, Throwable t) {
                    Log.w(TAG, "onFailure: " + t.getMessage());
                }
            });
        }
    }
    @OnClick(R.id.go_to_login)
    void goToRegister(){
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
    }


    private void handleErrors(ResponseBody response){

        ApiError apiError = Utils.converErrors(response);

        for(Map.Entry<String, List<String>> error : apiError.getErrors().entrySet()){
            if(error.getKey().equals("firstname")){
                tilFirstname.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("lastname")){
                tilLastname.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("email")){
                tilEmail.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("password")){
                tilPassword.setError(error.getValue().get(0));
            }
            if(error.getKey().equals("location")){
                tilLocation.setError(error.getValue().get(0));
            }
        }

    }

    public void setupRules(){

        validator.addValidation(this, R.id.til_firstname, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.til_lastname, RegexTemplate.NOT_EMPTY, R.string.err_name);
        validator.addValidation(this, R.id.til_email, Patterns.EMAIL_ADDRESS, R.string.err_email);
        validator.addValidation(this, R.id.til_password, "[a-zA-Z0-9]{6,}", R.string.err_password);
        validator.addValidation(this, R.id.til_location, RegexTemplate.NOT_EMPTY, R.string.err_name);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(call != null) {
            call.cancel();
            call = null;
        }
    }
}
