package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaplistActivity extends AppCompatActivity {
    TextView  tType, tName, tLocation;
    TextView  tType2, tName2, tLocation2;

    TextView  tType3, tName3, tLocation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button testMapRedirect = null;
        Button testMapRedirect2 = null;
        Button testMapRedirect3 = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist);
        tType = findViewById(R.id.type1);
        tName = findViewById(R.id.name1);
        tLocation = findViewById(R.id.location1);

        tType2 = findViewById(R.id.type2);
        tName2 = findViewById(R.id.name2);
        tLocation2 = findViewById(R.id.location2);

        tType3 = findViewById(R.id.type3);
        tName3 = findViewById(R.id.name3);
        tLocation3 = findViewById(R.id.location3);

        connectSQL sql = new connectSQL();
        sql.execute("SELECT * FROM class");

        testMapRedirect = (Button) findViewById(R.id.location1);
        testMapRedirect2 = (Button) findViewById(R.id.location2);
        testMapRedirect3 = (Button) findViewById(R.id.location3);
        LatLng coordinates;
        testMapRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MaplistActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        testMapRedirect2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MaplistActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        testMapRedirect3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MaplistActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });
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
                    queryResult.get(i).put("Instructor", rs.getString(2));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryResult;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result) {
            try {
                for (int i = 0; i < result.size(); i++) {
                    String type = result.get(i).get("id");
                    String name = result.get(i).get("Room");
                    String location = result.get(i).get("Instructor");

                    if (i == 0) {
                        tType.setText(type);
                        tName.setText(name);
                        tLocation.setText(location);
                    } else if (i == 1) {
                        tType2.setText(type);
                        tName2.setText(name);
                        tLocation2.setText(location);
                    } else if (i == 2) {
                        tType3.setText(type);
                        tName3.setText(name);
                        tLocation3.setText(location);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}