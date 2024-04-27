package hk.hku.cs.hkudirectory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

public class mapListAdapter extends BaseAdapter {
    private LinkedList<Maplist> mData;
    private Context mContext;

    public mapListAdapter(LinkedList<Maplist> mapData, Context mapContext) {
        this.mData = mapData;
        this.mContext = mapContext;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(mContext).inflate(R.layout.maplist_content,parent,false);
        TextView txt_cclass = (TextView) convertView.findViewById(R.id.text_class);
        txt_cclass.setText(mData.get(position).getMclass());
        return convertView;
    }
}
