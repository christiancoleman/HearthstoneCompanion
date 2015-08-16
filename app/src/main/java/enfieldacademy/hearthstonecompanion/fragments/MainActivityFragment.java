package enfieldacademy.hearthstonecompanion.fragments;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import enfieldacademy.hearthstonecompanion.data.HearthstoneDbHelper;
import enfieldacademy.hearthstonecompanion.models.HearthstoneCard;
import enfieldacademy.hearthstonecompanion.services.HearthstoneService;
import enfieldacademy.hearthstonecompanion.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String TAG = "MainActivityFragment";

    private List<HearthstoneCard> hearthstoneCards;

    SQLiteDatabase database;

    HearthstoneService service;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_main, container, false);

        HearthstoneDbHelper dbHelper = new HearthstoneDbHelper(getActivity());

        hearthstoneCards = new ArrayList<>();
        CardAdapter adapter = new CardAdapter(hearthstoneCards);
        GridView cardView = (GridView) parentView.findViewById(R.id.exampleList);
        cardView.setAdapter(adapter);

        service = new HearthstoneService(adapter);

        dbHelper.createCardBase(hearthstoneCards);

        return parentView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        database.close();

    }

    public class CardAdapter extends BaseAdapter {

        public List<HearthstoneCard> hearthstoneCards;

        public CardAdapter(List<HearthstoneCard> hearthstoneCards) {
            super();
            this.hearthstoneCards = hearthstoneCards;
        }

        @Override
        public Object getItem(int position) {
            if(hearthstoneCards == null) return null;
            return hearthstoneCards.get(position);
        }

        @Override
        public int getCount() {
            if(hearthstoneCards == null) return 0;
            return hearthstoneCards.size();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_card, null);
            }

            TextView nameTV = (TextView) convertView.findViewById(R.id.card_name);
            ImageView imageIV = (ImageView) convertView.findViewById(R.id.card_image);

            if(nameTV == null || imageIV == null) return null;

            String cardName = hearthstoneCards.get(position).getName();
            String cardImage = hearthstoneCards.get(position).getImage();

            nameTV.setText(cardName);

            if(cardImage == null || cardImage.equals("")) return convertView;
            Picasso.with(getActivity())
                    .load(cardImage)
                    .resize(246, 372)
                    .centerCrop()
                    .into(imageIV);

            return convertView;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }
}
