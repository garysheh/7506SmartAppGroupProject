package hk.hku.cs.hkudirectory;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

public class ContactAdapter extends BaseAdapter {

    private LinkedList<Contact> mData;
    private Context mContext;

    public ContactAdapter(LinkedList<Contact> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
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
        convertView = LayoutInflater.from(mContext).inflate(R.layout.contact_list_item,parent,false);
//        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView txt_cName = (TextView) convertView.findViewById(R.id.text_name);
//        TextView txt_cEmail = (TextView) convertView.findViewById(R.id.text_email);
//        img_icon.setBackgroundResource(mData.get(position).getaIcon());
        txt_cName.setText(mData.get(position).getcName());
//        txt_cEmail.setText(mData.get(position).getcEmail());
        return convertView;
    }
}

