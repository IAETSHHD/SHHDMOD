package com.example.mohamed.shhd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class missionhistoryActivity extends AppCompatActivity {
    ListView mylist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mission_history);
        mylist=(ListView)findViewById(R.id.listView);
        final ArrayList<item> countryinfo= new ArrayList<item>();
        String []countryarray=new String[7];
        countryarray[0]="Home light control";
        countryarray[1]="Guiding blinds";
        countryarray[2]="Mapping and security";
        countryarray[3]="wake up alarm";
        countryarray[4]="Taking to help deaf";
        countryarray[5]="Home light control";
        countryarray[6]="Take photos";

        String []capitalaarray=new String[7];
        capitalaarray[0]="home handler";
        capitalaarray[1]="Aiding disabled people";
        capitalaarray[2]="security";
        capitalaarray[3]="home handler";
        capitalaarray[4]="Aiding disabled people";
        capitalaarray[5]="home handler";
        capitalaarray[6]="home handler";
        for(int i=0;i<7;i++)
        {
            item myitem = new item();
            myitem.setCountry(countryarray[i]);
            myitem.setCapital(capitalaarray[i]);

            countryinfo.add(myitem);
        }

        customadpater myadapter=new customadpater(getApplicationContext(),countryinfo);
        mylist.setAdapter(myadapter);

        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String notify="Mission no. "+position+", "+countryinfo.get(position).getCountry();
                Toast.makeText(getApplicationContext(),notify,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
