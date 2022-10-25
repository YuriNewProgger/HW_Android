package com.example.autodrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
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
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.car_settings:
                Intent intentCars = new Intent(this, CarsActivity.class);
                startActivity(intentCars);
                return true;
            case R.id.my_room:
                Intent intentPA = new Intent(this, PersanalAreaActivity.class);
                startActivity(intentPA);
                return true;
            case R.id.contact_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}