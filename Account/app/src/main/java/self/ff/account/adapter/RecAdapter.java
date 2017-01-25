package self.ff.account.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import self.ff.account.R;

/**
 * Created by ff on 2017/1/23.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.ViewHolder>{

    private JSONArray jsonArray;
    public RecAdapter(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public RecAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.work_info,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecAdapter.ViewHolder holder, int position) {
        String name="";
        int days=0;
        String kind="";
        try {
            JSONObject json = (JSONObject) jsonArray.get(position);
            name = json.getString("worker");
            days = json.getInt("days");
            kind = json.getString("kind");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        holder.tvname.setText(name);
        holder.tvdays.setText(days);
        holder.kind.setText(kind);
    }

    @Override
    public int getItemCount() {
        return jsonArray.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView tvname;
        public TextView tvdays;
        public TextView kind;
        public ViewHolder(View itemView) {
            super(itemView);
            tvname = (TextView) itemView.findViewById(R.id.workname);
            tvdays = (TextView) itemView.findViewById(R.id.workdays);
            kind = (TextView) itemView.findViewById(R.id.kind);
        }
    }

}
