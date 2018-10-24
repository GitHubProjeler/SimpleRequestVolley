package com.example.fatih.simplerequestvolley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button get_data;
    String url = "http://ios-android.trkaynak.com/dosya.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        get_data = (Button) findViewById(R.id.button);
        get_data.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button){

            //İsteklerin işleneceği sınıf
            final RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

            //url bilgisi verilen adresten string veri alınır.
            StringRequest sr = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),"Gelen Veri : "+response,Toast.LENGTH_LONG).show();

                            //istek kapatılır.
                            requestQueue.stop();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),"Hata oluştu..",Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                    requestQueue.stop();
                }
            });

            //add() komutuyla yukarıda verilen StringRequest isteği işlenir ve sunucudan veri alınır.
            //Bu yazılmazda herhangi bir işlem yapılmaz.
            requestQueue.add(sr);

        }

    }
}
