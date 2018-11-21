package fodiee.thenick.com.foodiee;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class LocationSuggestionsAdapter extends RecyclerView.Adapter<LocationSuggestionsAdapter.LocationSuggestionsViewHolder>{

    ArrayList<CityName> cityList;

   public class LocationSuggestionsViewHolder extends RecyclerView.ViewHolder{

       public TextView cityNametv;

       public LocationSuggestionsViewHolder(@NonNull View itemView) {
           super(itemView);
           cityNametv=itemView.findViewById(R.id.locationNameSuggestiontv);
       }
   }

   public LocationSuggestionsAdapter(ArrayList cityList)
   {
       this.cityList=cityList;
   }


    @NonNull
    @Override
    public LocationSuggestionsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_suggestion_list_item,viewGroup,false);

       view.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               SharedPreferences sharedPreferences=view.getContext().getSharedPreferences("AppPref", Context.MODE_PRIVATE);
               SharedPreferences.Editor editor=sharedPreferences.edit();
               editor.putFloat("Latitude",Float.valueOf(String.valueOf(cityList.get(i).getLatitude())));
               editor.putFloat("Longitude",Float.valueOf(String.valueOf(cityList.get(i).getLongitude())));
               editor.commit();
               Intent mainActivityIntent =new Intent(view.getContext(),MainActivity.class);
               view.getContext().startActivity(mainActivityIntent);
           }
       });
        return new LocationSuggestionsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationSuggestionsViewHolder locationSuggestionsViewHolder, int i) {
        CityName cityName= (CityName) cityList.get(i);
        locationSuggestionsViewHolder.cityNametv.setText(cityName.cityName);
    }


    @Override
    public int getItemCount() {
        return cityList.size();
    }
}
