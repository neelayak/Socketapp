package net.iquesoft.socketapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        new Videodetails(this).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class Videodetails extends AsyncTask<Void, Void, Void> {
        ArrayList<Videohelper> arrayList = new ArrayList<>();
        Context c;

        public Videodetails(Context context) {
            c = context;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://interview-e18de.firebaseio.com/media.json?print=pretty";
            URLConnection urlConn = null;
            BufferedReader bufferedReader = null;
            try {
                URL urls = new URL(url);
                urlConn = urls.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                StringBuffer stringBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuffer.append(line);
                }


                JSONArray jArr = new JSONArray(stringBuffer.toString());
                for (int count = 0; count < jArr.length(); count++) {
                    JSONObject obj = jArr.getJSONObject(count);
                    String name = obj.getString("description");
                    String imageName = obj.getString("thumb");
                    String link = obj.getString("url");
                    String id = obj.getString("id");
                    String title = obj.getString("title");
                    Videohelper videohelper = new Videohelper();
                    videohelper.setDescription(name);
                    videohelper.setId(id);
                    videohelper.setThumb(imageName);
                    videohelper.setUrl(link);
                    videohelper.setTitle(title);
                    Log.e("title", title);
                    Log.e("title2", link);
                    arrayList.add(videohelper);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.e("Size", arrayList.size() + "");
            FrontAdapter frontAdapter = new FrontAdapter(c, R.layout.custom1, arrayList, 1);
            listView.setAdapter(frontAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this, Main3Activity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("Birds", arrayList);
                    intent.putExtras(bundle);
                    intent.putExtra("position", position);
                    startActivity(intent);
                }
            });
        }
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
