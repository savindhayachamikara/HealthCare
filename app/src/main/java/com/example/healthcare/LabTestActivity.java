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

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package 1 : Full Body Checkup", "", "", "", "15000"},
                    {"Package 2 : Blood Glucose Fasting", "", "", "", "5000"},
                    {"Package 3 : COVID-19 Checkup", "", "", "", "1000"},
                    {"Package 4 : Thyroid Check", "", "", "", "7000"},
                    {"Package 5 : Immunity Check", "", "", "", "8000"},

            };
    private String[] packagedetails ={
            "Blood Glucose Fasting\n" +
                    "Complete Hermogram\n" +
                    "HbAlc\n" +
                    "Iorn Studies\n" +
                    "Kindly Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Checkup",
            "Thyroid Profile-Total (T3, T4 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n " +
                    "Iron Studies\n " +
                    "Kidney Function Test\n " +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n " +
                    "Lipid Profile"




    };
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart, btnBack;
    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);
        btnBack = findViewById(R.id.buttonHABack);
        listView = findViewById(R.id.ScrollViewHA);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,HomeActivity.class));

            }
        });
        list = new ArrayList();
        for (int i = 0; i < packages.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:"+packages[i][4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
               R.layout.multilines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[] {R.id.linea, R.id.lineb, R.id.linec, R.id.lined, R.id.linee});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(LabTestActivity.this,LabTestDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packagedetails[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);


            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });


    }
}