package enfieldacademy.hearthstonecompanion.fragments;

import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import enfieldacademy.hearthstonecompanion.data.HearthstoneDbHelper;
import enfieldacademy.hearthstonecompanion.services.HearthstoneService;
import enfieldacademy.hearthstonecompanion.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private final String TAG = "MainActivityFragment";

    public static CustomAdapter adapter;
    public static ListView exampleListView;

    SQLiteDatabase database;

    HearthstoneService service;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_main, container, false);

        HearthstoneDbHelper dbHelper = new HearthstoneDbHelper(getActivity());

        database = dbHelper.getWritableDatabase();

        service = new HearthstoneService();

        exampleListView = (ListView) parentView.findViewById(R.id.exampleList);

        adapter = new CustomAdapter();

        return parentView;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        database.close();

    }

    public class CustomAdapter extends BaseAdapter {

        public CustomAdapter() {
            super();
        }

        @Override
        public Object getItem(int position) {
            if(HearthstoneService.allHearthstoneCards == null) return null;
            return HearthstoneService.allHearthstoneCards.get(position);
        }

        @Override
        public int getCount() {
            if(HearthstoneService.allHearthstoneCards == null) return 0;
            return HearthstoneService.allHearthstoneCards.size();
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) convertView = parent.getRootView();
            if(HearthstoneService.allHearthstoneCards == null) return null;
            TextView nameTV = (TextView) convertView.findViewById(R.id.name);
            if(nameTV == null) return null;
            nameTV.setText(HearthstoneService.allHearthstoneCards.get(position).getName());
            return convertView;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }
}
