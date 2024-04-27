package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import hk.hku.cs.hkudirectory.dao.UserDao;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "mysql-hkudirectory-MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

    }

    public void reg(View view) {
        startActivity(new Intent(getApplicationContext(), register.class));
    }

    public void login(View view) {
        EditText EditTextEmail = findViewById(R.id.email_input);
        EditText EditTextPassword = findViewById(R.id.password_input);

        new Thread() {
            public void run() {
                UserDao userDao = new UserDao();
                int msg = userDao.login(EditTextEmail.getText().toString(), EditTextPassword.getText().toString());
                hand1.sendEmptyMessage(msg);
            }
        }.start();
    }

    @SuppressLint("HandlerLeak")
    final Handler hand1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                Toast.makeText(getApplicationContext(), "Login failed!", Toast.LENGTH_LONG).show();
            } else if (msg.what == 1) {
                Toast.makeText(getApplicationContext(), "Login successfully!", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                startActivity(intent);
            } else if (msg.what == 2) {
                Toast.makeText(getApplicationContext(), "Wrong password!", Toast.LENGTH_LONG).show();
            } else if (msg.what == 3) {
                Toast.makeText(getApplicationContext(), "Account doesn't exist!", Toast.LENGTH_LONG).show();
            }
        }
    };

}


