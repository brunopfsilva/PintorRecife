package pintor.mercadobit.pt.pintorrecife;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class wbActivity extends AppCompatActivity {

    ProgressBar pb;
    WebView wbchat;

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
        wbchat = (WebView)findViewById(R.id.wbchat);

        WebSettings webSettings = wbchat.getSettings();


        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(true);
        //wbchat.setWebChromeClient(new WebChromeClient());
        CookieManager.getInstance().setAcceptCookie(true);

        wbchat.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                getWindow().setTitle("Chat"); //Set Activity tile to page title.
            }
        });

        wbchat.setWebViewClient(new WebViewClient(){

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error){
                handler.proceed();

            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url){
                //view.loadUrl("http://maps.google.com/maps?q="+longi+","+latti+"&t=h&z=15");
                //view.loadUrl("https://m.facebook.com/messages/thread/100011583002689/?ref=bookmarks");
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon){
                pb.setVisibility(View.VISIBLE);
            }
            @Override
            public void onPageFinished(WebView view, String url){
                pb.setVisibility(View.INVISIBLE);
            }


        });


            String newUA= "Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.4) Gecko/20100101 Firefox/4.0";
            wbchat.getSettings().setUserAgentString(newUA); //chama o agente desktop

            wbchat.loadUrl("https://m.facebook.com/messages/thread/100011583002689/?ref=bookmarks");
        //  wbchat.loadUrl("https://www.messenger.com/t/pedrinho.sdc.12");


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        pb.setVisibility(View.VISIBLE);
        startActivity(new Intent(this,MainActivity.class));
        wbchat.destroy();
        finish();

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
