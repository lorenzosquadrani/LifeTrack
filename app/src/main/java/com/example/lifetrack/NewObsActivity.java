package com.example.lifetrack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class NewObsActivity extends AppCompatActivity {

    public static final String KEY_NAME = "keyName";
    public static final String KEY_FORMAT = "keyFormat";
    public static final String KEY_COLOR = "keyColor";
    public static final String KEY_SAMPLEFREQ = "keySampleFreq";

    private EditText editTextName;
    private EditText editTextFormat;
    private EditText editTextColor;
    private EditText editTextSampleFreq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_obs);

        editTextName = (EditText) findViewById(R.id.inputName);
        editTextFormat = (EditText) findViewById(R.id.inputFormat);
        editTextColor = (EditText) findViewById(R.id.inputColor);
        editTextSampleFreq = (EditText) findViewById(R.id.inputSampleFreq);
    }

    public void onDoneClick(View v) {
        Intent intent = new Intent();
        Uri uri = Uri.parse(editTextName.getText().toString());
        intent.setData(uri);
        setResult(RESULT_OK, intent);
        finish();
    }
}