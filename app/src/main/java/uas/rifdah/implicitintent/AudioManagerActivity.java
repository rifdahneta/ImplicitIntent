package uas.rifdah.implicitintent;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AudioManagerActivity extends AppCompatActivity {

    @BindView(R.id.btn_ring)
    Button btnRing;
    @BindView(R.id.btn_silent)
    Button btnSilent;
    @BindView(R.id.btn_vibrate)
    Button btnVibrate;

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_manager);
        ButterKnife.bind(this);

        audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
    }

    @OnClick({R.id.btn_ring, R.id.btn_silent, R.id.btn_vibrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_ring:

                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                Toast.makeText(this, "mode normal", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_silent:
                Toast.makeText(this, "mode silent", Toast.LENGTH_SHORT).show();
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);

                break;
            case R.id.btn_vibrate:
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                Toast.makeText(this, "Mode Getar", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
