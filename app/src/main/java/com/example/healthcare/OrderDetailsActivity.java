package com.example.healthcare;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
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

public class OrderDetailsActivity extends AppCompatActivity {

    private String[][] orderdetails = {};
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lst = (ListView) findViewById(R.id.ScrollViewHA);
        btn = (Button) findViewById(R.id.buttonHABack);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext(),"healthcare",null,1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderData(username);

        orderdetails = new String[dbData.size()][];
        for(int i=0;i<orderdetails.length;i++){
            orderdetails[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            orderdetails[i][0] = strData[0];
            orderdetails[i][1] = strData[1]; //+ strData[3];
            if(strData[7].compareTo("medicine") ==0){
                orderdetails[i][3] ="Del:"+strData[4];
            }else {
                orderdetails[i][3] = "Del:"+strData[4]+" "+strData[5];

            }
            orderdetails[i][2] = "Rs.:"+strData[6];
            orderdetails[i][4] = strData[2];



        }

        list = new ArrayList();
        for(int i=0;i<orderdetails.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",orderdetails[i][0]);
            item.put("line2",orderdetails[i][1]);
            item.put("line3",orderdetails[i][2]);
            item.put("line4",orderdetails[i][3]);
            item.put("line5",orderdetails[i][4]);
            list.add(item);


        }
        sa = new SimpleAdapter(this,list,
                R.layout.multilines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.linee});
        lst.setAdapter(sa);



    }
}