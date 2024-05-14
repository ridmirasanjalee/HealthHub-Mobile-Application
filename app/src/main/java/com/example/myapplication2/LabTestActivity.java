package com.example.myapplication2;

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
import java.util.List;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages =
            {
                    {"Package 1: Full Body Checkup", "", "", "", "65500"},
                    {"Package 2: Blood Glucose Fasting", "", "", "", "1500"},
                    {"Package 3: Covid-19-Antibody", "", "", "", "3000"},
                    {"Package 4: Thyroid check", "", "", "", "25000"},
                    {"Package 5: Immunity check", "", "", "", "1500"},
            };

    private String[] package_details = {
            "Blood Glucose Fasting\n" +
                    "Complete Hemogram\n" +
                    "HbA1c\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LOH Lactate Dehydrogenase\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody",
            "Thyroid Profile-Total(13,14 & TSH Ultra-sensitive)",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitation, Serum\n" +
                    "Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Vitamin D Total-25 Hydroxy\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };

    SimpleAdapter sa;
    ListView listView;
    HashMap<String,String> item;
    Button btnGoToCart,btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lab_test);

        btnBack = findViewById(R.id.buttonLTBack);
        listView = findViewById(R.id.listviewLT);
        btnGoToCart=findViewById(R.id.buttonLTGotocart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        ArrayList<HashMap<String, String>> list = new ArrayList<>();
        for (int i = 0; i < packages.length; i++) {
            HashMap<String, String> item = new HashMap<>();
            item.put("Line1", packages[i][0]);
            item.put("Line2", "Description for " + packages[i][1]);
            item.put("Line3", "More Info for " + packages[i][2]);
            item.put("Line4", "Extra Info for " + packages[i][3]);
            item.put("Line5", "Total Cost: " + packages[i][4] + "/");
            list.add(item);
        }

        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(LabTestActivity.this, LabTestDetailActivity.class);
                it.putExtra("text1", packages[position][0]);
                it.putExtra("text2", package_details[position]);
                it.putExtra("text3", packages[position][4]);
                startActivity(it);
            }
        });
        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this,CartLabActivity.class));
            }
        });
    }
}





        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v,insets) -> {
            //Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            //v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            //return insets;
       // });
    //}*/
