package uas.rifdah.implicitintent;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationActivity extends AppCompatActivity {

    @BindView(R.id.btn_notifikasi)
    Button btnNotifikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_notifikasi)
    public void onViewClicked() {

        //todo atur notification manager

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        String chanelId = "1";
        String channelName = "notif";

        //todo cek versi android

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel mChannel = new NotificationChannel(chanelId, channelName, NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(mChannel);
        }

        //todo atur tampilan notifikasi

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, chanelId)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentTitle("Notifikasi")
                .setContentText("Hello !")
                .setAutoCancel(true);

        //todo atur lama getar dan suara notifikasi
        long [] getar = {500};
        Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //todo event ketika notification di klik
        Intent resultIntent = new Intent(this, NotificationActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this , 0,
                                        resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //todo eksekusi pending intent

        builder.setContentIntent(pendingIntent);
        builder.setSound(sound);
        builder.setVibrate(getar);

        notificationManager.notify(1, builder.build());
    }
}
