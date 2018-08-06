package gusmf.app_offline;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Gustavo on 14/03/2015.
 */
public class conexionCambio_Receiver extends BroadcastReceiver {

    cambio_con cambioCon;



    public conexionCambio_Receiver(cambio_con cambioCon){
        this.cambioCon = cambioCon;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        comprobar (context);
    }


    public static boolean hay_conexion(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public void comprobar (Context context){
        if (hay_conexion(context)){
            cambioCon.conectividad_si();
        }else{
            cambioCon.conectividad_no();
        }
    }
}
