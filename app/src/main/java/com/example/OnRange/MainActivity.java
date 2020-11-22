package com.example.OnRange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

   private EditText UserNameE;
   private EditText PasswordE;
   private Button LoginE;
    private TextView InformationOmInlogg;
    public static final String Extra_text1 = "com.example.OnRange.Extra_text1";
    public static final String Extra_text2 = "com.example.OnRange.Extra_text2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.Welcome);
        textView.setText("Login To IMPACT"); //set text for text view

        getSupportActionBar().setTitle("OnRange");  // set the Title
        /* getSupportActionBar().setDisplayShowTitleEnabled(false); */ // remove title
        UserNameE = findViewById(R.id.UserName);
        PasswordE = findViewById(R.id.Password);
        LoginE = findViewById(R.id.Login);

        InformationOmInlogg = findViewById(R.id.textName); // For Rewriting the username and password

        LoginE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String InputName = UserNameE.getText().toString();              // InputName has the username
                String InputPassword = PasswordE.getText().toString();          // InputPassword gets the password.
                getWindow().setSoftInputMode(
                        WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
                );
                if(InputName.isEmpty() || InputPassword.isEmpty() ){            // ingen input ger ett meddelande
                    Toast.makeText(MainActivity.this, "Please fill all correctly",Toast.LENGTH_SHORT).show();

                }else{

                            // skicka till impact o sånt.

                  //  Toast.makeText(MainActivity.this, "welcome",Toast.LENGTH_SHORT).show();

                  //  InformationOmInlogg.setText("UserName: " + InputName+  "\n"  +"Password: "+ InputPassword);  // For Rewriting the username and password

                    openActivity2();

                }
            }
        });

    }

    private void openActivity2() {
        EditText editText1 = (EditText) findViewById(R.id.UserName);
        String text = editText1.getText().toString();
        EditText editText2 = (EditText) findViewById(R.id.Password);
        String text2 = editText2.getText().toString();

        Intent intent = new Intent(this, Activity2.class);
        intent.putExtra(Extra_text1, text);
        intent.putExtra(Extra_text2, text2);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            // If you press about in the menu
            case R.id.item2:
               // Toast.makeText(this, "You pressed About", Toast.LENGTH_SHORT).show();
                AboutUs();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
        // for the about message
    private void AboutUs() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(), "test dialog");
    }



}