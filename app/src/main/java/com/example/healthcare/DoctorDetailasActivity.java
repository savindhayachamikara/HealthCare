package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailasActivity extends AppCompatActivity {
    private String[][] doctordetails1 = {
            {"Doctor Name : Kamal Perera","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0771234567","600"},
            {"Doctor Name : Nimal Perera","Hospital address : Gampha", "Exp : 3Years", "Mobile No: 0771535567","900"},
            {"Doctor Name : Udesh Wijewardhana","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0774564567","300"},
            {"Doctor Name : Amantha Gimhan","Hospital address : Kandy", "Exp : 4Years", "Mobile No: 0771896567","500"},
            {"Doctor Name : Chamika Dilshan","Hospital address : Colombo", "Exp : 2Years", "Mobile No: 0774775567","800"},
    };
    private String[][] doctordetails2 = {
            {"Doctor Name : Pramodika Dilhara","Hospital address : Baththaramulla", "Exp : 5Years", "Mobile No: 0771234567","600"},
            {"Doctor Name : Kavindra Reshan","Hospital address : Gampha", "Exp : 3Years", "Mobile No: 0771535567","900"},
            {"Doctor Name : Malsha Prabashwara","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0774564567","300"},
            {"Doctor Name : Saduni lanka","Hospital address : Kandy", "Exp : 4Years", "Mobile No: 0771896567","500"},
            {"Doctor Name : Charith Athapaththu","Hospital address : Colombo", "Exp : 2Years", "Mobile No: 0774775567","800"},
    };
    private String[][] doctordetails3 = {
            {"Doctor Name : Savindya Chamikara","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0771234567","600"},
            {"Doctor Name : Nimal Perera","Hospital address : Gampha", "Exp : 3Years", "Mobile No: 0771535567","900"},
            {"Doctor Name : Udesh Imalka","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0774564567","300"},
            {"Doctor Name : Sanduni Nisansala","Hospital address : Kandy", "Exp : 4Years", "Mobile No: 0771896567","500"},
            {"Doctor Name : Chamikara Herath","Hospital address : Colombo", "Exp : 2Years", "Mobile No: 0774775567","800"},
    };
    private String[][] doctordetails4 = {
            {"Doctor Name : Kamal Perera","Hospital address : Kurunegala", "Exp : 5Years", "Mobile No: 0771234567","600"},
            {"Doctor Name : Nimal Perera","Hospital address : Gampha", "Exp : 3Years", "Mobile No: 0771535567","900"},
            {"Doctor Name : Shehan Bhanuka","Hospital address : Negambo", "Exp : 5Years", "Mobile No: 0774564567","300"},
            {"Doctor Name : Amantha Gimhan","Hospital address : Kandy", "Exp : 4Years", "Mobile No: 0771896567","500"},
            {"Doctor Name : Amal Perera","Hospital address : Colombo", "Exp : 2Years", "Mobile No: 0774775567","800"},
    };
    private String[][] doctordetails5 = {
            {"Doctor Name : Kamal Perera","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0771234567","600"},
            {"Doctor Name : Sandun Perera","Hospital address : Gampha", "Exp : 3Years", "Mobile No: 0771535567","900"},
            {"Doctor Name : Udesh Wijewardhana","Hospital address : Colombo", "Exp : 5Years", "Mobile No: 0774564567","300"},
            {"Doctor Name : Amantha Gimhan","Hospital address : Kandy", "Exp : 4Years", "Mobile No: 0771896567","500"},
            {"Doctor Name : Yasiru Vishan","Hospital address : Colombo", "Exp : 2Years", "Mobile No: 0774775567","800"},
    };
     TextView tv;
     Button btn;

     String[][] doctordetails = {};

     HashMap<String,String> item;

     ArrayList list;
     SimpleAdapter sa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_doctor_detailas);

        tv = findViewById(R.id.listViewHATitle);
        btn = findViewById(R.id.buttonHABack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv.setText(title);
        if (title.compareTo("Family Physician")==0)
            doctordetails = doctordetails1;
        else
        if (title.compareTo("Dietician")==0)
            doctordetails = doctordetails2;
        else
        if (title.compareTo("Dentist")==0)
            doctordetails = doctordetails3;
        else
        if (title.compareTo("Surgeon")==0)
            doctordetails = doctordetails4;
        else
            doctordetails = doctordetails5;



            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailasActivity.this,FindDoctorActivity.class));
            }
        });

            list = new ArrayList();
            for (int i = 0; i < doctordetails.length; i++) {
                item = new HashMap<String, String>();
                item.put("line1", doctordetails[i][0]);
                item.put("line2", doctordetails[i][1]);
                item.put("line3", doctordetails[i][2]);
                item.put("line4", doctordetails[i][3]);
                item.put("line5", "Cons Fees:"+doctordetails[i][4]+"/-");
                list.add(item);

            }
            sa = new SimpleAdapter(this,list,
                    R.layout.multilines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.linea,R.id.lineb,R.id.linec,R.id.lined,R.id.linee}
            );
        ListView lst = findViewById(R.id.ScrollViewHA);
        lst.setAdapter(sa) ;

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent it = new Intent(DoctorDetailasActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                it.putExtra("text2",doctordetails[i][0]);
                it.putExtra("text3",doctordetails[i][1]);
                it.putExtra("text4",doctordetails[i][3]);
                it.putExtra("text5",doctordetails[i][4]);
                startActivity(it);



            }
        });

    }
}