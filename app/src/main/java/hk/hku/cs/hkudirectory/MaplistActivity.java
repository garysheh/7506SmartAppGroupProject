package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MaplistActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private List<Maplist> mapData = null;
    private Context mapContext;
    private mapListAdapter mapAdapter = null;
    private ListView list_map;

    List<Map<String, String>> queryName = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist);
        mapContext = MaplistActivity.this;
        final LayoutInflater inflater = LayoutInflater.from(this);
        MaplistActivity.connectSQL sql = new MaplistActivity.connectSQL();
        sql.execute("SELECT * FROM class");
        list_map = (ListView) findViewById(R.id.map_list_item);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int clickedPosition = position;
        if (clickedPosition >= 0 && clickedPosition < mapData.size()) {
            String clickedRoom = mapData.get(clickedPosition).getMclass();
            Toast.makeText(mapContext, "Clicked class: " + clickedRoom, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getBaseContext(), MapActivity.class);
            startActivity(intent);
        }
    }


    public class connectSQL extends AsyncTask<String, Void, List<Map<String, String>>> {
        private static final String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
        private static final String user = "potatoma";
        private static final String password = "potatoma123";

        List<Map<String, String>> queryResult = new ArrayList<Map<String, String>>(); //queryResult is stored in an arraylist consists of maps

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(MaplistActivity.this, "Please wait...", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(params[0]);

                int i = 0;
                queryResult.clear();
                while (rs.next()) {
                    queryResult.add(new HashMap<String, String>());
                    queryResult.get(i).put("id", rs.getString(1));
                    queryResult.get(i).put("Room", rs.getString(3));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryResult;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result) {
            mapData = new LinkedList<Maplist>();

            try {
                for (Map<String, String> map : result) {
                    String id = map.get("id");
                    mapData.add(new Maplist(id, ""));

                }
                mapAdapter = new mapListAdapter((LinkedList<Maplist>) mapData, mapContext);
                list_map.setAdapter(mapAdapter);
                list_map.setOnItemClickListener(MaplistActivity.this);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}