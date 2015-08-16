package enfieldacademy.hearthstonecompanion.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

public class HearthstoneContract {

    // The "Content authority" is a name for the entire content provider, similar to the
    // relationship between a domain name and its website.  A convenient string to use for the
    // content authority is the package name for the app, which is guaranteed to be unique on the
    // device.
    //public static final String CONTENT_AUTHORITY = "enfieldacademy.sunshine";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    //public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible paths (appended to base content URI for possible URI's)
    // For instance, content://com.example.android.sunshine.app/weather/ is a valid path for
    // looking at weather data. content://com.example.android.sunshine.app/givemeroot/ will fail,
    // as the ContentProvider hasn't been given any information on what to do with "givemeroot".
    // At least, let's hope not.  Don't be that dev, reader.  Don't be that dev.
    //public static final String PATH_CARDS = "cards";

    /*
        Inner class that defines the table contents of the cards table
        Students: This is where you will add the strings.
     */
    public static final class CardEntry implements BaseColumns {
        //public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CARDS).build();

        //public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARDS;

        //public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_CARDS;

        //public static Uri buildCardUri(long id) {
        //    return ContentUris.withAppendedId(CONTENT_URI, id);
        //}

        public static final String TABLE_NAME = "cards";

        public static final String COLUMN_CUSTOM_ID = "cardId";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_CARD_SET = "cardSet";

        public static final String COLUMN_TYPE = "type";

        public static final String COLUMN_FACTION = "faction";

        public static final String COLUMN_RARITY = "rarity";

        public static final String COLUMN_COST = "cost";

        public static final String COLUMN_ATTACK = "attack";

        public static final String COLUMN_HEALTH = "health";

        public static final String COLUMN_DURABILITY = "durability";

        public static final String COLUMN_TEXT = "text";

        public static final String COLUMN_FLAVOR = "flavor";

        public static final String COLUMN_ARTIST = "artist";

        public static final String COLUMN_COLLECTIBLE = "collectible";

        public static final String COLUMN_PLAYER_CLASS = "playerClass";

        public static final String COLUMN_HOW_TO_GET = "howToGet";

        public static final String COLUMN_HOW_TO_GET_GOLD = "howToGetGold";

        public static final String COLUMN_IMAGE = "img";

        public static final String COLUMN_IMAGE_GOLD = "imgGold";

        public static final String COLUMN_LOCALE = "locale";

        public static final String COLUMN_NUMBER_OWNED = "numberOwned";

        public static final String COLUMN_IMAGE_BLOB = "imgBlob";

        public static final String COLUMN_IMAGE_BLOB_GOLD = "imgBlobGold";
    }

}
