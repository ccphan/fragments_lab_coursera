package course.labs.fragmentslab;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FeedFragment extends Fragment {

	private static final String TAG = "FeedFragment";

	private TextView mTextView;

	private int mPosition = -1;
	private static FeedFragmentData feedFragmentData;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.feed, container, false);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Read in all Twitter feeds
		if (null == feedFragmentData) {

			feedFragmentData = new FeedFragmentData(getActivity());

		}

		// display the last saved feed before configuration change
		if (savedInstanceState != null) {
			int position = savedInstanceState.getInt("position");
			Log.e(TAG, "Position is " + position);

			if (position >= 0) {

				updateFeedDisplay(position);
			}
		}

	}

	// Display Twitter feed for selected feed

	void updateFeedDisplay(int position) {

		Log.i(TAG, "Entered updateFeedDisplay()");

		mPosition = position;
		mTextView = (TextView) getView().findViewById(R.id.feed_view);
		mTextView.setText(feedFragmentData.getFeed(position));

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		Log.d(TAG, "onSaveInstanceState");
		// track the feed position chosen prior to any configuration changes so we can instate
		outState.putInt("position", mPosition);
	}
}
