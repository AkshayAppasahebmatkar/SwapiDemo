package viral.com.swapidemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Context context;
    public static int characterPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this.getApplicationContext();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_item, R.id.txtName, getResources().getStringArray(R.array.nameList));
        list = (ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                characterPosition=position;
                if (checkInternetConenction()) {
                    switch (position) {
                        case 0:
                            try {
                                String response = null;
                                response = (String) new URLUtilAsync(MainActivity.this, "https://swapi.co/api/people/1/").get();
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
                        case 1:
                            try {
                                String response = null;
                                response = (String) new URLUtilAsync(MainActivity.this, "https://swapi.co/api/planets/3/").get();
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
                        case 2:
                            try {
                                String response = null;
                                response = (String) new URLUtilAsync(MainActivity.this, "https://swapi.co/api/starships/9/").get();
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
                        default:
                            try {
                                String response = null;
                                response = (String) new URLUtilAsync(MainActivity.this, "https://swapi.co/api/people/1/").get();
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
        });

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
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
            // Setting Dialog Title
            alertDialog.setTitle("Internet Connection Error.");
            // Setting Dialog Message
            alertDialog.setMessage("Please Check your Internet Connection....");
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {
                    // Write your code here to invoke YES event
                    //finish();
                    //System.exit(0);
                }
            });
            // Showing Alert Message
            alertDialog.show();
           // Toast.makeText(this, " Please Check your Internet Connection.", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

}
