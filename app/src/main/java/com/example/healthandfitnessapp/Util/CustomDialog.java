package com.example.healthandfitnessapp.Util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.healthandfitnessapp.R;

public class CustomDialog extends Dialog implements View.OnClickListener {

    public Context mContext;
    public Dialog d;
    public Button yes, no;

    private String dialogTitle;
    private TextView dialogTitleTxt;
    private TextView titleTxt, subTxt;
    private String inputTitle, inputSub;
    private EditText dialog_titleValue, dialog_subValue;
    //private String inputOneValue, inputTwoValue;

    public CustomDialog(@NonNull Context context, String dialogTitle,
                        String inputTitle, String inputSub) {
        super(context);
        this.mContext = context;
        this.dialogTitle = dialogTitle;
        this.inputTitle = inputTitle;
        this.inputSub = inputSub;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = findViewById(R.id.btn_yes);
        no = findViewById(R.id.btn_no);
        dialogTitleTxt = findViewById(R.id.txt_title);
        titleTxt = findViewById(R.id.titleTxt);
        dialog_titleValue = findViewById(R.id.dialog_titleValue);
        subTxt = findViewById(R.id.subTxt);
        dialog_subValue = findViewById(R.id.dialog_subValue);

        dialogTitleTxt.setText(dialogTitle);
        titleTxt.setText(inputTitle);
        subTxt.setText(inputSub);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        //inputOneValue = dialog_titleValue.getText().toString().trim();
        //inputTwoValue = dialog_subValue.getText().toString().trim();

    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btn_yes) {

        } if (view.getId() == R.id.btn_no) {
            dismiss();
        }
    }

    public String getFirstValue() {
        return dialog_titleValue.getText().toString().trim();
    }
    public String getSecondValue() {
        return dialog_subValue.getText().toString().trim();
    }

}

