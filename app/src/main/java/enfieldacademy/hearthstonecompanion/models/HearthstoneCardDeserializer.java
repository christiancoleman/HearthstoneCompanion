package enfieldacademy.hearthstonecompanion.models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import enfieldacademy.hearthstonecompanion.data.HearthstoneContract.CardEntry;

public class HearthstoneCardDeserializer{

    private static final String TAG = "CardDeserializer";
    private static final String EMPTY = "";
    private static final int ZERO = 0;
    private static final boolean FALSE = false;
    private HearthstoneCard h;

    public HearthstoneCardDeserializer(JSONObject card){

        h = new HearthstoneCard();

        try {
            h.setCardId(card.getString(CardEntry.COLUMN_CUSTOM_ID));
        } catch (JSONException e){
            h.setCardId(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_CUSTOM_ID + " not found");
        }

        try {
            h.setName(card.getString(CardEntry.COLUMN_NAME));
        } catch (JSONException e){
            h.setName(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_NAME + " not found");
        }

        try{
            h.setCardSet(card.getString(CardEntry.COLUMN_CARD_SET));
        } catch (JSONException e){
            h.setCardSet(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_CARD_SET + " not found");
        }

        try {
            h.setType(card.getString(CardEntry.COLUMN_TYPE));
        } catch (JSONException e){
            h.setType(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_TYPE + " not found");
        }

        try {
            h.setFaction(card.getString(CardEntry.COLUMN_FACTION));
        } catch (JSONException e){
            h.setFaction(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_FACTION + " not found");
        }

        try {
            h.setRarity(card.getString(CardEntry.COLUMN_RARITY));
        } catch (JSONException e){
            h.setRarity(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_RARITY + " not found");
        }

        try {
            h.setCost(card.getInt(CardEntry.COLUMN_COST));
        } catch (JSONException e){
            h.setCost(ZERO);
            Log.d(TAG, CardEntry.COLUMN_COST + " not found");
        }

        try {
            h.setAttack(card.getInt(CardEntry.COLUMN_ATTACK));
        } catch (JSONException e){
            h.setAttack(ZERO);
            Log.d(TAG, CardEntry.COLUMN_ATTACK + " not found");
        }

        try {
            h.setHealth(card.getInt(CardEntry.COLUMN_HEALTH));
        } catch (JSONException e){
            h.setHealth(ZERO);
            Log.d(TAG, CardEntry.COLUMN_HEALTH + " not found");
        }

        try {
            h.setDurability(card.getString(CardEntry.COLUMN_DURABILITY));
        } catch (JSONException e){
            h.setDurability(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_DURABILITY + " not found");
        }

        try {
            h.setText(card.getString(CardEntry.COLUMN_TEXT));
        } catch (JSONException e){
            h.setText(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_TEXT + " not found");
        }

        try {
            h.setFlavor(card.getString(CardEntry.COLUMN_FLAVOR));
        } catch (JSONException e){
            h.setFlavor(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_FLAVOR + " not found");
        }

        try {
            h.setArtist(card.getString(CardEntry.COLUMN_ARTIST));
        } catch (JSONException e){
            h.setArtist(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_ARTIST + " not found");
        }

        try {
            h.setCollectible(card.getBoolean(CardEntry.COLUMN_COLLECTIBLE));
        } catch (JSONException e){
            h.setCollectible(FALSE);
            Log.d(TAG, CardEntry.COLUMN_COLLECTIBLE + " not found");
        }

        try {
            h.setPlayerClass(card.getString(CardEntry.COLUMN_PLAYER_CLASS));
        } catch (JSONException e){
            h.setPlayerClass(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_PLAYER_CLASS + " not found");
        }

        try {
            h.setHowToGet(card.getString(CardEntry.COLUMN_HOW_TO_GET));
        } catch (JSONException e){
            h.setHowToGet(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_HOW_TO_GET + " not found");
        }

        try {
            h.setImage(card.getString(CardEntry.COLUMN_IMAGE));
        } catch (JSONException e){
            h.setImage(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_IMAGE + " not found");
        }

        try {
            h.setImageGold(card.getString(CardEntry.COLUMN_IMAGE_GOLD));
        } catch (JSONException e){
            h.setImageGold(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_IMAGE_GOLD + " not found");
        }

        try {
            h.setLocale(card.getString(CardEntry.COLUMN_LOCALE));
        } catch (JSONException e){
            h.setLocale(EMPTY);
            Log.d(TAG, CardEntry.COLUMN_LOCALE + " not found");
        }

    }

    public HearthstoneCard getCard() {
        return h;
    }

}
