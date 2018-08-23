package pintor.mercadobit.pt.pintorrecife.presenter;

import pintor.mercadobit.pt.pintorrecife.MainActivity;
import pintor.mercadobit.pt.pintorrecife.model.modelDownloadData;
import pintor.mercadobit.pt.pintorrecife.model.modelchekPin;


public class PresenterMain {

    private MainActivity mainActivity;

    public PresenterMain(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public String sendpin(String pin){


        //api pegar do servidor
        new modelchekPin.get_pin_from_server(mainActivity).execute(pin); //não esquecer de por o .execute(); para chamar o async

        return pin;
    }

    public void getDataCliente(){



        //api pegar do servidor
        new modelDownloadData.get_data_from_server(mainActivity).execute(); //não esquecer de por o .execute(); para chamar o async

    }


    public void resultpin(){

        mainActivity.pinMsg();


    }


    public void resultdata() {

        mainActivity.actionDate();

    }
}
