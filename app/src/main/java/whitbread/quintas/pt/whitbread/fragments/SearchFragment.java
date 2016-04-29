package whitbread.quintas.pt.whitbread.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import whitbread.quintas.pt.whitbread.R;
import whitbread.quintas.pt.whitbread.helpers.SystemConstants;

public class SearchFragment extends Fragment {

    private View mainView;
    private Button searchBtn;
    private EditText searchET;
    private Handler searchHandler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mainView = inflater.inflate(R.layout.fragment_search, container, false);
        initViews();

        return mainView;
    }

    public void setSearchHandler(Handler handler) {
        searchHandler = handler;
    }

    /**
     * Initializes all the graphical components as well as the click listeners
     * */
    private void initViews() {
        searchET = (EditText)mainView.findViewById(R.id.fragment_search_input);
        searchBtn = (Button)mainView.findViewById(R.id.fragment_search_btn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doSearch();
            }
        });
    }

    /**
     * Dismisses the fragment.
     * */
    public void dismissFragment() {
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.toast_fade_in, R.anim.toast_fade_out)
                .hide(this).commit();
        refreshFragmentState();
    }

    /**
     * Shows the fragment
     * */
    public void showFragment() {
        getFragmentManager().beginTransaction().setCustomAnimations(R.anim.toast_fade_in, R.anim.toast_fade_out)
                .show(this).commit();
    }

    /**
     * Refreshes the fragment state
     * */
    protected void refreshFragmentState() {
        searchET.setText("");
    }

    /**
     * Perform search
     * */
    private void doSearch() {
        if(searchHandler == null)
            return;
        Message msg = new Message();
        msg.what = SystemConstants.HANDLER_SEARCH;
        Bundle data = new Bundle();
        data.putString(SystemConstants.HANDLER_SEARCH_LOCATION, searchET.getText().toString());
        msg.setData(data);
        searchHandler.sendMessage(msg);
    }

}
