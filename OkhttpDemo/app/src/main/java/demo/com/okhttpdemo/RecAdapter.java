package demo.com.okhttpdemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by ff on 2017/2/11.
 */
public class RecAdapter extends RecyclerView.Adapter<RecAdapter.MyHolder> implements View.OnClickListener{

    private Context context;

    private OkHttpClient client;

    private List<Meizi> meizis;

    private RecyclerView recyclerView;

    public RecAdapter(Context context,List<Meizi> meizis,RecyclerView recyclerView){
        this.context = context;
        this.meizis = meizis;
        this.recyclerView = recyclerView;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.line_meizi_item,parent,false);

        MyHolder holder = new MyHolder(view);

        view.setOnClickListener(this);

        client = new OkHttpClient();
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.tv.setText("图"+position);

        Request request = new Request.Builder()
                .url(meizis.get(position).getUrl())
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Bitmap bitmap = BitmapFactory.decodeStream(response.body().byteStream());
                holder.iv.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    public int getItemCount() {
        return meizis.size();
    }

    @Override
    public void onClick(View v) {
        int position = recyclerView.getChildAdapterPosition(v);
        Toast.makeText(context,"点击"+position+"个",Toast.LENGTH_SHORT).show();
    }

    public void addItem(Meizi meizi,int position){
        meizis.add(position,meizi);
        notifyItemInserted(position);
        recyclerView.scrollToPosition(position);
    }

    public void removeItem(final int position){
        final Meizi removed = meizis.get(position);
        meizis.remove(position);
        notifyItemInserted(position);
        Toast.makeText(context,"删除"+position+"个",Toast.LENGTH_SHORT).show();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        private TextView tv;;
        public MyHolder(View itemView) {
            super(itemView);
            iv=(ImageView) itemView.findViewById(R.id.line_item_iv);
            tv = (TextView) itemView.findViewById(R.id.line_item_tv);
        }
    }
}
