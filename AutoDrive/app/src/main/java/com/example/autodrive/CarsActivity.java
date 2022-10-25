package com.example.autodrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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

public class CarsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cars);

        httpCall("http://192.168.1.9:3000/api/cars");
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
                Intent intentMainActivity = new Intent(this, MainActivity.class);
                startActivity(intentMainActivity);
                return true;
            case R.id.car_settings:
                return true;
            case R.id.my_room:
                Intent intentPA = new Intent(this, PersanalAreaActivity.class);
                startActivity(intentPA);
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
                                String photo = rec.getString("photo");
                                String price = rec.getString("price");
                                String countFree = rec.getString("count_free");
                                String result = "Название: " + name + "\n"
                                        + "Цена: " + price + "\n"
                                        + "Свободно: " + countFree;


                                TextView tv = new TextView(getApplicationContext());
                                tv.setText(result);
                                tv.setTextSize(20);
                                tv.setGravity(Gravity.CENTER);

                                byte[] decodedString = Base64.decode(photo, Base64.DEFAULT);
                                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                                ImageView img = new ImageView(getApplicationContext());
                                img.setImageBitmap(decodedByte);
                                img.setLayoutParams(new android.view.ViewGroup.LayoutParams(500,250));
                                img.setMaxHeight(20);
                                img.setMaxWidth(20);

                                Button btn = new Button(getApplicationContext());
                                btn.setText("Арендовать");
                                btn.setBackgroundColor(getResources().getColor(R.color.purple_500));
                                btn.setLayoutParams(new android.view.ViewGroup.LayoutParams(500,150));
                                btn.setTextSize(15);
                                btn.setTextColor(getResources().getColor(R.color.white));
                                //if(((UserModel)getApplication()).getName() == null){
                                 //   btn.setEnabled(false);
                                //}

                                LinearLayout ll = (LinearLayout) findViewById(R.id.listCar);
                                ll.setGravity(Gravity.CENTER_VERTICAL);
                                ll.setGravity(Gravity.CENTER_HORIZONTAL);
                                ll.addView(img);
                                ll.addView(tv);
                                ll.addView(btn);

                                TextView sep = new TextView(getApplicationContext());
                                sep.setText("*******************************************************");
                                sep.setTextSize(15);
                                sep.setGravity(Gravity.CENTER);
                                ll.addView(sep);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
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