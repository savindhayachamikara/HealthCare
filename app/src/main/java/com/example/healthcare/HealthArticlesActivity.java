package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class HealthArticlesActivity extends AppCompatActivity {

    private String[][] healthdetails =
            {
                    {"Walking Daily", "", "", "", "Click More Details"},
                    {"Home Care of COVID-19", "", "", "", "Click More Details"},
                    {"Stop Smoking", "", "", "", "Click More Details"},
                    {"Menstrual Cramps", "", "", "", "Click More Details"},
                    {"Healthy Gut", "", "", "", "Click More Details"},


            };
    private int[] images = {
            R.drawable.health1,
            R.drawable.health2,
            R.drawable.health3,
            R.drawable.health4,
            R.drawable.health5,
    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnBack;
    ListView lst;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_health_articles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lst = findViewById(R.id.ScrollViewHA);
        btnBack = findViewById(R.id.buttonHABack);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticlesActivity.this,HomeActivity.class));
            }
        });
        list = new ArrayList();
        for (int i = 0; i < healthdetails.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", healthdetails[i][0]);
            item.put("line2", healthdetails[i][1]);
            item.put("line3", healthdetails[i][2]);
            item.put("line4", healthdetails[i][3]);
            item.put("line5", healthdetails[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
        ListView lst = findViewById(R.id.ScrollViewHA);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(HealthArticlesActivity.this, HealthArticleDetailsActivity.class);
                it.putExtra("text1", healthdetails[i][0]);
                it.putExtra("text2",images[i]);
                startActivity(it);
            }
        });





    }
}