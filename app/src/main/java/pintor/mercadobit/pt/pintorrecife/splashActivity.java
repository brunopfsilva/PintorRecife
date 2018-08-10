package pintor.mercadobit.pt.pintorrecife;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class splashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        Handler handle = new Handler(); //hadler trabalha com thread no android
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarInicio();
            }
        },4000);


    }



    public void mostrarInicio(){

        ProgressBar pb = (ProgressBar)findViewById(R.id.progress);
        pb.setVisibility(View.VISIBLE);
        startActivity(new Intent(this,MainActivity.class));
        finish();

    }
}
