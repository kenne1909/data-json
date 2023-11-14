package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvSP;

    ArrayList<item> arrayTittle;

    itemadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvSP=(ListView) findViewById(R.id.lvsanpham);
        arrayTittle =new ArrayList<>();
        adapter=new itemadapter(MainActivity.this, R.layout.item,arrayTittle);
        lvSP.setAdapter(adapter);
        new ReadJSON().execute("https://dummyjson.com/products");
    }

    private class ReadJSON extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {

            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return content.toString();
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject object=new JSONObject(s);

                JSONArray array= object.getJSONArray("products");
                for(int i=0;i<array.length();i++){
                    JSONObject objectpr=array.getJSONObject(i);
                    String title=objectpr.getString("title");
                    String description=objectpr.getString("description");
                    String imageUrl = objectpr.getString("thumbnail");
                    String price=objectpr.getString("price");
                    String brand=objectpr.getString("brand");
                    String category=objectpr.getString("category");
                    //Toast.makeText(MainActivity.this, ten, Toast.LENGTH_SHORT).show();
                    //ImageView imageView = (ImageView) findViewById(R.id.imageViewhinh);
                    //Picasso.get().load(imageUrl).into(imageView);
                    arrayTittle.add(new item(title,description,"$"+price,brand,category,imageUrl));
                }
                adapter.notifyDataSetChanged();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }
    }


}