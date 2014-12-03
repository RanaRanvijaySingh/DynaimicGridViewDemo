package magpie.com.magpie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import magpie.com.magpie.viewadapters.GridViewAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private static final int FIRST_GRID_VIEW = 1;
	private static final int SECOND_GRID_VIEW = FIRST_GRID_VIEW + 1;
	private final int MINIMUM_NO_OF_COLUMNS = 3;
	private int numberOfServices = 8;
	private LinearLayout llGridViewHolder;
	private List<View> listViews;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initializeComponents();
	}

	private void initializeComponents() {
		llGridViewHolder= (LinearLayout) findViewById(R.id.llGridViewHolder);
		listViews = getListOfViewsFrom(numberOfServices);
		if (listViews.size() > 0) {
			initializeGridView(llGridViewHolder,listViews);
		}
		
	}

	private List<View> getListOfViewsFrom(int numberOfServices) {
		List<View> listViews = new ArrayList<View>();
		if (numberOfServices > 0) {
			LayoutInflater inflater = (LayoutInflater)this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			for (int i = 0; i < numberOfServices; i++) {
				View view= inflater.inflate(R.layout.view_item, null);
				listViews.add(view);
			}	
		}
		return listViews;
	}

	private void initializeGridView(LinearLayout llGridViewHolder, List<View> listViews) {
		GridView gridViewFirst = new GridView(MainActivity.this);
		GridView gridViewSecond = null;
		int numberOfServices = listViews.size();
		if (numberOfServices >= MINIMUM_NO_OF_COLUMNS) {
			gridViewFirst.setNumColumns(MINIMUM_NO_OF_COLUMNS);
			int numberOfRows = numberOfServices / MINIMUM_NO_OF_COLUMNS;
			gridViewFirst.setAdapter(new GridViewAdapter(this, getViewListFrom(listViews,numberOfRows * MINIMUM_NO_OF_COLUMNS ,FIRST_GRID_VIEW)));
			int numberOfColumnSecondView = numberOfServices % MINIMUM_NO_OF_COLUMNS;
			if (numberOfColumnSecondView > 0) {
				gridViewSecond = new GridView(MainActivity.this);
				gridViewSecond.setNumColumns(numberOfColumnSecondView);
				gridViewSecond.setAdapter(new GridViewAdapter(this, getViewListFrom(listViews,numberOfColumnSecondView ,SECOND_GRID_VIEW)));
			}
		}else {
			gridViewFirst.setNumColumns(numberOfServices);
			gridViewFirst.setAdapter(new GridViewAdapter(this, listViews));
		}
		llGridViewHolder.addView(gridViewFirst);
		if (gridViewSecond != null ) {
			llGridViewHolder.addView(gridViewSecond);
		}
	}

	private List<View> getViewListFrom(List<View> listViews, int count,int gridView) {
		List<View> listViewsFiltered = new ArrayList<View>();
		if (listViews.size() > 0) {
			if (gridView == FIRST_GRID_VIEW) {
				for (int i = 0; i < count; i++) {
					listViewsFiltered.add(listViews.get(i));
				}
			}else{
				Collections.reverse(listViews);
				for (int i = 0; i < count; i++) {
					listViewsFiltered.add(listViews.get(i));
				}
			}
		}
		return listViewsFiltered;
	}
}
