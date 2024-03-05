package com.example.lab11;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
public class MainActivity extends AppCompatActivity {
    private TextView resultTextView;
    private RequestQueue requestQueue;
    private String apiUrl = "https://lalit-site1.sites.tjhsst.edu";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTextView = findViewById(R.id.my_response_textview);
        requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET, apiUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        processJsonArray(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }

    private void processJsonArray(JSONArray jsonArray) {
        try {
            if (jsonArray.length() > 0) {
                resultTextView.setText("ID: " + jsonArray.getJSONObject(0).getString("id"));
            } else {
                resultTextView.setText("No data available");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
