package sg.edu.rp.c346.carapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;



public class CarDetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moves_details);
        Intent intent = getIntent();
        final CarDetails m = (CarDetails) intent.getSerializableExtra("cardetails") ;

       TextView tvName = (TextView) findViewById(R.id.tvName);
        TextView tvDesc = (TextView) findViewById(R.id.tvdesc);
        TextView tvprice = (TextView) findViewById(R.id.tvprice);
        TextView tvyear = (TextView) findViewById(R.id.tvyear);
        tvName.setText(m.getName());
        tvDesc.setText(m.getDescription());
        tvprice.setText(m.getCost_price().toString());
        tvyear.setText(m.getRelease_year());



    }



}