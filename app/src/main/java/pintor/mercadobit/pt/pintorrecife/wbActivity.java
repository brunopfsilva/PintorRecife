package pintor.mercadobit.pt.pintorrecife;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;

public class wbActivity extends AppCompatActivity {

    ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wb);


        initViews();
    }

    private void initViews() {

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //    setSupportActionBar(toolbar);
        pb = (ProgressBar)findViewById(R.id.progress);
        pb.setVisibility(View.INVISIBLE);


    }
}
