package com.crakama.refugee.Auth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crakama.refugee.R;
import com.crakama.refugee.database.DB;
import com.crakama.refugee.database.DBHelperAdapter;

public class RegisterActivity extends AppCompatActivity {
    DBHelperAdapter helper = new DBHelperAdapter(this);
    Button btnregister;
    EditText txtUsername, txtEmail, txtPassword,txtConfirmPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtUsername = (EditText)findViewById(R.id.editTextUsername);
        txtEmail = (EditText)findViewById(R.id.editTextEmail);
        txtPassword = (EditText)findViewById(R.id.editTextpassword);
        txtConfirmPass = (EditText)findViewById(R.id.editTextCpassword);

        btnregister = (Button)findViewById(R.id.buttonregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             String nameStr = txtUsername.getText().toString();
                String emailStr = txtEmail.getText().toString();
                String passStr = txtPassword.getText().toString();
                String confirmpassStr = txtConfirmPass.getText().toString();

                if(!passStr.equals(confirmpassStr)){
                   Toast pass = Toast.makeText(RegisterActivity.this,"Password did not match",Toast.LENGTH_SHORT);
                    pass.show();
                }else{
                    DB b = new DB();
                    b.setUsername(nameStr);
                    b.setEmail(emailStr);
                    b.setPass(passStr);

                    helper.insertUser();
                }
            }
        });
    }
}
