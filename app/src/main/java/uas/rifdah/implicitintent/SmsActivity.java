package uas.rifdah.implicitintent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SmsActivity extends AppCompatActivity {

    @BindView(R.id.edt_nomor_tujuan)
    EditText edtNomorTujuan;
    @BindView(R.id.edt_body_sms)
    EditText edtBodySms;
    @BindView(R.id.btn_sms_langsung)
    Button btnSmsLangsung;
    @BindView(R.id.btn_sms_aplikasi)
    Button btnSmsAplikasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        ButterKnife.bind(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                Toast.makeText(this, "permission sudah diaktifkan", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 2);
            }
        }
    }

    @OnClick({R.id.edt_nomor_tujuan, R.id.btn_sms_langsung, R.id.btn_sms_aplikasi})
    public void onViewClicked(View view) {

        //todo ambil data dari input an user

        String noTelpon = edtNomorTujuan.getText().toString().trim();
        String bodySms = edtBodySms.getText().toString().trim();
        switch (view.getId()) {
            case R.id.edt_nomor_tujuan:
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

                //todo atur data kembali dari kontak

                startActivityForResult(intent,1 );
                break;
            case R.id.btn_sms_langsung:

                if (TextUtils.isEmpty(noTelpon) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(noTelpon, null, bodySms, null, null);
                    Toast.makeText(this, "berhasil kirim sms", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_sms_aplikasi:

                if (TextUtils.isEmpty(noTelpon) || TextUtils.isEmpty(bodySms)){
                    Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent2 = new Intent(Intent.ACTION_SENDTO);
                    intent2.setData(Uri.parse("smsto: " + Uri.encode(noTelpon)));
                    intent2.putExtra("sms_body" , bodySms);
                    startActivity(intent2);
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //todo ambil data dari kontak berdasarkan requestCode
        if(requestCode == 1){
            if (resultCode == RESULT_OK){
                Cursor cursor = null;
                Uri uri = data.getData();

                cursor = getContentResolver().query(uri, new String[] {ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
                        null,
                        null,
                        null);

                if (cursor != null && cursor.moveToNext()){
                    String nama = cursor.getString(1);
                    String noTelpon = cursor.getString(0);

                    edtNomorTujuan.setText(noTelpon);

                }
            }
        }

        if (requestCode == 2){
            if (resultCode == RESULT_OK){
                Toast.makeText(this, "permission sms diaktifkan", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED){
                Toast.makeText(this, "permission batal", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
