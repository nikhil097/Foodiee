package fodiee.thenick.com.foodiee;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LocationModeSelection extends AppCompatActivity {

    Button bTurnOnLocation;
    Button bSelectLocationManually;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_mode_selection);

        bTurnOnLocation = findViewById(R.id.bTurnOnLocation);
        bSelectLocationManually = findViewById(R.id.bSelectLocationManualy);

        bTurnOnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(myIntent);
            }
        });

        bSelectLocationManually.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent manualLocationSelect = new Intent(LocationModeSelection.this, ManualLocationSelection.class);
                startActivity(manualLocationSelect);
            }
        });






       // checkLocationStatus();

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkLocationStatus();
    }

    public void checkLocationStatus(){
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gps_enabled = false;
        boolean network_enabled = false;

        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch(Exception ex) {}

        try {
            network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch(Exception ex) {}

        if(!gps_enabled && !network_enabled) {
            // notify user
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setMessage("Location are disabled");
            dialog.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub
                    Intent myIntent = new Intent( Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(myIntent);
                    //get gps
                }
            });
            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    // TODO Auto-generated method stub

                }
            });
            dialog.show();
        }
        else{
            finish();
            Intent mainActivity=new Intent(LocationModeSelection.this,MainActivity.class);
            startActivity(mainActivity);


        }



    }
}
