package pintor.mercadobit.pt.pintorrecife;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.carbs.android.library.MDDialog;
import pintor.mercadobit.pt.pintorrecife.presenter.PresenterMain;
import pintor.mercadobit.pt.pintorrecife.views.GaleriaActivity;
import pintor.mercadobit.pt.pintorrecife.views.wbActivity;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @BindView(R.id.progress) ProgressBar pb;
    PresenterMain presenterMain;
    View layout;
    LayoutInflater inflater;
    String pin;
    SharedPreferences.Editor preferencesput;
    SharedPreferences preferencesget;
    TextView telefone,txt_nome_app,txtemail;




    /** @BindView(R.id.inputPin) **/
    EditText inputPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(MainActivity.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initViews();
        //instancia de presente main
        presenterMain = new PresenterMain(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callme(view);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        txtemail = (TextView) header.findViewById(R.id.txtemail);
        txt_nome_app = (TextView)header.findViewById(R.id.txt_nome_app);
        if(!preferencesget.getString("email_cliente","").equals("null")){
            txtemail.setText("Insira seu id");
        }
        txtemail.setText(preferencesget.getString("email",""));
        txt_nome_app.setText(preferencesget.getString("nome_app",""));



        pb.setVisibility(View.INVISIBLE);

    }

    public void initViews(){


        pb.setVisibility(View.VISIBLE);
        preferencesput = getSharedPreferences("USER_INFORMATION",MODE_PRIVATE).edit();
        preferencesget = getSharedPreferences("USER_INFORMATION",MODE_PRIVATE);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            finish();
            return true;
        }else if (id == R.id.action_pin) {

            getPin();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getViewContent(View root,int id){
        EditText field = (EditText)root.findViewById(id);
        return field.getText().toString();
    }

    public void getPin(){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {


                new MDDialog.Builder(MainActivity.this)
                        .setTitle("Validar PIN")
                        .setContentView(R.layout.layout_pin )
                        .setNegativeButton(" - ", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {


                                pb.setVisibility(View.VISIBLE);
                                startActivity(new Intent(getBaseContext(),MainActivity.class));
                                finish();



                            }
                        })
                        .setPositiveButton("Enviar", new View.OnClickListener() {



                            @Override
                            public void onClick(View v) {
                                final View root = v.getRootView(); //pega o root do layout
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {


                                        pb.setVisibility(View.VISIBLE);

                                        pin = getViewContent(root,R.id.inputPin);

                                        preferencesput.putString("pin",pin);
                                        preferencesput.commit();
                                        preferencesput.apply();

                                        presenterMain.sendpin(pin);

                                    }
                                });


                               // finish();
                            }

                        })

                        .create()
                        .show();

            }
        });



    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            pb.setVisibility(View.VISIBLE);
            startActivity(new Intent(this,MainActivity.class));
            finish();
        } else if (id == R.id.nav_gallery) {
            pb.setVisibility(View.VISIBLE);
            startActivity(new Intent(this,GaleriaActivity.class));
            finish();
        } else if (id == R.id.nav_slideshow) {


            showcontacts();


        } else if (id == R.id.nav_share) {

            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "https://www.facebook.com/pedrotoic.sdc.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);

        } else if (id == R.id.nav_chat) {

            pb.setVisibility(View.VISIBLE);
            startActivity(new Intent(this,wbActivity.class));
            finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showcontacts() {

        new MDDialog.Builder(MainActivity.this)
                .setTitle("Meios de contacto")
                .setContentView(R.layout.activity_contacto )
                .setNegativeButton(" - ", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                        layout = inflater.inflate(R.layout.activity_contacto, null);
                        telefone = (TextView)layout.findViewById(R.id.id_telefone);
                        telefone.setText(preferencesget.getString("telefone",""));
                        pb.setVisibility(View.VISIBLE);
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                        finish();
                    }
                })
                .setPositiveButton("ok", new View.OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        pb.setVisibility(View.VISIBLE);
                        startActivity(new Intent(getBaseContext(),MainActivity.class));
                        finish();
                    }

                })

                .create()
                .show();


    }

    public void callme(View view) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_layout, null);

        String numero = "5581988735652";

        Uri uri = Uri.parse("tel:" +numero);
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1); //solicita a permissao novamente caso ela não tenha sido conxedida
            return;
        }
        startActivity(intent);

    }

    public void pinMsg() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Pin Valido", Toast.LENGTH_SHORT).show();

                presenterMain.getDataCliente();
            }
        });

    }

    public void actionDate() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getBaseContext(), "Dados Carregados", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getBaseContext(),MainActivity.class));
            }
        });

    }
}
