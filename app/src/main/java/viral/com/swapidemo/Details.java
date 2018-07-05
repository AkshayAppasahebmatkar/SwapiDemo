package viral.com.swapidemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by viral
 * on 4/7/18.
 */
public class Details extends AppCompatActivity {
    TextView name, height, mass, date;
    Context context;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
        context = getApplicationContext();
        Bundle bundle = getIntent().getExtras();
        String data = bundle.getString("data");

        name = (TextView) findViewById(R.id.name);
        height = (TextView) findViewById(R.id.height);
        mass = (TextView) findViewById(R.id.mass);
        date = (TextView) findViewById(R.id.date);

        try {
            JSONObject jsonObject = new JSONObject(data);
            if(jsonObject.has("name"))
            name.setText(jsonObject.getString("name"));
            if(jsonObject.has("height"))
                height.setText(jsonObject.getString("height"));
            if(jsonObject.has("mass"))
                mass.setText(jsonObject.getString("mass"));
            if(jsonObject.has("created"))
                date.setText(jsonObject.getString("created"));
        } catch (JSONException e) {

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will

        switch (item.getItemId()) {

            case R.id.itable:
                refreshScreen();
                break;

        }
        return (super.onOptionsItemSelected(item));
    }

    private void refreshScreen() {
        Intent intent;
        if (checkInternetConenction()) {
            switch (MainActivity.characterPosition) {
                case 0:
                    try {
                        String response = null;
                        response = (String) new URLUtilAsync(Details.this, "https://swapi.co/api/people/1/").get();
                        if (response != null) {
                            finish();
                            intent = new Intent(context, Details.class);
                            intent.putExtra("data", response);
                            startActivity(intent);
                        } else {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
                case 1:
                    try {
                        String response = null;
                        response = (String) new URLUtilAsync(Details.this, "https://swapi.co/api/planets/3/").get();
                        if (response != null) {
                            finish();
                            intent = new Intent(context, Details.class);
                            intent.putExtra("data", response);
                            startActivity(intent);
                        } else {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    break;
                case 2:
                    try {
                        String response = null;
                        response = (String) new URLUtilAsync(Details.this, "https://swapi.co/api/starships/9/").get();
                        if (response != null) {
                            finish();
                            intent = new Intent(context, Details.class);
                            intent.putExtra("data", response);
                            startActivity(intent);
                        } else {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }

                    break;
                default:
                    try {
                        String response = null;
                        response = (String) new URLUtilAsync(Details.this, "https://swapi.co/api/people/1/").get();
                        if (response != null) {
                            intent = new Intent(context, Details.class);
                            intent.putExtra("data", response);
                            startActivity(intent);
                        } else {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    public boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);
        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(Details.this);
            alertDialog.setTitle("Internet Connection Error.");
            alertDialog.setMessage("Please Check your Internet Connection....");
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {

                }
            });
            // Showing Alert Message
            alertDialog.show();
            return false;
        }
        return false;
    }
}