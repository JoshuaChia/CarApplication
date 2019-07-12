package sg.edu.rp.c346.carapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CarActivity extends AppCompatActivity {
    ListView lv;
    TextView tvCar;

    ArrayAdapter <CarDetails>aa;
    ArrayList<CarDetails> alcar = new ArrayList<CarDetails>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        Intent i = getIntent();
        Car g = (Car) i.getSerializableExtra("car");

        tvCar = findViewById(R.id.tvCar);
        lv = findViewById(R.id.lvCar);

        tvCar.setText(g.getName());
        aa = new ArrayAdapter<CarDetails>(this, android.R.layout.simple_list_item_1, alcar);
        lv.setAdapter(aa);

        RequestParams params = new RequestParams();
        params.add("id", String.valueOf(g.getId()));
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("http://10.0.2.2/CarApplication/getcardetailsbyID.php",params, new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject car = (JSONObject)response.get(i);
                        CarDetails ab =  new CarDetails(car.getInt("idcardetails"),car.getString("name"),car.getString("release_year"),car.getString("cost_price"),car.getString("description"),car.getInt("idcarname"));
                        alcar.add(ab);

                    }
                    aa.notifyDataSetChanged();

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }


        });

        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getBaseContext() ,CarDetailActivity.class);
                CarDetails choosenmove = alcar.get(position);
                i.putExtra("cardetails",choosenmove);
                startActivity(i);
            }
        });



    }
}