package enfieldacademy.hearthstonecompanion.services;

import android.app.ProgressDialog;
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

import enfieldacademy.hearthstonecompanion.MyApplicationConstants;
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
    private ProgressDialog progressDialog;

    public HearthstoneService(final Context context, MainActivityFragment.CardAdapter adapter){
        this.context = context;
        this.adapter = adapter;
        AsyncTask<Void, Void, Void> checkDB = new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "Please wait", "Checking database...");
            }

            @Override
            protected Void doInBackground(Void... params) {
                hearthstoneCards = getCardsFromDatabase();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                if(hearthstoneCards.isEmpty()) {
                    Log.d(TAG, "######### --- Querying web server...");
                    establishEndpoint(ALL_CARDS);
                    performQueryToServer();
                } else {
                    Log.d(TAG, "######### --- Using internal database...");
                    updateGridView();
                }
            }

        };
        checkDB.execute();
    }

    private List<HearthstoneCard> getCardsFromDatabase(){
        HearthstoneDbHelper dbHelper = new HearthstoneDbHelper(context);
        hearthstoneCards = dbHelper.getCardBase();
        return hearthstoneCards;
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
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "Please wait", "Downloading cards...");
            }

            @Override
            protected String doInBackground(Void... params) {
                return createJSONString();
            }

            @Override
            protected void onPostExecute(String response) {
                super.onPostExecute(response);
                progressDialog.dismiss();
                processResponse(response);
            }

        };
        task.execute();
    }

    private String createJSONString(){
        String response = "";
        try {
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
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
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = ProgressDialog.show(context, "Please wait", "Downloading images...");
            }

            @Override
            protected Void doInBackground(Void... params) {
                parseCardsFromResponse(response);
                populateInternalDatabase();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                updateGridView();
            }

        };
        task.execute();
    }

    private void parseCardsFromResponse(String response){
        try {
            JSONObject object = new JSONObject(response);

            for(int i = 0; i < object.length(); i++){
                JSONArray array = object.getJSONArray(MyApplicationConstants.getJSONArrayName(i));
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
        dbHelper.close();
    }

    private void updateGridView(){
        adapter.hearthstoneCards = hearthstoneCards;
        adapter.notifyDataSetChanged();
    }

}