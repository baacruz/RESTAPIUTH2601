package com.example.restapiuth2601;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapiuth2601.Models.Post;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String url = "https://jsonplaceholder.typicode.com/posts";
    private ArrayList<Post> listaPosts;
    private ListView listViewPosts;
    private ArrayAdapter<Post> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        listViewPosts = findViewById(R.id.listViewPosts);
        listaPosts = new ArrayList<>();
        
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaPosts);
        listViewPosts.setAdapter(adapter);

        obtenerPosts();
    }

    private void obtenerPosts() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new com.android.volley.Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray jsonArray) {
                        try {
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                int userId = object.getInt("userId");
                                int id = object.getInt("id");
                                String title = object.getString("title");
                                String body = object.getString("body");

                                Post post = new Post(userId, id, title, body);
                                listaPosts.add(post);
                            }
                            
                            adapter.notifyDataSetChanged();
                            Log.d("VOLLEY", "Post Cargados: " + listaPosts.size());

                        }catch (Exception ex){
                            ex.printStackTrace();
                        }

                    }
                }, volleyError -> {
                    Log.d("VOLLEY", "Error: " + volleyError.toString());
                    }
        );

        requestQueue.add(jsonArrayRequest);
    }
}
