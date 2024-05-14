package com.example.myapplication2;

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

public class OrderDetailsAcitivity extends AppCompatActivity {


    private String[][] order_details = {};

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details_acitivity);

        btn = findViewById(R.id.buttonODBack);
        lst = findViewById(R.id.listView0D);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(OrderDetailsAcitivity.this,HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext());

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        ArrayList dbData = db.getOrderdata(username);

        order_details = new String[dbData.size()][];
        for(int i=0;i<order_details.length;i++){
            order_details[i]=new String[5];
            String arrData = dbData.get(i).toString();
            String[]strData =arrData.split(java.util.regex.Pattern.quote("$"));
            order_details[i][0] = strData[0];
            order_details[i][1] = strData[1];
            if(strData[7].compareTo("medicine")==0){
                order_details[i][3] = "Del"+strData[4];
            }else {
                order_details[i][3] = "Del"+strData[4]+""+strData[5];
            }
            order_details[i][2] = "Rs."+strData[6];
            order_details[i][4] = strData[7];
        }

        list = new ArrayList();
        for(int i=0;i<order_details.length;i++){
            item = new HashMap<String,String>();
            item.put("Line1",order_details[i][0]);
            item.put("Line2",order_details[i][1]);
            item.put("Line3",order_details[i][2]);
            item.put("Line4",order_details[i][3]);
            item.put("Line5",order_details[i][4]);
            list.add(item);
        }

        sa=new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"Line1","Line2","Line3","Line4","Line5"},
                new int[]{R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});
        ListView lst = findViewById(R.id.listView0D);
        lst.setAdapter(sa);

    }
}