package uas.rifdah.implicitintent;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WifiActivity extends AppCompatActivity {

    @BindView(R.id.switch_wifi)
    Switch switchWifi;
    @BindView(R.id.txt_status)
    TextView txtStatus;

    WifiManager wifiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        ButterKnife.bind(this);


        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        assert wifiManager != null;
        if (wifiManager.isWifiEnabled()){
            switchWifi.setChecked(true);
            txtStatus.setText("Wifi sudah nyala");
        } else {
            switchWifi.setChecked(false);
            txtStatus.setText("Wifi belum nyala");
        }

        switchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    wifiManager.setWifiEnabled(true);
                    txtStatus.setText("Wifi dinyalakan");
                } else {
                    wifiManager.setWifiEnabled(false);
                    txtStatus.setText("wifi dimatikan");
                }
            }
        });
    }
}
