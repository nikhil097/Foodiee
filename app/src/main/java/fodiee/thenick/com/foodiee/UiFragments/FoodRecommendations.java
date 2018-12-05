package fodiee.thenick.com.foodiee.UiFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

import fodiee.thenick.com.foodiee.R;


public class FoodRecommendations extends Fragment {


    RequestQueue restaurantRecommendationsQueue;

    public FoodRecommendations() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        return inflater.inflate(R.layout.fragment_food_recommendations, container, false);
    }


    public void receiveRestaurantRecommendations(){

        if(restaurantRecommendationsQueue==null)
        {
            restaurantRecommendationsQueue= Volley.newRequestQueue(getActivity());
        }

        JsonObjectRequest restaurantRecommendationObject = new JsonObjectRequest("https://developers.zomato.com/api/v2.1/search?q=shree&lat=28.717010&lon=77.102360", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    response.getString("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, null);


        restaurantRecommendationsQueue.add(restaurantRecommendationObject);
    }

}
