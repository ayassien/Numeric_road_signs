package org.whitetech.nrs.numeric_road_signs;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import org.whitetech.nrs.numeric_road_signs.LangItem;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.widget.AdapterView.OnItemClickListener;

public class Config extends AppCompatActivity {


        ListView lv;
        SearchView sv;
        ArrayList<String> langNames;
        ArrayList<LangItem> langItems;
        Button submit;
        String[] teams = {"Man Utd", "Man City", "Chelsea", "Arsenal", "Liverpool", "Totenham"};
        ArrayAdapter<String> adapter;
        String text = "";
        ArrayList<LangItem> filteredList;
    EditText Lang;
        int index;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config);
        langItems = new ArrayList<LangItem>();
        langNames = new ArrayList<String>();
        submit = (Button) findViewById(R.id.submitBtn);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Config.this, MainActivity.class);


               Lang = findViewById(R.id.langtxt);
                SelectedLangFile.Id = Lang.getText().toString();
                if(!(SelectedLangFile.Id == null ||  SelectedLangFile.Id == ""))
                    startActivity(i);
                else
                    Log.e("index", SelectedLangFile.Id );
            }
        });
        // file to inputstream
        InputStream input = null;
        try {
            input = getAssets().open("lang_list.txt");

            // myData.txt can't be more than 2 gigs.
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);


            // byte buffer into a string
            text = new String(buffer);
            input.close();
            Log.e("texxxt", text);


            JSONArray langData = new JSONArray(text);
            for (int i = 0; i < langData.length(); i++) {

                JSONObject langObj = langData.getJSONObject(i);
                LangItem langItem = new LangItem(langObj.getInt("id"), langObj.getString("Name"), langObj.getString("EnName"), langObj.getString("ArName"));
                langNames.add(langItem.getName());
                langItems.add(langItem);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get ata from file


        lv = (ListView) findViewById(R.id.listView1);
        sv = (SearchView) findViewById(R.id.searchView1);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, langNames);

        lv.setAdapter(adapter);
            lv.setOnItemClickListener(new  OnItemClickListener() {
                public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
                {
                    SelectedLangFile.Id = String.valueOf(itemId + 201);
                    Log.e("index", SelectedLangFile.Id );
                }
            });




        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String text) {
                // TODO Auto-generated method stub

                return false;
            }

            @Override
            public boolean onQueryTextChange(String textt) {

                adapter.getFilter().filter(textt);
                index = findElement(textt);

//               filteredList = new ArrayList<LangItem>();
//                for (int i = 0; i < langItems.size(); i++) {
//                    String filterableString = langItems.get(i).getName();
//                    if (filterableString.toLowerCase().contains(text)) {
//                        filteredList.add(langItems.get(i));
//                        index = i;
//                    }
//
//
//                }

                return false;
            }


        });





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("index", langItems.get(index).getName());
            }
        });


    }




        public int findElement(String textt) {

        // assume that arr is the language array
        int searchResult = -1;
        for( int i=0, c = langNames.size(); i< c; i++)
        {
            if(langNames.get(i) == textt)
            {
                searchResult = i;
            }

        }

        return searchResult;
    }

    }




//lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    @Override
//    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//
//    }
//});
//    }



