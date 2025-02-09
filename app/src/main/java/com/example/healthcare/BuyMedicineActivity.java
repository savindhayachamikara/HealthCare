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

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages = {
            {"Uprise-D3 1000IU Capsule","","","","50"},
            {"HealthVit C 500mg Capsule","","","","75"},
            {"Vitamin B Complex Capsule","","","","100"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule","","","","100"},
            {"Dolo 650 Tablet","","","","20"},
            {"Crocin 650 Advance Tablet","","","","30"},
            {"Strepsils Medicated Capsule","","","","100"},
            {"Tata 1mg Calcium + Vitamin D3","","","","100"},
            {"Feronia -XT Tablet","","","","100"},

    };

    private String[] packagedetails = {
            "Building and Keeping the bones & teeth Strong\n"+
                    "Reducing Fatigue/stress and muscular pains "+
                    "Boosting immunity and increasing resistance against infections ",
            "Chromium is an essential trace mineral that plays an important role in the formation of red blood cells",
            "Provides relief from vitamin B deficiencies\n"+
                    "Helps in formation of red blood cells "+
                    "Maintains healthy nervous system",
            "It promotes health as well as skin benefit.\n"+
                    "It helps in formation of red blood cells.\n"+
                    "It act as a supplement to many vitamins and minerals.",
            "Dolo 650 Tablet helps relieve mild to moderate pain ",
            "Helps relieve fever and mild to moderate pain\n "+
                    "Suitable for people with heart condition",
            "Relieves toothache and counter treats cavities\n"+
                    "Provides healthy smiles",
            "Reduces the risk of calcium deficiency, which increases risk of heart disease\n"+
                    "Provides relief from vitamin D deficiency",
            "Helps to reduce iron deficiency due to chronic blood loss or low intake of iron"

    };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lst = findViewById(R.id.ScrollViewHA);
        btnBack = findViewById(R.id.buttonHABack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });

        list = new ArrayList();
        for (int i=0;i<packages.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5","Total Cost:"+packages[i][4]+"/-");
            list.add(item);


        }

        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.linee});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[i][0]);
                it.putExtra("text2",packagedetails[i]);
                it.putExtra("text3",packages[i][4]);
                startActivity(it);
            }

        });


    }
}