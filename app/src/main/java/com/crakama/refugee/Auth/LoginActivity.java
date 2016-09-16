package com.crakama.refugee.Auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crakama.refugee.MainActivity;
import com.crakama.refugee.R;
import com.crakama.refugee.database.DB;
import com.crakama.refugee.database.DBHelperAdapter;

public class LoginActivity extends AppCompatActivity {
   Button btnlogin;
    EditText txtusername,txtpassword;
    DBHelperAdapter helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        txtusername = (EditText)findViewById(R.id.editTextlogusername);
        final String strg = txtusername.getText().toString();
        txtpassword = (EditText)findViewById(R.id.editTextlogpass);
        final String pass = txtpassword.getText().toString();

        btnlogin = (Button)findViewById(R.id.buttonlogin);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String password = helper.searchPass(strg);
                if(pass.equals(password)){
                    DB b = new DB();
                    b.setIsLogin(true);
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }else{
                    DB b = new DB();
                    b.setIsLogin(false);
                    Toast temp = Toast.makeText(LoginActivity.this, "Password Mismatch!!" , Toast.LENGTH_SHORT);
                    temp.show();
                }

            }
        });
    }
}
