package enfieldacademy.hearthstonecompanion.services;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import enfieldacademy.hearthstonecompanion.data.HearthstoneDbHelper;
import enfieldacademy.hearthstonecompanion.fragments.MainActivityFragment;
import enfieldacademy.hearthstonecompanion.models.HearthstoneCard;
import enfieldacademy.hearthstonecompanion.models.HearthstoneCardDeserializer;

public class HearthstoneService {

    private static final String TAG = "HearthstoneService";
    private static final String BASE_URL = "https://omgvamp-hearthstone-v1.p.mashape.com";
    private static final String ALL_CARDS = "/cards";

    private Context context;
    private List<HearthstoneCard> hearthstoneCards = new ArrayList<>();
    private MainActivityFragment.CardAdapter adapter;
    private HttpURLConnection connection;
    private InputStream inputStream;

    public HearthstoneService(Context context, MainActivityFragment.CardAdapter adapter){
        this.context = context;
        this.adapter = adapter;
        establishEndpoint(ALL_CARDS);
        performQueryToServer();
    }

    private void establishEndpoint(String query){
        URL endpoint;
        try {
            endpoint = new URL(BASE_URL + query);
            try {
                connection = (HttpURLConnection) endpoint.openConnection();
            } catch (IOException e){
                Log.d(TAG, "");
            }
            connection.setRequestProperty("X-Mashape-Key", "YRwqKcOdMemshyXps6jdQnyNU0eOp1MjDbYjsnWBXxLs8PT1li");
        } catch(MalformedURLException e){
            Log.d(TAG, "establishURL() failed");
        }
    }

    // returns JSON string response from server
    private void performQueryToServer(){
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                return createJSONString();
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                processResponse(response);
            }

        };
        task.execute();
    }

    private String createJSONString(){
        String response = "";
        try {
            inputStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder total = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
            response = total.toString();
        } catch(Exception e){
            Log.e(TAG, "Exception in getAllHearthstoneCards() - " + e.toString());
        } finally {
            connection.disconnect();
        }
        return response;
    }

    private void processResponse(final String response){
        AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                parseCardsFromResponse(response);
                populateInternalDatabase();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                updateGridView();
            }

        };
        task.execute();
    }

    private void parseCardsFromResponse(String response){
        try {
            JSONObject object = new JSONObject(response);

            for(int i = 0; i < object.length(); i++){
                JSONArray array = object.getJSONArray(getJSONArrayName(i));
                for(int j = 0; j < array.length(); j++){
                    JSONObject card = array.getJSONObject(j);
                    HearthstoneCardDeserializer deserializer = new HearthstoneCardDeserializer(card);
                    HearthstoneCard h = deserializer.getCard();
                    hearthstoneCards.add(h);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void populateInternalDatabase(){
        HearthstoneDbHelper dbHelper = new HearthstoneDbHelper(context);
        dbHelper.createCardBase(hearthstoneCards);
    }

    private void updateGridView(){
        adapter.hearthstoneCards = hearthstoneCards;
        adapter.notifyDataSetChanged();
    }


    private String getJSONArrayName(int position){
        switch(position){
            case 0:
                return "Basic";
            case 1:
                return "Classic";
            case 2:
                return "Credits";
            case 3:
                return "Naxxramas";
            case 4:
                return "Debug";
            case 5:
                return "Goblins vs Gnomes";
            case 6:
                return "Missions";
            case 7:
                return "Promotion";
            case 8:
                return "Reward";
            case 9:
                return "System";
            case 10:
                return "Blackrock Mountain";
            case 11:
                return "Hero Skins";
            case 12:
                return "Tavern Brawl";
            default:
                return "Basic";
        }
    }

}