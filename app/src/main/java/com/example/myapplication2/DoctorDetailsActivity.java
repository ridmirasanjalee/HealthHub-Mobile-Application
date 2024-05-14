 package com.example.myapplication2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

 public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
                    {"Doctor Name:Ajith Silva","Hospital Address:Roseth Hospital","Exp:5yrs","Mobile No:+947712345678","600"},
                    {"Doctor Name : Shantha Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
                    {"Doctor Name : Nethsara Gamage", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
                    {"Doctor Name : Prabath De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
                    {"Doctor Name : Ravindra Gamage", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

            };
    private String[][] doctor_details2=
            {
                    {"Doctor Name:Malithi Samarakjeewa","Hospital Address:Ruhunu Hospital","Exp:5yrs","Mobile No:+947712345678","600"},
                    {"Doctor Name : Sewwandi Balasooriya", "Hospital Address : Philip Hospital", "Exp : 10yrs", "Mobile No:+94776545453", "800"},
                    {"Doctor Name : Sandamali Athapaththu", "Hospital Address : Co-operative Hospital", "Exp : 2yrs", "Mobile No:+94778787678", "900"},
                    {"Doctor Name : Leonard Anuruddha", "Hospital Address :Navaloka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
                    {"Doctor Name : Jeewanthi Bandara", "Hospital Address : Co-operative Hospital", "Exp : 15yrs", "Mobile No:+94776545345", "600"}

            };
    private String[][] doctor_details3=
            {
                    {"Doctor Name:Sandaru Ravihara","Hospital Address:Kings Hospital","Exp:5yrs","Mobile No:+947712345678","600"},
                    {"Doctor Name : Chamindu Hansana", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
                    {"Doctor Name : Lihini Diwyangi", "Hospital Address :Nawaloka Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
                    {"Doctor Name : Manorika Priyadarshani", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
                    {"Doctor Name : Uvindu Abydewa", "Hospital Address : Asoka Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

            };
    private String[][] doctor_details4=
            {
                    {"Doctor Name:Omesha Viduranga","Hospital Address:philip Hospital","Exp:5yrs","Mobile No:+947712345678","600"},
                    {"Doctor Name : Sisil Dissanayake", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
                    {"Doctor Name : Sithara Gamage", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
                    {"Doctor Name : Dinesh De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
                    {"Doctor Name : Bandara Gamage", "Hospital Address : Ninesweals Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

            };
    private String[][] doctor_details5=
            {
                    {"Doctor Name: Namal Silva","Hospital Address:Hemas Thalawathugoda Hospital","Exp:5yrs","Mobile No:+947712345678","600"},
                    {"Doctor Name : Mishad Ahamad", "Hospital Address : Lanka Hospital", "Exp : 8yrs", "Mobile No:+94776545453", "800"},
                    {"Doctor Name : Shamila Nuwan", "Hospital Address : Co-operative Hospital", "Exp : 4yrs", "Mobile No:+94778787678", "900"},
                    {"Doctor Name : Thisari Ransala De Silva", "Hospital Address : Lanka Hospital", "Exp : 4yrs", "Mobile No:+94778984563", "700"},
                    {"Doctor Name : Sayona Madushani", "Hospital Address : Co-operative Hospital", "Exp : 5yrs", "Mobile No:+94776545345", "600"}

            };
     private TextView tv;
     private Button btn;
     private String[][] doctor_details = {};
     private ArrayList<HashMap<String, String>> list;
     private SimpleAdapter sa;

     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         EdgeToEdge.enable(this);
         setContentView(R.layout.activity_doctor_details);

         tv = findViewById(R.id.textViewDDtitle);
         btn = findViewById(R.id.buttonLTBack);

         Intent it = getIntent();
         String title = it.getStringExtra("title");
         tv.setText(title);

         switch (title) {
             case "Family Physicians":
                 doctor_details = doctor_details1;
                 break;
             case "Dietician":
                 doctor_details = doctor_details2;
                 break;
             case "Dentist":
                 doctor_details = doctor_details3;
                 break;
             case "Surgeon":
                 doctor_details = doctor_details4;
                 break;
             default:
                 doctor_details = doctor_details5;
                 break;
         }

         btn.setOnClickListener(v -> startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class)));

         ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
             Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
             return insets;
         });

         list = new ArrayList<>();
         for (String[] details : doctor_details) {
             HashMap<String, String> item = new HashMap<>();
             item.put("Line1", details[0]);
             item.put("Line2", details[1]);
             item.put("Line3", details[2]);
             item.put("Line4", details[3]);
             item.put("Line5", "Cons Fees: " + details[4] + "/=");
             list.add(item);
         }

         sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                 new String[]{"Line1", "Line2", "Line3", "Line4", "Line5"},
                 new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});

         ListView listView = findViewById(R.id.listviewDD);
         listView.setAdapter(sa);

         listView.setOnItemClickListener((adapterView, view, i, l) -> {
             Intent intent = new Intent(DoctorDetailsActivity.this,BookAppoinmentActivity.class);
             intent.putExtra("text1", title);
             intent.putExtra("text2", doctor_details[i][0]);
             intent.putExtra("text3", doctor_details[i][1]);
             intent.putExtra("text4", doctor_details[i][3]);
             intent.putExtra("text5", doctor_details[i][4]);
             startActivity(intent);
         });
     }
 }