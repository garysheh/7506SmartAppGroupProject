package hk.hku.cs.hkudirectory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button mapListPage = null; // the button for maplist
        Button contactPage = null; // the button for contact page
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_page);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//        return insets
        mapListPage = (Button) findViewById(R.id.map_button);
        contactPage = (Button) findViewById(R.id.contact_button);
        mapListPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(HomePageActivity.this, MaplistActivity.class);
                startActivity(intent);
            }
        });

        contactPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getBaseContext(), ContactListActivity.class);
//                ArrayList<String> contact_name = new ArrayList<String>();
//
//                contact_name.add("Eason Chan");
//                contact_name.add("Judy");
//                contact_name.add("Ken");
//                contact_name.add("Mary");
//
//                intent.putStringArrayListExtra("contact_name", contact_name);
                startActivity(intent);
            }
        });
        };
    }
