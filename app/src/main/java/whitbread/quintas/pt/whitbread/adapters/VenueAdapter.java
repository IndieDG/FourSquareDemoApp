package whitbread.quintas.pt.whitbread.adapters;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;

import java.util.List;

import whitbread.quintas.pt.whitbread.R;
import whitbread.quintas.pt.whitbread.model.Venue;

public class VenueAdapter extends ArrayAdapter<Venue> {

    private Activity mActivity;
    private int layoutId;
    List<Venue> mVenues = null;

    public VenueAdapter(Activity mActivity, int layoutId, List<Venue> homePosts) {
        super(mActivity, layoutId, homePosts);
        this.mActivity = mActivity;
        this.layoutId = layoutId;
        this.mVenues = homePosts;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VenueHolder holder;
        final Venue venue = mVenues.get(position);

        LayoutInflater inflater = mActivity.getLayoutInflater();
        convertView = inflater.inflate(layoutId, parent, false);

        holder = new VenueHolder();
        holder.name = (TextView) convertView.findViewById(R.id.venue_name);
        holder.location = (TextView) convertView.findViewById(R.id.venue_location);

        holder.name.setText(venue.getName());
        String address = "";
        if(venue.getLocation().getAddress() != null)
            address = venue.getLocation().getAddress() + ", ";
        if(venue.getLocation().getCity() != null)
            address += venue.getLocation().getCity();
        if(!address.isEmpty())
            holder.location.setText(address);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + venue.getLocation().getLat() + "," + venue.getLocation().getLng()));
                mActivity.startActivity(intent);
            }
        });

        convertView.setTag(holder);

        return convertView;
    }

    @Override
    public int getCount() {
        if(mVenues != null)
            return mVenues.size();
        return 0;
    }

    public void setVenues(List<Venue> venues) {
        mVenues = venues;
    }

    private static class VenueHolder {
        TextView name;
        TextView location;
    }

}
