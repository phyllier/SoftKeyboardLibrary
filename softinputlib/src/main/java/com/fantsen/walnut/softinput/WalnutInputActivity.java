package com.fantsen.walnut.softinput;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Author: zyl
 * Create time: 2017/11/8
 */

public class WalnutInputActivity extends AppCompatActivity {
    private InputRelativeLayout inputRel;
    private EditText editInput;
    private TextView btnOk;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        initView();
        initEvent();
    }

    private void initView() {
        inputRel = findViewById(R.id.relInput);
        editInput = findViewById(R.id.editInput);
        View viewMove = findViewById(R.id.linearInput);
        btnOk = findViewById(R.id.txtOk);
        inputRel.setMoveView(viewMove);
    }

    private void initEvent() {
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInputMethod(editInput);
                onBackPressed();
            }
        });

        inputRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideInputMethod(editInput);
                onBackPressed();
            }
        });
    }

    protected void hideInputMethod(View view) {
        InputMethodManager inputManger = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != inputManger) {
            inputManger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftInput(View view) {
        InputMethodManager inputManger = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (null != inputManger) {
            inputManger.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            inputRel.postDelayed(new Runnable() {
                @Override
                public void run() {
                    editInput.requestFocus();
                    showSoftInput(editInput);
                }
            }, 270);
            editInput.requestFocus();
            showSoftInput(editInput);
        }
    }
}
