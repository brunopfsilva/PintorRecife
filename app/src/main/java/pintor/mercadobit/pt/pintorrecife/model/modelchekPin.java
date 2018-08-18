package pintor.mercadobit.pt.pintorrecife.model;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import pintor.mercadobit.pt.pintorrecife.MainActivity;
import pintor.mercadobit.pt.pintorrecife.presenter.PresenterMain;
import pintor.mercadobit.pt.pintorrecife.tasks.Common;

import static android.content.Context.MODE_PRIVATE;

public class modelchekPin {

    //@SuppressLint("StaticFieldLeak")
    public static class get_pin_from_server extends AsyncTask<String, Void, Void> {

       // ProgressDialog dialog;
        SharedPreferences.Editor preferencesput;

        private MainActivity mainActivity;

        PresenterMain presenterMain;

        public get_pin_from_server(MainActivity mainActivity){

           // dialog = new ProgressDialog(mainActivity);
            //construtor para pode chamar o progress dialog
            this.mainActivity = mainActivity;
            presenterMain = new PresenterMain(mainActivity);
            callserver();

        }

        @Override
        protected Void doInBackground(String... pin) {

            Ion.getDefault(mainActivity).getConscryptMiddleware().enable(false); //remove erro IonConscrypt: Conscrypt initialization failed.



            Ion.with(mainActivity)
                    .load(Common.GET_PIN)
                    .addHeader("Content-Type", "application/json")
                    .setBodyParameter("pin", String.valueOf(pin))
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {


                            try{


                                if (result.get("retorno").getAsString().equals("YES")){

                                    preferencesput = mainActivity.getSharedPreferences("USER_INFORMATION",MODE_PRIVATE).edit();
                                    preferencesput.putString("email",result.get("email").getAsString());
                                    preferencesput.apply();
                                    presenterMain.resultpin();

                                }else if (result.get("retorno").getAsString().equals("ERROR")){

                                    Toast.makeText(mainActivity, " Error ", Toast.LENGTH_SHORT).show();
                                }

                                }catch (Exception ex){

                                Toast.makeText(mainActivity, " Error ", Toast.LENGTH_SHORT).show();
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
               //     dialog.setMessage("So um momento, por favor aguarde.");
              //      dialog.show();
        //        }
         //   });
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

         //   runOnUiThread(new Runnable() {
         //       @Override
        //        public void run() {
                 //   if (dialog.isShowing()) {
                //        dialog.dismiss();
               //     }
                //    Toast.makeText(revisaodeCompraActivity.this, "Carrinho enviado com sucesso", Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(getBaseContext(),InicioActivity.class));
                   // finish();

         //       }
       //     });


        }
    }

    public static void callserver(){

    }

}
