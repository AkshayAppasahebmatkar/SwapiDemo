package viral.com.swapidemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    ListView list;
    Context context;

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
        });

    }


}
