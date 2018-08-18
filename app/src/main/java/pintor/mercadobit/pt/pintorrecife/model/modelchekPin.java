package pintor.mercadobit.pt.pintorrecife.model;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import pintor.mercadobit.pt.pintorrecife.MainActivity;
import pintor.mercadobit.pt.pintorrecife.tasks.Common;

import static android.content.Context.MODE_PRIVATE;

public class modelchekPin {

    @SuppressLint("StaticFieldLeak")
    private class get_pin_from_server extends AsyncTask<String, Void, Void> {

        ProgressDialog dialog;
        SharedPreferences.Editor preferencesput;

        private MainActivity mainActivity;


        public get_pin_from_server(String pin){

            dialog = new ProgressDialog(mainActivity);
            //construtor para pode chamar o progress dialog
            this.mainActivity = mainActivity;
        }

        @Override
        protected Void doInBackground(String... pin) {


            Ion.with(mainActivity)
                    .load(Common.GET_PIN)
                    .setBodyParameter("pin", String.valueOf(pin))
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {


                            try{


                                if (result.get("retorno").getAsString().equals("YES")){

                                    preferencesput = mainActivity.getSharedPreferences("USER_INFORMATION",MODE_PRIVATE).edit();

                                }

                                }catch (Exception ex){

                            }

                        }
                    });


            return null;


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           // runOnUiThread(new Runnable() {
           //     @Override
         //       public void run() {
                    dialog.setMessage("So um momento, por favor aguarde.");
                    dialog.show();
        //        }
         //   });
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

         //   runOnUiThread(new Runnable() {
         //       @Override
        //        public void run() {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                //    Toast.makeText(revisaodeCompraActivity.this, "Carrinho enviado com sucesso", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getBaseContext(),InicioActivity.class));
                   // finish();

         //       }
       //     });


        }
    }


}
