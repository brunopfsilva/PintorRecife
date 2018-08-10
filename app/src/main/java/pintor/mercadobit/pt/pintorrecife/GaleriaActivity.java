package pintor.mercadobit.pt.pintorrecife;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class GaleriaActivity extends AppCompatActivity {


    ListView listView;
    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaeria);


        this.setTitle("Galeria");
        initViews();
        pb.setVisibility(View.INVISIBLE);

    }

    public void initViews(){


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    //    setSupportActionBar(toolbar);
        pb = (ProgressBar)findViewById(R.id.progress);
        pb.setVisibility(View.VISIBLE);
        listView = (ListView)findViewById(R.id.lstgaleria);

        ArrayList<Galeria> galerias = new ArrayList<Galeria>();

        for(int i = 0; i < 19; i++){

            Galeria galeria = new Galeria();
            galeria.setGaleria(String.valueOf(i));

            galerias.add(galeria);
        }

        listView.setAdapter(new ImageAdapter(this,galerias));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(this,MainActivity.class));
            finish();
            return true;
        }

        return true;

    }
}
