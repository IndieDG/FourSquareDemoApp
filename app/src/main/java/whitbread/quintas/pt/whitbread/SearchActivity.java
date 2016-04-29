package whitbread.quintas.pt.whitbread;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.List;

import whitbread.quintas.pt.whitbread.adapters.VenueAdapter;
import whitbread.quintas.pt.whitbread.assync_tasks.SearchVenueTask;
import whitbread.quintas.pt.whitbread.fragments.SearchFragment;
import whitbread.quintas.pt.whitbread.helpers.SystemConstants;
import whitbread.quintas.pt.whitbread.model.VenueRequest;

public class SearchActivity extends AppCompatActivity {

    private ListView searchLV;
    private ImageView searchToggle;
    private SearchFragment searchFragment;
    private View searchLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();

        doSearch(getResources().getString(R.string.location_search_default));
    }

    private void initViews() {
        searchLV = (ListView)findViewById(R.id.search_venue_lv);
        searchToggle = (ImageView)findViewById(R.id.search_toggle);
        searchFragment = (SearchFragment)getFragmentManager().findFragmentById(R.id.search_fragment);
        searchLoading = findViewById(R.id.search_loading);

        getFragmentManager().beginTransaction().hide(searchFragment).commit();
        searchFragment.setSearchHandler(new SearchHandler());

        searchToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchFragment.isVisible())
                    searchFragment.dismissFragment();
                else
                    searchFragment.showFragment();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(searchFragment.isVisible()) {
            searchFragment.dismissFragment();
            return;
        }
        finish();
    }

    public void searchVenueTaskCallback(VenueRequest venueRequest) {
        if(searchLV.getAdapter() == null) {
            VenueAdapter adapter = new VenueAdapter(this, R.layout.layout_venue_list, venueRequest.getResponse().getVenues());
            searchLV.setAdapter(adapter);
        }
        else if(searchLV.getAdapter() instanceof VenueAdapter){
            ((VenueAdapter)searchLV.getAdapter()).setVenues(venueRequest.getResponse().getVenues());
            ((VenueAdapter)searchLV.getAdapter()).notifyDataSetChanged();
        }
        searchLoading.setVisibility(View.GONE);
    }

    public void showLoading() {
        searchLoading.setVisibility(View.VISIBLE);
    }

    private void doSearch(String nearLoctation) {
        if(nearLoctation == null)
            return;
        if(nearLoctation.isEmpty())
            return;
        SearchVenueTask searchVenueTask = new SearchVenueTask(this, nearLoctation);
        searchVenueTask.execute();


        if(searchFragment.isVisible())
            searchFragment.dismissFragment();
    }

    private class SearchHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == SystemConstants.HANDLER_SEARCH) {
                if (msg.getData() != null) {
                    Bundle data = msg.getData();
                    doSearch(data.getString(SystemConstants.HANDLER_SEARCH_LOCATION));
                }
            }
        }
    }
}
