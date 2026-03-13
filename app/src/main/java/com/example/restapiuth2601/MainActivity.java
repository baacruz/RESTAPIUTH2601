package com.example.restapiuth2601;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.restapiuth2601.Configuracion.ApiConfig;
import com.example.restapiuth2601.Configuracion.Personas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Personas> listaPersonas;
    private ListView listViewPersonas;
    private PersonaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Referencia al ListView en activity_main.xml
        listViewPersonas = findViewById(R.id.listViewPosts);
        listaPersonas = new ArrayList<>();
        
        adapter = new PersonaAdapter(this, listaPersonas);
        listViewPersonas.setAdapter(adapter);

        obtenerPersonas();
    }

    private void obtenerPersonas() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //  JsonArrayRequest porque el PHP devuelve un array directo
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                ApiConfig.EndPointGet,
                null,
                new com.android.volley.Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            listaPersonas.clear();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = response.getJSONObject(i);
                                
                                Personas persona = new Personas();
                                persona.setId(object.getInt("id"));
                                persona.setNombres(object.getString("nombres"));
                                persona.setApellidos(object.getString("apellidos"));
                                persona.setDireccion(object.getString("direccion"));
                                persona.setTelefono(object.getString("telefono"));
                                persona.setFoto(object.getString("foto"));

                                listaPersonas.add(persona);
                            }
                            
                            adapter.notifyDataSetChanged();
                            Log.d("VOLLEY", "Personas cargadas: " + listaPersonas.size());

                        } catch (JSONException ex) {
                            Log.e("VOLLEY", "Error al parsear JSON: " + ex.getMessage());
                            Toast.makeText(MainActivity.this, "Error en el formato de datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, volleyError -> {
                    String errorMsg = volleyError.getMessage();
                    if (errorMsg == null) errorMsg = "Error desconocido de red";
                    Log.e("VOLLEY", "Error de conexión: " + errorMsg);
                    Toast.makeText(MainActivity.this, "Error de conexión: " + errorMsg, Toast.LENGTH_LONG).show();
                }
        );

        requestQueue.add(jsonArrayRequest);
    }
}
