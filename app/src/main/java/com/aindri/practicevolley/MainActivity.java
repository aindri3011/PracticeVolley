package com.aindri.practicevolley;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler1;
    RecyclerAdapter recyclerAdapter;
    ArrayList<UserModel> arrayList = new ArrayList<>();

    RequestQueue requestQueue;
    JsonArrayRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler1 = findViewById(R.id.recycler1);
        recycler1.setHasFixedSize(true);
        recycler1.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJson();

//           jsonArrayrequest();

    }

    private void parseJson() {
        String strUrl = "Enter your url";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, strUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject dat = jsonArray.getJSONObject(i);
                                int id = dat.getInt("id");
                                String email = dat.getString("email");
                                String first_name = dat.getString("first_name");
                                String last_name = dat.getString("last_name");
                                String avatar = dat.getString("avatar");

                                arrayList.add(new UserModel(id, email, first_name, last_name, avatar));
                            }
                            recyclerAdapter = new RecyclerAdapter(MainActivity.this, arrayList);
                            recycler1.setAdapter(recyclerAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        requestQueue.add(request);
    }


}




















//    private void jsonArrayrequest(){
//request=new JsonArrayRequest(strUrl, new Response.Listener<JSONArray>() {
//    @Override
//    public void onResponse(JSONArray response) {
//        JSONObject jsonObject = null;
//        for (int i = 0; i < response.length(); i++) {
//            try {
//                jsonObject = response.getJSONObject(i);
//            UserModel userModel=new UserModel();
//            userModel.setId(jsonObject.getInt("id"));
//            userModel.setEmail(jsonObject.getString("email"));
//            userModel.setFirst_name(jsonObject.getString("first_name"));
//            userModel.setLast_name(jsonObject.getString("last_name"));
//            userModel.setAvatar(jsonObject.getString("avatar"));
//            arrayList.add(userModel);
//
//
//
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//        setuprecyclerview(arrayList);
//    }
//    }, new Response.ErrorListener() {
//    @Override
//    public void onErrorResponse(VolleyError error) {
//
//    }
//
//    });
//
//requestQueue= Volley.newRequestQueue(MainActivity.this);
//requestQueue.add(request);
//    }
//    private void setuprecyclerview(ArrayList<UserModel>arrayList){
//        linearLayoutManager = new LinearLayoutManager(MainActivity.this,RecyclerView.VERTICAL, false);
//        recyclerAdapter= new RecyclerAdapter(arrayList, MainActivity.this);
//        recycler1.setLayoutManager(linearLayoutManager);
//        recycler1.setAdapter(recyclerAdapter);
//
//    }





