package com.example.OnRange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Activity3 extends AppCompatActivity implements View.OnClickListener {

    private String Username,Password,Tenant,DevEUI,AppKey,AppEUI;
    private TextView information;
    String finalCredBase6 = "";
    private Button Add,Delete,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
       information = findViewById(R.id.textView33);
        Add = findViewById(R.id.AddBut);
        Delete = findViewById(R.id.DeletBut);
        back = findViewById(R.id.QRBut);


        Add.setVisibility(View.GONE);
        Delete.setVisibility(View.INVISIBLE);
      //  back.setVisibility(View.INVISIBLE);


        Add.setOnClickListener( this);
        Delete.setOnClickListener( this);
        back.setOnClickListener( this);


        Intent intent = getIntent();
        DevEUI = intent.getStringExtra("DevEUI");
        AppKey = intent.getStringExtra("AppKey");
        AppEUI = intent.getStringExtra("AppEUI");
        Tenant = intent.getStringExtra("Tenant");
        Username = intent.getStringExtra("Username");
        Password = intent.getStringExtra("Password");

        String credentials = Username + ":"+  Password;
       String credBase64 = "Basic " + Base64.encodeToString(credentials.getBytes(),Base64.NO_WRAP);

        String url =  "https://impact.idc.nokia.com/m2m/endpoints/"; // https://impact.idc.nokia.com/m2m/endpoints?groupName=groupName=Europe.SWEDEN.HALMSTAD.Testbed.Student.OnbApp&startOffset=0&endOffset=30&fetchAll=True

        Retrofit.Builder builder = new Retrofit.Builder()               // https://impact.idc.nokia.com/m2m/endpoints/HejHANI123456789/ https://impact.idc.nokia.com/m2m/endpoints?groupName=groupName=Europe.SWEDEN.HALMSTAD.Testbed.Student.MgmtApp&startOffset=0&endOffset=30&fetchAll=True
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                ;
        Retrofit retrofit = builder.build();
        UserClient userClient = retrofit.create(UserClient.class);
      //  String ASDAS = "HejHANI123456789/";
      //  String Auth = "Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp";


        Call<String> call = userClient.getUser(credBase64,DevEUI);

         finalCredBase6 = credBase64;

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
               //     Add.setVisibility(View.VISIBLE);

                  //  information.setText(response.body().toString());

                      Delete.setVisibility(View.VISIBLE);
                   //     openActivity4();

                }
                if(!response.isSuccessful()){
                  //      openActivity5();


                   // information.setText(response.code());
                    Add.setVisibility(View.VISIBLE);
               //     Delete.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onFailure(Call<String> User, Throwable t) {
                information.setText (t.getMessage());

            }
        });

    }

    @Override
    public void onClick(View v) {
            if(v.getId() == R.id.AddBut){
                String url =  "https://impact.idc.nokia.com/m2m/endpoints/"; // http://impact.idc.nokia.com:30050/m2m/endpoints https://impact.idc.nokia.com/m2m/endpoints/

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        //           .client(OkHttpClientBuilder.build());
                        ;

                Retrofit retrofit = builder.build();
                UserClient userClient = retrofit.create(UserClient.class);


                AdditionalParams AdditionalParams1 = new AdditionalParams(
                        "SWEDENALN",
                        DevEUI,
                        AppKey,
                        AppEUI,
                        "OTAA",
                        "A",
                        "EU868",
                        "true",
                        "STATIC",
                        "A",
                        "1.0.3",
                        "0",
                        "MANUAL",
                        "0",
                        "0",
                        "0"
                );

                //  String text = "Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp";
                User user = new User(AdditionalParams1, "",Tenant,"", DevEUI, "HTTP");
                Call<User> call = userClient.addUser(finalCredBase6,user);

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){                                                    // ändra allt till <String> så funkar delete, o sök
                            // textView.setText("SUCCESS ADD - " + response.code());
                            //   textView.setText(response.body().toString());
                              information.setText(response.body().toString());
                            Toast.makeText(Activity3.this, "ADDED Successfully",Toast.LENGTH_SHORT).show();
                            Add.setVisibility(View.GONE);

                        }
                        if(!response.isSuccessful()){
                            //  textView.setText("NOT SUCCESS BUT NOT FAIL ADD - " + response.code());
                            //   textView.setText(JsonTest.toString());
                            //    textView.setText("Authorization:Basic" + text);
                            Toast.makeText(Activity3.this, "Not Successfull",Toast.LENGTH_SHORT).show();
                            information.setText(response.body().toString());

                        }
                    }
                    @Override
                    public void onFailure(Call<User> User, Throwable t) {
                          information.setText("Fail: " +t.getMessage());
                    }
                });


            //    Toast.makeText(Activity3.this, "ADD",Toast.LENGTH_SHORT).show();
            }               // funkar
            if(v.getId() == R.id.DeletBut){
                String url =  "https://impact.idc.nokia.com/m2m/endpoints/";

                Retrofit.Builder builder = new Retrofit.Builder()
                        .baseUrl(url)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        ;
                Retrofit retrofit = builder.build();
                UserClient userClient = retrofit.create(UserClient.class);

                String DeleteString = DevEUI + "/";
                // String Auth = "Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp";
                Call<String> call = userClient.deleteUser(finalCredBase6,DeleteString);


                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){                                                    // ändra allt till <String> så funkar delete, o sök
                            information.setText(response.body().toString());
                            Toast.makeText(Activity3.this, "Delete Successfully",Toast.LENGTH_SHORT).show();
                         //   Toast.makeText(Activity4.this, response.body().toString() ,Toast.LENGTH_SHORT).show();
                            Delete.setVisibility(View.INVISIBLE);

                        }
                        if(!response.isSuccessful()){
                            information.setText(response.body().toString());
                            Toast.makeText(Activity3.this, "Delete NOT Successfully",Toast.LENGTH_SHORT).show();
                         //   Toast.makeText(Activity4.this, response.body().toString(),Toast.LENGTH_SHORT).show();


                        }
                    }
                    @Override
                    public void onFailure(Call<String> User, Throwable t) {
                        information.setText("Fail: " +t.getMessage());
                    }
                });
            }             // funkar
            if(v.getId() == R.id.QRBut){
                Toast.makeText(Activity3.this, "BACK",Toast.LENGTH_SHORT).show();
                finish();
           }
    }
}



