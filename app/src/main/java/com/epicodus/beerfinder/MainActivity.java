package com.epicodus.beerfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.searchButton) Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        searchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == searchButton) {
//            Intent intent = new Intent(MainActivity.this, )
            Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
        }
    }
}
