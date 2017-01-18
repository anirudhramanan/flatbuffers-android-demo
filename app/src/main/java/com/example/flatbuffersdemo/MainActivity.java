package com.example.flatbuffersdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.flatbuffersdemo.flatbuffer.Event;
import com.example.flatbuffersdemo.flatbuffer.EventList;
import com.example.flatbuffersdemo.json.EventJson;
import com.example.flatbuffersdemo.json.EventListJson;
import com.google.gson.Gson;

import java.nio.ByteBuffer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private double jsonTime, flatBuffTime;
    private Gson gson;

    @NonNull
    private Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button parseUsingGson = (Button) findViewById(R.id.parsejson);
        Button parseUsingFlatBuffer = (Button) findViewById(R.id.parseflatbuff);
        final TextView jsonResultTextView = (TextView) findViewById(R.id.jsonresult);
        final TextView flatResultTextView = (TextView) findViewById(R.id.flatbufferresult);

        parseUsingGson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startTime = System.currentTimeMillis();
                EventListJson eventListJson = getGson().fromJson(Utils.readJSONFile(MainActivity.this), EventListJson.class);

                for (int i = 0; i < eventListJson.eventJsonArrayList.size(); i++) {
                    EventJson eventJson = eventListJson.eventJsonArrayList.get(i);
                    Log.d(TAG, eventJson.toString());
                }

                long endTime = System.currentTimeMillis();
                jsonTime = endTime - startTime;
                jsonResultTextView.setText("Results (Gson)\n\n" + "Total Size : " + eventListJson.eventJsonArrayList.size()
                        + "\nTotal Time : " + jsonTime + " ms");
            }
        });

        parseUsingFlatBuffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long startTime = System.currentTimeMillis();
                ByteBuffer byteBuffer = ByteBuffer.wrap(Utils.readFlatBuffFile(MainActivity.this));
                EventList eventList = EventList.getRootAsEventList(byteBuffer);

                for (int i = 0; i < eventList.eventLength(); i++) {
                    Event event = eventList.event(i);
                    Log.d(TAG, event.id() + "");
                }

                long endTime = System.currentTimeMillis();
                flatBuffTime = endTime - startTime;
                flatResultTextView.setText("Results (FlatBuffer)\n\n" + "Total Size : " + eventList.eventLength()
                        + "\nTotal Time : " + flatBuffTime + " ms");
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