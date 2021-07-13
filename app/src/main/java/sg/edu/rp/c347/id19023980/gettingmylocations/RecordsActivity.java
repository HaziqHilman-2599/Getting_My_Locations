package sg.edu.rp.c347.id19023980.gettingmylocations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class RecordsActivity extends AppCompatActivity {
    ArrayAdapter aa;
    ArrayList<String> al;
    Button btnRefresh;
    ListView lv;
    TextView tv;
    String folderLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        btnRefresh = findViewById(R.id.btnRefresh);
        tv = findViewById(R.id.tvRecords);
        lv = findViewById(R.id.lv);
        al = new ArrayList<>();
        tv.setText("Number of records: " + al.size());
        aa = new ArrayAdapter(RecordsActivity.this, android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                folderLocation = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Folder";
                File targetFile = new File(folderLocation,"data2.txt");
                if (targetFile.exists() == true){
                    String data = "";
                    try{
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while(line != null){
                            data += line +"\n";
                            al.add(line);
                            line = br.readLine();

                        }
                        br.close();
                        reader.close();
                    }catch (Exception e){
                        Toast.makeText(RecordsActivity.this,"Failed to read!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content",data);
                    tv.setText("Number of records: " + al.size());
                    aa.notifyDataSetChanged();
                }
            }
        });
    }
}