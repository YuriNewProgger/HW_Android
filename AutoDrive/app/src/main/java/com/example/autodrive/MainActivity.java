package com.example.autodrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String name = ((UserModel)getApplication()).getName();
        if(name != null){
            findViewById(R.id.signBtn).setVisibility(View.GONE);
            findViewById(R.id.registrBtn).setVisibility(View.GONE);
            findViewById(R.id.hiddenLabel).setVisibility(View.VISIBLE);
            TextView mainText = findViewById(R.id.mainText);

            mainText.setText("Привет " + name);
        }
    }

    @Override
    public  boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        TextView headerView = findViewById(R.id.mainText);
        switch (id){
            case R.id.action_setting:
                return true;
            case R.id.car_settings:
                Intent intentCars = new Intent(this, CarsActivity.class);
                startActivity(intentCars);
                return true;
            case R.id.my_room:
                String name = ((UserModel)getApplication()).getName();
                if(name != null){
                    Intent intent = new Intent(this, PersanalAreaActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getApplicationContext(), "Вы не авторизованы", Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.contact_settings:
                Intent intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClickSign(View view) {
        Intent intent = new Intent(this, SignActivity.class);
        startActivity(intent);
    }

    public void onClickRegistr(View view) {
        Intent intent = new Intent(this, RegistrActivity.class);
        startActivity(intent);
    }
}