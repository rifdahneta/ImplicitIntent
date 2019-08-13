package uas.rifdah.implicitintent;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class TeleponActivity extends AppCompatActivity {

    @BindView(R.id.btn_panggil)
    Button btnPanggil;
    @BindView(R.id.btn_dial_phone)
    Button btnDialPhone;
    @BindView(R.id.edt_nomor_telepon)
    EditText edtNomorTelepon;
    @BindView(R.id.tv_nama_telpon)
    EditText tvNamaTelpon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telepon);
        ButterKnife.bind(this);

        //todo check permission


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                Toast.makeText(this, "permission sudah diaktifkan", Toast.LENGTH_SHORT).show();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 2);
            }
        }
    }


    @SuppressLint("MissingPermission")
    @OnClick({R.id.btn_panggil, R.id.btn_dial_phone, R.id.edt_nomor_telepon})
    public void onViewClicked(View view) {

        String nomorTelepno = edtNomorTelepon.getText().toString().trim();
        switch (view.getId()) {
            case R.id.btn_panggil:

                if (TextUtils.isEmpty(nomorTelepno)){
                    Toast.makeText(this, "Tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentCall = new Intent(Intent.ACTION_CALL);
                    intentCall.setData(Uri.parse("tel:" + nomorTelepno));
                    startActivity(intentCall);
                }
                break;
            case R.id.btn_dial_phone:
                if (TextUtils.isEmpty(nomorTelepno)){
                    Toast.makeText(this, "Tidak bleh kosong", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intentDial = new Intent(Intent.ACTION_DIAL);
                    intentDial.setData(Uri.parse("tel: " +nomorTelepno));
                    startActivity(intentDial);
                }
                break;
            case R.id.edt_nomor_telepon:
                //todo atur intent ke kontak

                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                intent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);

                //todo atur data kembali dari kontak

                startActivityForResult(intent,1 );
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

                    edtNomorTelepon.setText(noTelpon);
                    tvNamaTelpon.setText(nama);
                }
            }
        }
    }
}
