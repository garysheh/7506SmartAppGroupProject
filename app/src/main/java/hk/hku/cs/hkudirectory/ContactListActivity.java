package hk.hku.cs.hkudirectory;//package hk.hku.cs.hkudirectory;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
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

    String name;

    List<Map<String, String>> queryName = new ArrayList<Map<String, String>>(); //queryResult is stored in an arraylist consists of maps

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        mContext = ContactListActivity.this;
        final LayoutInflater inflater = LayoutInflater.from(this);
        View headView = inflater.inflate(R.layout.view_header, null, false);

        connectSQL sql = new connectSQL();
        sql.execute("SELECT * FROM people");


        list_c = (ListView) findViewById(R.id.contact_list_item_lv);
        list_c.addHeaderView(headView);



    }

    public class connectSQL extends AsyncTask<String, Void, List<Map<String, String>>> {
        private static final String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
        private static final String user = "potatoma";
        private static final String password = "potatoma123";



//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            Toast.makeText(ContactListActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
//        }

        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(params[0]);

                int i = 0;
                queryName.clear();
                while (rs.next()) {
                    queryName.add(new HashMap<String, String>());
                    queryName.get(i).put("ID", rs.getString(1));
                    queryName.get(i).put("name", rs.getString(2));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryName;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result) {
            mData = new LinkedList<Contact>();

            try {


                for (Map<String, String> map : result) {
                    String name = map.get("name");
                    mData.add(new Contact(name, ""));
                }

                mAdapter = new ContactAdapter((LinkedList<Contact>) mData, mContext);
                list_c.setAdapter(mAdapter);
                list_c.setOnItemClickListener(ContactListActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int clickedPosition = position - 1;
        if (clickedPosition >= 0 && clickedPosition < mData.size()) {
            String clickedName = mData.get(clickedPosition).getcName();
            //Toast.makeText(mContext, "Clicked name: " + clickedName, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
        intent.putExtra("name", clickedName);
        startActivity(intent);
    }
}
}

