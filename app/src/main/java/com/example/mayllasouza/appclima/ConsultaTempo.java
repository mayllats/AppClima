package com.example.mayllasouza.appclima;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.widget.EditText;
import android.widget.ImageView;

public class ConsultaTempo extends AppCompatActivity {

    Double latitude;
    Double longitude;

    ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void consultaServidor(View view) {
        latitude = Double.parseDouble(((EditText)findViewById(R.id.latitudeEditText)).getText().toString());
        longitude = Double.parseDouble(((EditText)findViewById(R.id.latitudeEditText)).getText().toString());

        RetrofitService.getServico().consulta(latitude, longitude).enqueue(new Callback<DadosClima>() {
            @Override
            public void onResponse(Call<DadosClima> call, Response<DadosClima> response) {
                int statusCode = response.code();
                String icon = response.body().getCurrently().getIcon();

                consultaTempo(icon);
            }
            @Override
            public void onFailure(Call<DadosClima> call, Throwable t) {
                Toast.makeText(ConsultaTempo.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void consultaTempo(String icon){
        imagem = (ImageView) findViewById(R.id.imageView);
        Log.i("teste", icon);

        switch (icon){
            case "clear-night":
                imagem.setImageResource(R.drawable.moon);
                break;
            case "rain":
                imagem.setImageResource(R.drawable.flash);
                break;
            case "snow":
                imagem.setImageResource(R.drawable.snowflake);
                break;
            case "sleet":
                imagem.setImageResource(R.drawable.sleet);
                break;
            case "wind":
                imagem.setImageResource(R.drawable.wind);
                break;
            case "cloudy":
                imagem.setImageResource(R.drawable.cloud);
                break;
            case "partly-cloudy-day":
                imagem.setImageResource(R.drawable.cloudysun);
                break;
            case "partly-cloudy-night":
                imagem.setImageResource(R.drawable.cloudymoon);
                break;
            case "clear-day":
                imagem.setImageResource(R.drawable.sunny);
                break;
            case "":
                imagem.setImageResource(R.drawable.cloudymoon);
                break;
        }
    }
}