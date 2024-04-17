package hk.hku.cs.hkudirectory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button testMapRedirect = null;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maplist);
        tType = findViewById(R.id.type1);
        tName = findViewById(R.id.name1);
        tLocation = findViewById(R.id.location1);

        tType2 = findViewById(R.id.type2);
        tName2 = findViewById(R.id.name2);
        tLocation2 = findViewById(R.id.location2);

        connectSQL sql = new connectSQL();
        sql.execute("SELECT * FROM people");

        testMapRedirect = (Button) findViewById(R.id.location1);
        testMapRedirect.setOnClickListener(new View.OnClickListener() {
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
                    queryResult.get(i).put("type", rs.getString(3));
                    queryResult.get(i).put("name", rs.getString(2));
                    queryResult.get(i).put("location", rs.getString(6));
                    i++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryResult;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result) {
            //param: result contains records returned from database
            try {

                String type = result.get(0).get("type");
                String name = result.get(0).get("name");
                String location = result.get(0).get("location");

                String type2 = result.get(1).get("type");
                String name2 = result.get(1).get("name");
                String location2 = result.get(1).get("location");

                tType.setText(type);
                tName.setText(name);
                tLocation.setText(location);

                tType2.setText(type2);
                tName2.setText(name2);
                tLocation2.setText(location2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}