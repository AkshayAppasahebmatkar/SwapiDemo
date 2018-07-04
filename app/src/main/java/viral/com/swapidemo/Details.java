package viral.com.swapidemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by viral
 * on 4/7/18.
 */
public class Details extends AppCompatActivity {
    TextView name, height, mass, date;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_view);
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


}