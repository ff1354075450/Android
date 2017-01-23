package demo.com.myfragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by ff on 2016/12/19.
 */

public class ListFragmentD extends ListFragment{
    String[] presidents={
            "Dwight D. Eisenhower",
            "Jon F. Kennedy ",
            "Lyndon B. jonhson",
            "Richard Nixon",
            "Gerald Ford",
            "Jimmy Carter",
            "Ronald Reagan",
            "Georage H. W. Bush",
            "Bill Clinton",
            "George W. Bush",
            "Barack Obama"
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,presidents));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(getActivity(), "you have selected "+presidents[position], Toast.LENGTH_SHORT).show();
        //改变activity中的值
        ((Button) getActivity().findViewById(R.id.left_frg_button2)).setText(presidents[position]);
    }
}
