package uas.rifdah.implicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Browser;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btn_audio)
    Button btnAudio;
    @BindView(R.id.btn_notification)
    Button btnNotification;
    @BindView(R.id.btn_wifi)
    Button btnWifi;
    @BindView(R.id.btn_email)
    Button btnEmail;
    @BindView(R.id.btn_sms)
    Button btnSms;
    @BindView(R.id.btn_telepon)
    Button btnTelepon;
    @BindView(R.id.btn_camera)
    Button btnCamera;
    @BindView(R.id.btn_browser)
    Button btnBrowser;
    @BindView(R.id.btn_alarm)
    Button btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_audio, R.id.btn_notification, R.id.btn_wifi, R.id.btn_email, R.id.btn_sms, R.id.btn_telepon, R.id.btn_camera, R.id.btn_browser, R.id.btn_alarm})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_audio:

                Intent intent= new Intent(this, AudioManagerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_notification:

              intent= new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_wifi:
                intent= new Intent(this, WifiActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_email:
                intent= new Intent(this, EmailActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_sms:
                intent= new Intent(this, SmsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_telepon:
                intent= new Intent(this, TeleponActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_camera:
                intent= new Intent(this, CameraActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_browser:

                break;
            case R.id.btn_alarm:
                intent= new Intent(this, AlarmActivity.class);
                startActivity(intent);
                break;
        }
    }
}
