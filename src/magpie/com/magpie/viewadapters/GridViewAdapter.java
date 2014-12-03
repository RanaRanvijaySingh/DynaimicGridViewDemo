package magpie.com.magpie.viewadapters;

import java.util.List;

import magpie.com.magpie.MainActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class GridViewAdapter extends BaseAdapter {

	private MainActivity mContext;
	private List<View> listLayouts;
	
	public GridViewAdapter(MainActivity mContext,List<View> listLayouts){
		this.mContext = mContext;
		this.listLayouts = listLayouts;
	}
	
	@Override
	public int getCount() {
		return listLayouts.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = listLayouts.get(position);
		return view;
	}

}
