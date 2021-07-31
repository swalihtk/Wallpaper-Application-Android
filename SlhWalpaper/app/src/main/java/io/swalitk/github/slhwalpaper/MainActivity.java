package io.swalitk.github.slhwalpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<DataHandler> dataHandlerList;
    DataHandler dataHandler;
    SwipeRefreshLayout refreshLayout;
    ListView listView;
    WallpaperAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataHandlerList=new ArrayList<>();
        refreshLayout=findViewById(R.id.refreshList);
        listView=findViewById(R.id.all_wallpaper_listView);

        loadData("First");

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData("Refresh");
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(MainActivity.this, ViewWallpaper.class);
                intent.putExtra("title", dataHandlerList.get(position).getTitle());
                intent.putExtra("imageUrl", dataHandlerList.get(position).getImage());
                startActivity(intent);
            }
        });

    }

    private void loadData(String type){
        refreshLayout.setRefreshing(true);
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        StringRequest request=new StringRequest(Request.Method.GET, "https://slhwalpaper.herokuapp.com/api/walpaper.json", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                refreshLayout.setRefreshing(false);
                parseJson(response, type);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                refreshLayout.setRefreshing(false);
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(request);
    }

    private void parseJson(String response, String type) {

        if(type.equals("Refresh")){
            dataHandlerList.clear();
            adapter.notifyDataSetChanged();
        }

        try{
            JSONArray jsonArray=new JSONArray(response);
            for(int i=0; i<jsonArray.length(); i++){
                JSONObject jsonObject=jsonArray.getJSONObject(i);
                dataHandlerList.add(new DataHandler(jsonObject.getString("title"), jsonObject.getString("thumbnail"), jsonObject.getString("image")));
            }
            adapter=new WallpaperAdapter(getApplicationContext(), R.layout.image_list, dataHandlerList);
            listView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}