package hk.hku.cs.hkudirectory;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ProfileActivity extends AppCompatActivity {

    private String clickedName;
    private String type_data;
    private String email_data;
    private String linkedin_data;
    private String location_data;

    List<Map<String, String>> queryData = new ArrayList<Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);


        Intent intent = getIntent();
        if (intent != null) {
            clickedName = intent.getStringExtra("name");
        }

        connectSQL sql = new connectSQL();
        String query = "SELECT * FROM people WHERE name = '" + clickedName + "'";
        sql.execute(query);

    };
    public class connectSQL extends AsyncTask<String, Void, List<Map<String, String>>> {
        private static final String url = "jdbc:mysql://nuc.hkumars.potatoma.com:3306/comp7506?useSSL=false&allowPublicKeyRetrieval=true";
        private static final String user = "potatoma";
        private static final String password = "potatoma123";



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(ProfileActivity.this, clickedName, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected List<Map<String, String>> doInBackground(String... params) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, user, password);

                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(params[0]);


                int i = 0;
                queryData.clear();
                while (rs.next()) {
                    queryData.add(new HashMap<String, String>());
                    queryData.get(i).put("type", rs.getString(3));
                    queryData.get(i).put("email", rs.getString(4));
                    queryData.get(i).put("linkedin", rs.getString(5));
                    queryData.get(i).put("location", rs.getString(6));
                    i++;
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
            return queryData;
        }

        @Override
        protected void onPostExecute(List<Map<String, String>> result) {

            try {
                TextView nameTextView = findViewById(R.id.text_name);
                nameTextView.setText(clickedName);
                if (result != null && !result.isEmpty()) {
                    Map<String, String> data = result.get(0);
                    TextView typeTextView = findViewById(R.id.text_type_data);
                    typeTextView.setText(data.get("type"));
                    TextView emailTextView = findViewById(R.id.text_email_data);
                    emailTextView.setText(data.get("email"));
                    TextView linkedinTextView = findViewById(R.id.text_text_linkedin_data);
                    linkedinTextView.setText(data.get("linkedin"));
                    TextView locationTextView = findViewById(R.id.text_text_location_data);
                    locationTextView.setText(data.get("location"));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    }

