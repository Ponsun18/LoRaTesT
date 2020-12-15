package com.example.OnRange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Activity3 extends AppCompatActivity implements View.OnClickListener {

    // hej
    private String Username,Password,Tenant,DevEUI,AppKey,AppEUI;
    private TextView information;
    String finalCredBase6 = "";
    private Button Add,Delete,back;
    FusedLocationProviderClient fusedLocationProviderClient;
    String Long = "";
    String Lat = "";
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


        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(
                Activity3.this
        );

        Call<String> call = userClient.getUser(credBase64,DevEUI);

         finalCredBase6 = credBase64;
        getCurrentLocation();
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() == 401){
                   // information.setText("Fel inlogg - reboot och skriv in igen");
                    Toast.makeText(Activity3.this, "Check Username and Password",Toast.LENGTH_SHORT).show();
                    finish();

                }
                if(response.isSuccessful()){

                  //  information.setText(" del " + response.code());     // fick 202 med riktiga inlogg

                    information.setText("The sensor: " +DevEUI + " is in the " +"\n" + Tenant + "\n"+ "Do you want to delete it? Or Re scan?" + "\n" + "Long " + Long + "Lat " + Lat);

                      Delete.setVisibility(View.VISIBLE);

                }
                if(!response.isSuccessful() && response.code() != 401){

                    information.setText("The sensor: " +DevEUI + " is not in the " +"\n" + Tenant +"\n" +"Do you want to add it? Or Re scan?" + "\n" + "Long " + Long + "Lat " + Lat);
                  //  information.setText(" ad " + response.code());  // fick 404 med riktiga inlogg          401 med fel
                    Add.setVisibility(View.VISIBLE);

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
                if(ActivityCompat.checkSelfPermission( Activity3.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission( Activity3.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                    String url = "https://impact.idc.nokia.com/m2m/endpoints/"; // http://impact.idc.nokia.com:30050/m2m/endpoints https://impact.idc.nokia.com/m2m/endpoints/

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
                            "12.1234",
                            "56.1234",
                            "0"
                    );

                    //  String text = "Basic T25ib2FyZENCOmFscGVybmE2Nzk5ZW5lcmdp";
                    User user = new User(AdditionalParams1, "", Tenant, "", DevEUI, "HTTP");
                    Call<User> call = userClient.addUser(finalCredBase6, user);

                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (response.isSuccessful()) {                                                    // ändra allt till <String> så funkar delete, o sök
                                // textView.setText("SUCCESS ADD - " + response.code());
                                //   textView.setText(response.body().toString());
                             //   information.setText(response.body().toString() + Long + Lat);
                                information.setText("Device added successfully");
                                Toast.makeText(Activity3.this, "ADDED Successfully", Toast.LENGTH_SHORT).show();
                                Add.setVisibility(View.GONE);

                            }
                            if (!response.isSuccessful()) {
                                //  textView.setText("NOT SUCCESS BUT NOT FAIL ADD - " + response.code());
                                //   textView.setText(JsonTest.toString());
                                //    textView.setText("Authorization:Basic" + text);
                                Toast.makeText(Activity3.this, "Not Successfull", Toast.LENGTH_SHORT).show();
                               // information.setText(response.body().toString() + Long + Lat);
                                information.setText("Device was not successfully added");

                            }
                        }

                        @Override
                        public void onFailure(Call<User> User, Throwable t) {
                            information.setText("Fail: " + t.getMessage());
                        }
                    });

                }
                else{
                    ActivityCompat.requestPermissions(Activity3.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION}, 100);
                }
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
    @SuppressLint("MissingPermission")
    private void getCurrentLocation() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){

            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location location = task.getResult();

                    if(location != null){
                        Lat = String.valueOf(location.getLatitude());
                        Long = String.valueOf(location.getLongitude());


                    //    tvlat.setText(String.valueOf(location.getLatitude()));
                    //    tvlong.setText(String.valueOf(location.getLongitude()));
                    }
                    else{
                        LocationRequest locationRequest = new LocationRequest().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                                .setInterval(1000)// 10000
                                .setFastestInterval(100) // 1000
                                .setNumUpdates(1);
                        LocationCallback locationCallback = new LocationCallback(){
                            @Override
                            public void onLocationResult(LocationResult locationResult) {
                                Location location1 = locationResult.getLastLocation();

                                Lat = String.valueOf(location1.getLatitude());
                                Long = String.valueOf(location1.getLongitude());

                             //   tvlat.setText(String.valueOf(location1.getLatitude()));
                             //   tvlong.setText(String.valueOf(location1.getLongitude()));

                            }
                        };
                        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
                    }
                }
            });
        }else{
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 100 && grantResults.length > 0 && (grantResults[0] + grantResults[1] == PackageManager.PERMISSION_GRANTED)){
            getCurrentLocation();
        }else{
            Toast.makeText(getApplicationContext(), "permission nono", Toast.LENGTH_SHORT).show();
        }
    }
}



