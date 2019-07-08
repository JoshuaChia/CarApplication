package sg.edu.rp.c346.carapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class CarMain extends AppCompatActivity {

    private ListView lvCar;
    ArrayList<Car> alCar = new ArrayList<Car>();
    ArrayAdapter<Car> aaCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_main);

        lvCar = (ListView) findViewById(R.id.lvCar);
        aaCar = new ArrayAdapter<Car>(this, android.R.layout.simple_list_item_1, alCar);
        lvCar.setAdapter(aaCar);

        //TODO: Task 1: Consume getCategories.php using AsyncHttpClient
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://10.0.2.2/CarApplication/getCar.php", new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // called when response HTTP status is "200 OK"
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject cars = (JSONObject)response.get(i);
                        Car c =  new Car(cars.getInt("idcarname"), cars.getString("name"));
                        alCar.add(c);
                    }
                    aaCar.notifyDataSetChanged();

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }


        });

        lvCar.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Car c = alCar.get(i);  // Get the selected Category
                Intent intent = new Intent(getApplicationContext(),CarActivity.class);
                intent.putExtra("car", c);
                startActivity(intent);
            }
        });
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here.
//
//        int id = item.getItemId();
//
//        if (id == R.id.SummarySelection) {
//            Intent i = new Intent(getBaseContext(),SummaryMain.class);
//            startActivity(i);
//        }else if (id == R.id.searchSelection){
//            Intent i = new Intent(getBaseContext(),SearchActivity.class);
//            startActivity(i);
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
