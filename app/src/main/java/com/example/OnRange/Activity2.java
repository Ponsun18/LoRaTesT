package com.example.OnRange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class Activity2 extends AppCompatActivity implements View.OnClickListener{ // implements View.OnClickListener

    private TextView InformationOmInlogg2;
    private Button scanBtn;
    private EditText asd;
    private String DevEUI,AppEUI,AppKey;
    private String username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        InformationOmInlogg2 = findViewById(R.id.textView2);
        scanBtn = findViewById(R.id.button2);
        scanBtn.setOnClickListener(this);
        asd = findViewById(R.id.editTextTextPassword);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.Extra_text1);
        password = intent.getStringExtra(MainActivity.Extra_text2);

      //  InformationOmInlogg2.setText(text1 + "\n" + text2);
        InformationOmInlogg2.setText("Write which tenant you want to work with, (EXAMPLE) " +"\n" +"Europe.SWEDEN.HALMSTAD.Testbed.Student.OnbApp");

        asd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String Test = asd.getText().toString().trim();

                scanBtn.setEnabled(!Test.isEmpty());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        scanCode();
    }

    private void scanCode() {
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setCaptureActivity(CaptureAct.class);
        integrator.setOrientationLocked(false);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt("SCANNING CODE");
        integrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null){
            if(result.getContents() != null){
                String ASD = result.getContents();
                String [] arrSplit = ASD.split(",");


                // skicka till Activity3?

              //  InformationOmInlogg2.setText("Dev EUI: " + arrSplit[0] + "\n" + "Default App Key: " + arrSplit[1] + "\n" + "Default App EUI: " + arrSplit[2]);
                DevEUI = arrSplit[0];
                AppKey = arrSplit[1];
                AppEUI = arrSplit[2];
                openActivity3();
                finish();
            }


            else {
                Toast.makeText(this, "No results", Toast.LENGTH_LONG).show();
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void openActivity3() {

        String text = asd.getText().toString();

       // InformationOmInlogg2.setText(DevEUI+"\n"+AppKey+"\n"+AppEUI+"\n"+text+"\n"); //+username+"\n"+password

        Intent intent2 = new Intent(this, Activity3.class);
        intent2.putExtra("DevEUI",DevEUI);
        intent2.putExtra("AppKey", AppKey);
        intent2.putExtra("AppEUI", AppEUI);
        intent2.putExtra("Tenant", text);
        intent2.putExtra("Username", username);
        intent2.putExtra("Password", password);

        startActivity(intent2);


    }


}