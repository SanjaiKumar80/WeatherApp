package com.app.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.weatherapp.Retrofit.ApiClient;
import com.app.weatherapp.Retrofit.ApiInterface;
import com.app.weatherapp.Retrofit.Example;

import javax.security.auth.login.LoginException;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView image;
    TextView TempText, TextDescription, Humidity;
    EditText objtextField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        image = findViewById(R.id.search);
        TempText = findViewById(R.id.tempText);
        TextDescription = findViewById(R.id.descText);
        Humidity = findViewById(R.id.humidityText);
        objtextField = findViewById(R.id.textField);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Place = objtextField.getText().toString().trim();
                getWeatherData(Place);
            }
        });
    }

    private void getWeatherData(String name) {
          final String ApiKey="6127f835a551f810f3af7591bddda894";
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiInterface.getWeatherData(ApiKey,name);

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {

                TempText.setText("Temp" + " " + response.body().getMain().getTemp() + " C");
                TextDescription.setText("Feels Like" + " " + response.body().getMain().getFeels_like());
                Humidity.setText("Humidity" + " " + response.body().getMain().getHumidity());


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}