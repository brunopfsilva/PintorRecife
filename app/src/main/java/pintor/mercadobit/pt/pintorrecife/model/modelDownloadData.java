package pintor.mercadobit.pt.pintorrecife.model;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import java.util.Arrays;

import pintor.mercadobit.pt.pintorrecife.MainActivity;
import pintor.mercadobit.pt.pintorrecife.presenter.PresenterMain;
import pintor.mercadobit.pt.pintorrecife.tasks.Common;

import static android.content.Context.MODE_PRIVATE;

public class modelDownloadData {



    @SuppressLint("StaticFieldLeak")
    public static class get_data_from_server extends AsyncTask<Void, Void, Void> {

        // ProgressDialog dialog;
        SharedPreferences.Editor preferencesput;

        MainActivity mainActivity;

        PresenterMain presenterMain;

        SharedPreferences preferencesget;


        public get_data_from_server(MainActivity mainActivity){

            this.mainActivity = mainActivity;
            presenterMain = new PresenterMain(mainActivity);
            preferencesget = mainActivity.getSharedPreferences("USER_INFORMATION",MODE_PRIVATE);

        }

        @Override
        protected Void doInBackground(final Void... voids) {

            Ion.getDefault(mainActivity).getConscryptMiddleware().enable(false); //remove erro IonConscrypt: Conscrypt initialization failed.

            preferencesput = mainActivity.getSharedPreferences("USER_INFORMATION",MODE_PRIVATE).edit();

            Ion.with(mainActivity)
                    .load(Common.GETDADOS)
                    //     .addHeader("Content-Type", "text/html")
                    .setBodyParameter("email",preferencesget.getString("email","email"))
                    .asJsonArray()
                    .setCallback(new FutureCallback<JsonArray>() {
                        @Override
                        public void onCompleted(Exception e, JsonArray result) {

                            try{


                                for (int i = 0; i < result.size(); i++) {

                                    JsonObject obj = result.get(i).getAsJsonObject();

                                //    if(Arrays.toString(pin).trim().equals(obj.get("Pin").getAsString())){
                                        preferencesput.putString("email_cliente",obj.get("email_cliente").getAsString());
                                        preferencesput.putString("nome_cliente",obj.get("nome_cliente").getAsString());
                                        preferencesput.putString("telefone",obj.get("telefone").getAsString());
                                        preferencesput.putString("link_psrtilha",obj.get("link_psrtilha").getAsString());
                                        preferencesput.putString("link_social",obj.get("link_social").getAsString());
                                        preferencesput.putString("link_chat",obj.get("link_chat").getAsString());
                                        preferencesput.putString("photo_link",obj.get("photo_link").getAsString());
                                        preferencesput.putString("texto_do_app",obj.get("texto_do_app").getAsString());
                                        preferencesput.putString("end_cliente",obj.get("end_cliente").getAsString());
                                        preferencesput.putString("pin_do_Cliente",obj.get("pin_do_Cliente").getAsString());
                                        preferencesput.commit();
                                        preferencesput.apply();
                                        presenterMain.resultdata();

                                   // }


                                }


                            }catch (Exception ex){
                                Toast.makeText(mainActivity, ""+ex, Toast.LENGTH_SHORT).show();
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

}
