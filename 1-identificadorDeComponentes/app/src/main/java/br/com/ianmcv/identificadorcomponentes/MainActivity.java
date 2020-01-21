package br.com.ianmcv.identificadorcomponentes;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.StringDef;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import java.util.List;

import static android.os.BatteryManager.BATTERY_PROPERTY_CAPACITY;
import static android.os.BatteryManager.BATTERY_STATUS_CHARGING;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ActivityPrincipal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView_componentes_sistema);
        List<String> listaComponentesSistema = new ArrayList<String>();

        listaComponentesSistema.add(getResources().getString(R.string.MODELO)+": "+Build.MODEL);

        listaComponentesSistema.add(getResources().getString(R.string.ID)+": "+Build.ID);

        listaComponentesSistema.add(getResources().getString(R.string.CRIADOR)+": "+Build.MANUFACTURER);

        IntentFilter ifilterBateria = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilterBateria);
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        listaComponentesSistema.add(getResources().getString(R.string.BATERIA)+": "+level+"%");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listaComponentesSistema);
        listView.setAdapter(arrayAdapter);

        Log.v(TAG,"entrou na activity principal");


    }
}
