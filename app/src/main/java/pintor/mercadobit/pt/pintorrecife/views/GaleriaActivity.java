package pintor.mercadobit.pt.pintorrecife.views;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import pintor.mercadobit.pt.pintorrecife.MainActivity;
import pintor.mercadobit.pt.pintorrecife.R;
import pintor.mercadobit.pt.pintorrecife.model.Galeria;
import pintor.mercadobit.pt.pintorrecife.model.ImageAdapter;
import pintor.mercadobit.pt.pintorrecife.presenter.PresenterGaleria;

public class GaleriaActivity extends AppCompatActivity {


    @BindView(R.id.lstgaleria) ListView listView;
    @BindView(R.id.progress) ProgressBar pb;
    PresenterGaleria presenterGaleria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galaeria);
        ButterKnife.bind(this);

        //chamada do presenter
        presenterGaleria = new PresenterGaleria(this);

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
        pb.setVisibility(View.VISIBLE);

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
