package gusmf.app_offline;

import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;


public class MainActivity extends Activity {


    cambio_con c_c;
    conexionCambio_Receiver cc_r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //////////////USO
        //1-CREAR NUEVO OBJETO DE LA INTERFACE cambio_con
        // Su objetivo es comunicar a la activity el cambio de conectividad
        //2-REGISTRAR INTENT RECEIVER EN EL ONRESUME()
        // Escuchará solo los cambios de conectividad
        //3-CUANDO HAYA UN CAMBIO DE CONECTIVIDAD SE EJECUTAN LAS ACCIONES PREPARADAS EN LOS MÉTODOS
        // conectividad_si // conectividad_no
        //usa los permisos en el manifest:
        //<uses-permission android:name="android.permission.INTERNET" />
        //<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        //<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        //4-EN EL ONPAUSE(), ELIMINAR REGISTRO AL BROADCAST


        c_c = new cambio_con() {
            @Override
            public void conectividad_si() {
                ////////////HAY CONEXION A INTERNET
                ///////REALIZAR ACCIONES
                Toast.makeText(MainActivity.this, "INTERNET ON", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void conectividad_no() {
                //////////NO HAY CONEXION A INTERNET
                //////REALIZAR ACCIONES
                Toast.makeText(MainActivity.this, "INTERNET OFF", Toast.LENGTH_SHORT).show();
            }
        };



    }

    @Override
    public void onPause(){
        super.onPause();
        if (cc_r != null)
            unregisterReceiver(cc_r);
    }

    @Override
    public void onResume(){
        super.onResume();
        cc_r = new conexionCambio_Receiver(c_c);
        /////registrarnos al broadcast
        registerReceiver(
                cc_r,
                new IntentFilter(
                        ConnectivityManager.CONNECTIVITY_ACTION));
    }



}
