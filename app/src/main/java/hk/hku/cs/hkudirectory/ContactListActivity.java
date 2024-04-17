package hk.hku.cs.hkudirectory;//package hk.hku.cs.hkudirectory;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ContactListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<Contact> mData = null;
    private Context mContext;
    private ContactAdapter mAdapter = null;
    private ListView list_c;
    private LinearLayout ly_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        mContext = ContactListActivity.this;
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.view_header, null, false);


        list_c = (ListView) findViewById(R.id.contact_list_item_lv);
        list_c.addHeaderView(headView);

        mData = new LinkedList<Contact>();
        mData.add(new Contact("Eason Chan", "test1"));
        mData.add(new Contact("Jacky Cheung", "2"));
        mData.add(new Contact("Jay Chou", "3"));
        mData.add(new Contact("Hins Cheung", "4"));
        mData.add(new Contact("You", "5"));
        mAdapter = new ContactAdapter((LinkedList<Contact>) mData, mContext);


        list_c.setAdapter(mAdapter);
        list_c.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(mContext,"Loading..." ,Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
        startActivity(intent);
    }
}

