package com.example.autodrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PersanalAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persanal_area);

        TextView fieldName = findViewById(R.id.fieldName);
        fieldName.setText("Имя: " + ((UserModel)getApplication()).getName());

        TextView fieldSurname = findViewById(R.id.fieldSurname);
        fieldSurname.setText("Фамилия: " + ((UserModel)getApplication()).getSurname());

        TextView fieldPatronymic = findViewById(R.id.fieldPatronymic);
        fieldPatronymic.setText("Отчество: " + ((UserModel)getApplication()).getPatronymic());

        TextView fieldPhone = findViewById(R.id.fieldPhone);
        fieldPhone.setText("Телефон: " + ((UserModel)getApplication()).getPhone());

        TextView fieldEmail = findViewById(R.id.fieldEmail);
        fieldEmail.setText("Эл. почта: " + ((UserModel)getApplication()).getEmail());

        httpCall("http://192.168.1.9:3000/api/history/"+((UserModel)getApplication()).getId());
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
                Intent maintain = new Intent(this, MainActivity.class);
                startActivity(maintain);
                return true;
            case R.id.car_settings:
                Intent intentCars = new Intent(this, CarsActivity.class);
                startActivity(intentCars);
                return true;
            case R.id.my_room:
                return true;
            case R.id.contact_settings:
                Intent intent = new Intent(this, ContactActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void httpCall(String url) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                        try {
                            JSONArray list = new JSONArray(response);
                            for (int i = 0; i < list.length(); ++i) {
                                JSONObject rec = list.getJSONObject(i);
                                String name = rec.getString("name");
                                String startDate = rec.getString("start_date_book")
                                        .replace('T', ' ').replace('Z', ' ');
                                String ensDate = rec.getString("end_date_book")
                                        .replace('T', ' ').replace('Z', ' ');
                                String sum = rec.getString("sum_rent").replace('?', ' ');
                                String result = "Автомобиль: " + name + "\n" + "Начало: " + startDate + "\n" + "Окончание: " + ensDate + "\n" + "Сумма: " + sum;

                                TextView tv = new TextView(getApplicationContext());
                                tv.setText(result);
                                tv.setTextSize(20);
                                tv.setGravity(Gravity.CENTER_VERTICAL);
                                LinearLayout ll = (LinearLayout) findViewById(R.id.historyList);
                                ll.addView(tv);

                                TextView sep = new TextView(getApplicationContext());
                                sep.setText("==========");
                                sep.setTextSize(20);
                                sep.setGravity(Gravity.CENTER_VERTICAL);
                                ll.addView(sep);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
            }
        });

        queue.add(stringRequest);
    }
}