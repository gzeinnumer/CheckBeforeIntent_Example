package com.gzeinnumer.checkbeforeintent_example;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gzeinnumer.cbi.CheckBeforeIntent;

public class MainActivity extends AppCompatActivity {

    EditText editText1;
    EditText editText2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = findViewById(R.id.ed1);
        editText2 = findViewById(R.id.ed2);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(view -> {
            check();
        });
    }

    private void check() {
        new CheckBeforeIntent()
                .addView(editText1)
                .addView(editText2)
                .isSaveToLeave(new CheckBeforeIntent.CheckBeforeIntentCallBack() {
                    @Override
                    public void isSaveToLeave(boolean isSave) {
                        if (isSave) //true -> free to lease
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        else { //false -> not free to leave
                            Toast.makeText(MainActivity.this, "Are you sure leave the activity", Toast.LENGTH_SHORT).show();
                            //show confirm dialod and move to new activity if user choise YES
                            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }
                    }
                }).build();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        check();
    }
}