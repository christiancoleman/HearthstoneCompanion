package enfieldacademy.hearthstonecompanion.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import enfieldacademy.hearthstonecompanion.data.HearthstoneContract.CardEntry;
import enfieldacademy.hearthstonecompanion.models.HearthstoneCard;

public class HearthstoneDbHelper extends SQLiteOpenHelper{

    private static final String TAG = "HearthstoneDbHelper";
    private static final int DATABASE_VERSION = 3;

    static final String DATABASE_NAME = "hearthstone.db";

    public HearthstoneDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_LOCATION_TABLE = "CREATE TABLE " + CardEntry.TABLE_NAME + " (" +
                CardEntry._ID + " INTEGER PRIMARY KEY , " +
                CardEntry.COLUMN_CUSTOM_ID + " TEXT UNIQUE NOT NULL, " +
                CardEntry.COLUMN_NAME + " TEXT, " +
                CardEntry.COLUMN_CARD_SET + " TEXT, " +
                CardEntry.COLUMN_TYPE + " TEXT, " +
                CardEntry.COLUMN_FACTION + " TEXT, " +
                CardEntry.COLUMN_RARITY + " TEXT, " +
                CardEntry.COLUMN_COST + " INTEGER, " +
                CardEntry.COLUMN_ATTACK + " INTEGER, " +
                CardEntry.COLUMN_HEALTH + " INTEGER, " +
                CardEntry.COLUMN_DURABILITY + " TEXT, " +
                CardEntry.COLUMN_TEXT + " TEXT, " +
                CardEntry.COLUMN_FLAVOR + " TEXT, " +
                CardEntry.COLUMN_ARTIST + " TEXT, " +
                CardEntry.COLUMN_COLLECTIBLE + " INTEGER, " + // technically a boolean but SQL stores booleans 1 or 0
                CardEntry.COLUMN_PLAYER_CLASS + " TEXT, " +
                CardEntry.COLUMN_HOW_TO_GET + " TEXT, " +
                CardEntry.COLUMN_HOW_TO_GET_GOLD + " TEXT, " +
                CardEntry.COLUMN_IMAGE + " TEXT, " +
                CardEntry.COLUMN_IMAGE_GOLD + " TEXT, " +
                CardEntry.COLUMN_LOCALE + " TEXT, " +
                CardEntry.COLUMN_NUMBER_OWNED + " TEXT, " +
                CardEntry.COLUMN_IMAGE_BLOB + " BLOB, " +
                CardEntry.COLUMN_IMAGE_BLOB_GOLD + " BLOB" +
                ");";

        db.execSQL(SQL_CREATE_LOCATION_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        // Note that this only fires if you change the version number for your database.
        // It does NOT depend on the version number for your application.
        // If you want to update the schema without wiping data, commenting out the next 2 lines
        // should be your top priority before modifying this method.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CardEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void createCardBase(List<HearthstoneCard> hearthstoneCards){

        Log.d(TAG, "createCardBase entered");
        int counter = 1;

        SQLiteDatabase db = this.getWritableDatabase();

        for(HearthstoneCard card : hearthstoneCards){

            Log.d(TAG, "counter = " + counter);
            counter++;

            ContentValues values = new ContentValues();

            values.put(CardEntry.COLUMN_CUSTOM_ID, card.getCardId());
            values.put(CardEntry.COLUMN_NAME, card.getName());
            values.put(CardEntry.COLUMN_CARD_SET, card.getCardSet());
            values.put(CardEntry.COLUMN_TYPE, card.getType());
            values.put(CardEntry.COLUMN_FACTION, card.getFaction());
            values.put(CardEntry.COLUMN_RARITY, card.getRarity());
            values.put(CardEntry.COLUMN_COST, card.getCost());
            values.put(CardEntry.COLUMN_ATTACK, card.getAttack());
            values.put(CardEntry.COLUMN_HEALTH, card.getHealth());
            values.put(CardEntry.COLUMN_DURABILITY, card.getDurability());
            values.put(CardEntry.COLUMN_TEXT, card.getText());
            values.put(CardEntry.COLUMN_FLAVOR, card.getFlavor());
            values.put(CardEntry.COLUMN_ARTIST, card.getArtist());
            values.put(CardEntry.COLUMN_COLLECTIBLE, card.isCollectible());
            values.put(CardEntry.COLUMN_PLAYER_CLASS, card.getPlayerClass());
            values.put(CardEntry.COLUMN_HOW_TO_GET, card.getHowToGet());
            values.put(CardEntry.COLUMN_IMAGE, card.getImage());
            values.put(CardEntry.COLUMN_IMAGE_GOLD, card.getImageGold());
            values.put(CardEntry.COLUMN_LOCALE, card.getLocale());
            values.put(CardEntry.COLUMN_NUMBER_OWNED, card.getNumberOwned());
            values.put(CardEntry.COLUMN_IMAGE_BLOB, card.getImageBlob());
            values.put(CardEntry.COLUMN_IMAGE_BLOB_GOLD, card.getImageBlobGold());

            db.insert(CardEntry.TABLE_NAME, null, values);

        }

        db.close();

    }
}
