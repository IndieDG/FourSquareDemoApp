package whitbread.quintas.pt.whitbread.assync_tasks;

import android.app.Activity;
import android.os.AsyncTask;

import java.io.IOException;

import whitbread.quintas.pt.whitbread.SearchActivity;
import whitbread.quintas.pt.whitbread.connection.RemoteExecutor;
import whitbread.quintas.pt.whitbread.helpers.SystemConstants;
import whitbread.quintas.pt.whitbread.model.VenueRequest;

public class SearchVenueTask extends AsyncTask<Void, Void, VenueRequest> {

    private Activity mActivity;
    private String nearLocation;

    public SearchVenueTask(Activity activity, String nearLocation) {
        mActivity = activity;
        this.nearLocation = nearLocation;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(mActivity instanceof SearchActivity)
            ((SearchActivity)mActivity).showLoading();
    }

    @Override
    protected VenueRequest doInBackground(Void... params) {
        String url = SystemConstants.ENDPOINT_FOURSQUARE_VENUE_SEARCH
                + SystemConstants.ENDPOINT_FOURSQUARE_CLIENT_ID
                + SystemConstants.FOURSQUARE_CLIENT_ID
                + SystemConstants.ENDPOINT_FOURSQUARE_CLIENT_SECRET
                + SystemConstants.FOURSQUARE_CLIENT_SECRET
                + SystemConstants.ENDPOINT_VERSION
                + SystemConstants.ENDPOINT_FOURSQUARE_NEAR
                + nearLocation;
                //+ SystemConstants.ENDPOINT_FOURSQUARE_LL
                //+ "40.7463956,-73.9852992";
        try {
            return RemoteExecutor.getInstance().executeGet(url);
        } catch (IOException e) {
            //ignore
        }
        return null;
    }

    @Override
    protected void onPostExecute(VenueRequest venues) {
        super.onPostExecute(venues);
        if(mActivity instanceof SearchActivity)
            ((SearchActivity)mActivity).searchVenueTaskCallback(venues);
    }
}
