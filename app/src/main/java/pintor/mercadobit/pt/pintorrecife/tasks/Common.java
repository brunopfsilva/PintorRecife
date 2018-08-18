package pintor.mercadobit.pt.pintorrecife.tasks;


/**
 * Created by Pedro on 16/05/2017.
 */

public class Common {

    public static final String GETDADOS = "http://myhostapps-tk.umbler.net/api/pedro/v1/getdados.php";
    public static final String GET_PIN = "http://myhostapps-tk.umbler.net/api/pedro/v1/getdados.php";

    //token
    public static final String token = "domtakeway";


    public static final String token_firebase = "http://myhostapps.tk/api/cliente/update_token.php";


    public static boolean isNullOrEmptyString(String content) {
        return (!(content != null && !content.trim().isEmpty()));
    }


}
