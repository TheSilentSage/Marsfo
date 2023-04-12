package com.example.marsfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ArrayList<MarsImage> marsImagesList = new ArrayList<>();
    MarsImageAdapter marsImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_activity);

        RecyclerView recyclerView = findViewById(R.id.imagerecyclerView);
        LinearLayoutManager llm = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        marsImageAdapter = new MarsImageAdapter(this,marsImagesList);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(marsImageAdapter);
        search();
        getSupportActionBar().hide();


    }


    public  void search(){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                // Background Work
                try {
                    callImageApi();
                } catch (JSONException | MalformedURLException e) {
                    throw new RuntimeException(e);
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        marsImageAdapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
    private void callImageApi() throws JSONException, MalformedURLException {
        URL url;

        {
            try {
                url = new URL("https://api.nasa.gov/mars-photos/api/v1/rovers/Curiosity/photos?sol=1000&page=1&api_key=DEMO_KEY");
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }

        HttpURLConnection urlConnection;

        {
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        {
            BufferedReader in;
            try {
                in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String inputLine;
            StringBuilder response = new StringBuilder();

            while (true) {
                try {
                    if ((inputLine = in.readLine()) == null) break;
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                response.append(inputLine);
            }
            try {
                in.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
//            Log.d("Apireq", "Response: " + response);
            urlConnection.disconnect();
            parseJson(response.toString());
            Log.d("Apireq", "Response: " + marsImagesList.toString());
        }
    }

    private void parseJson(String jsonString) throws JSONException, MalformedURLException {
        JSONObject root = new JSONObject(jsonString);
        JSONArray photosArray = root.getJSONArray("photos");
        for (int i = 0; i < photosArray.length(); i++) {
            JSONObject photoObject = photosArray.getJSONObject(i);
            JSONObject photoCameraObject = photoObject.getJSONObject("camera");
            JSONObject photoRoverObject = photoObject.getJSONObject("rover");
            String solDay = photoObject.getString("sol");
            String image_url = photoObject.getString("img_src").replace("http","https");
            String earthDate = photoObject.getString("earth_date");
            String cameraName = photoCameraObject.getString("name");
            String fullCameraName = photoCameraObject.getString("full_name");
            String roverName = photoRoverObject.getString("name");

            MarsImage marsImage = new MarsImage(solDay,cameraName,fullCameraName,image_url,earthDate,roverName);
            marsImagesList.add(marsImage);
        }
    }
}