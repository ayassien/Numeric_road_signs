package org.whitetech.nrs.numeric_road_signs;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.whitetech.nrs.numeric_road_signs.LangItem;
import org.whitetech.nrs.numeric_road_signs.SignData;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String FileId = SelectedLangFile.Id;
    EditText area;
    EditText sign;
    TextView signText;
    ArrayList<SignData> signDataArray;

boolean x = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        area = findViewById(R.id.areaedittext);
        sign = findViewById(R.id.signnumedittext);
        signText = findViewById(R.id.signText);

        signDataArray = new ArrayList<SignData>();
        Button done = findViewById(R.id.done);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // file to inputstream
                InputStream input = null;
                try {
                    input = getAssets().open(FileId);

                    // myData.txt can't be more than 2 gigs.
                    int size = input.available();
                    byte[] buffer = new byte[size];
                    input.read(buffer);
                    input.close();

                    // byte buffer into a string
                    String text = new String(buffer);
                    Log.e("texxxt",text);


                    JSONObject langData = new JSONObject(text);
                    int lid = Integer.valueOf( SelectedLangFile.Id);
                    if(langData.getInt("langId") == lid ){   //lang id from activity 1

                        JSONArray signData = langData.getJSONArray("Areas");
                        for (int i = 0; i < signData.length(); i++) {

                            JSONObject signObj = signData.getJSONObject(i);
                            int areaId =  signObj. getInt("AreaId");
                            if(areaId == Integer.parseInt(area.getText().toString())) {

                                JSONArray signsarray = signObj.getJSONArray("Signs");
                                for (int j = 0; j < signsarray.length(); j++) {
                                    SignData signDataObj = new SignData(signsarray.getJSONObject(j).getInt("id"), signsarray.getJSONObject(j).getString("txt"));

                                    if(signDataObj.getId() == Integer.parseInt(sign.getText().toString())){
                                        signText.setText(signDataObj.getText());
x = true;
                                    }
                                }
                            }
//                    signDataArray.add(signDataObj);
//                   if(signDataObj.getId() == Integer.parseInt(area.getText().toString())){
//
//
//
//                   }
                        }


                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
