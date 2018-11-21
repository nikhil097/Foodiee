package fodiee.thenick.com.foodiee;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ManualLocationSelection extends AppCompatActivity {

    RequestQueue locationRequestQueue;
    EditText locationNameEt;
    RecyclerView locationSuggestionslv;
    ArrayList cityList;
    LocationSuggestionsAdapter locationSuggestionAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_location_selection);

        locationNameEt=findViewById(R.id.locationNameEt);
        locationSuggestionslv=findViewById(R.id.locationSuggestionslv);
        cityList=new ArrayList();
        locationSuggestionAdapter=new LocationSuggestionsAdapter(cityList);

        //  receiveLocationSuggestions();

        locationNameEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                receiveLocationSuggestions(charSequence.toString());

               // cityList.add(new CityName("hi"));


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        locationSuggestionslv.setLayoutManager(mLayoutManager);
        locationSuggestionslv.setItemAnimator(new DefaultItemAnimator());
        locationSuggestionslv.setAdapter(locationSuggestionAdapter);


    }





    public void receiveLocationSuggestions(String query)
    {
        if(locationRequestQueue==null)
        {
            locationRequestQueue= Volley.newRequestQueue(this);
        }

        JsonObjectRequest locationRequest = new JsonObjectRequest("https://developers.zomato.com/api/v2.1/locations?query="+query, null,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                Log.v("response",response.toString());

                try {

                    cityList.clear();
                    JSONObject cityListObject=new JSONObject(response.toString());
                    JSONArray citySuggestionsArray=cityListObject.getJSONArray("location_suggestions");
                    for(int i=0;i<citySuggestionsArray.length();i++)
                    {
                        JSONObject cityObject=citySuggestionsArray.getJSONObject(i);
                        cityList.add(new CityName(cityObject.getString("title"),cityObject.getDouble("latitude"),cityObject.getDouble("longitude")));
                        locationSuggestionslv.setAdapter(locationSuggestionAdapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        },null){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<String, String>();
           //     headers.put("query", "delhi");
                headers.put("user-key", "b3e77d88760548b1eda199195ebc3449");
                return headers;
            }
        };

        locationRequestQueue.add(locationRequest);






    }



}
