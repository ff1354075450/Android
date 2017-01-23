package self.ff.account.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.json.JSONArray;

/**
 * Created by ff on 2017/1/21.
 */

public class WorkInfoAdapter extends BaseAdapter{

    private JSONArray jsonArray;
    public WorkInfoAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public int getCount() {
        return jsonArray.length();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
