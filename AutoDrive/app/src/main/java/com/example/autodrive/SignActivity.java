package com.example.autodrive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

public class SignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);

        TextView test = findViewById(R.id.test);
        Button accept = findViewById(R.id.btnAcceptSign);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText pas = findViewById(R.id.signPasswordField);
                EditText log = findViewById(R.id.signLoginField);

                postDataUsingVolley("http://192.168.1.9:3000/api/login", test,
                        log.getText().toString(),
                        pas.getText().toString());
            }
        });
    }

    private void postDataUsingVolley(String _url, TextView test, String login, String password) {
        // url to post our data
        String url = _url;

        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        // on below line we are calling a string
        // request method to post the data to our API
        // in this we are calling a post method.
        StringRequest request = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // inside on response method we are
                // hiding our progress bar
                // and setting data to edit text as empty

                // on below line we are displaying a success toast message.
                Toast.makeText(getApplicationContext(), "Data added to API", Toast.LENGTH_SHORT).show();
                try {
                    // on below line we are parsing the response
                    // to json object to extract data from it.
                    JSONObject respObj = new JSONObject(response);

                    // below are the strings which we
                    // extract from our json object.
                    int id = respObj.getInt("id");
                    String name = respObj.getString("name");
                    String surname = respObj.getString("surname");
                    String patronymic = respObj.getString("patronymic");
                    String number_phone = respObj.getString("number_phone");
                    String email = respObj.getString("email");
                    String pData = respObj.getString("p_data");

                    // on below line we are setting this string s to our text view.
                    test.setText("Name : " + name + "\n" + "Surname: " + surname);

                    ((UserModel)getApplication()).setId(id);
                    ((UserModel)getApplication()).setName(name);
                    ((UserModel)getApplication()).setSurname(surname);
                    ((UserModel)getApplication()).setPatronymic(patronymic);
                    ((UserModel)getApplication()).setPhone(number_phone);
                    ((UserModel)getApplication()).setEmail(email);
                    ((UserModel)getApplication()).setpData(pData);

                } catch (JSONException e) {
                    e.printStackTrace();
                    test.setText("Error : " + e.toString());
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // method to handle errors.
                Toast.makeText(getApplicationContext(), "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // below line we are creating a map for
                // storing our values in key and value pair.
                Map<String, String> params = new HashMap<String, String>();

                // on below line we are passing our key
                // and value pair to our parameters.
                params.put("login", login);
                params.put("password", password);

                // at last we are
                // returning our params.
                return params;
            }
        };
        // below line is to make
        // a json object request.
        queue.add(request);
    }

    public void httpCall(String url, TextView test) {

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // enjoy your response
                        test.setText(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // enjoy your error status
                test.setText(error.toString());
            }
        });

        queue.add(stringRequest);
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
                Intent intentCars = new Intent(this, CarsActivity.class);
                startActivity(intentCars);
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

}