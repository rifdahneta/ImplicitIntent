package uas.rifdah.implicitintent;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CameraActivity extends AppCompatActivity {

    @BindView(R.id.img_result)
    ImageView imgResult;
    @BindView(R.id.btn_galeri)
    Button btnGaleri;
    @BindView(R.id.btn_kamera)
    Button btnKamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ButterKnife.bind(this);


        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)){
                Toast.makeText(this, "permission sudah diaktifkan", Toast.LENGTH_SHORT).show();
            }else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
            }
        }
    }

    @OnClick({R.id.btn_galeri, R.id.btn_kamera})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_galeri:

                Intent intentGalery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intentGalery, 2);

                break;
            case R.id.btn_kamera:
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intentCamera, 3);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2){
            if(resultCode == RESULT_OK){

                Uri uriImage = data.getData();
                imgResult.setImageURI(uriImage);
            } else if (resultCode == RESULT_CANCELED)  {
                Toast.makeText(this, "gagal ambil gambar", Toast.LENGTH_SHORT).show();
            }
        }
        if (requestCode == 3){
            if (resultCode == RESULT_OK){

                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                imgResult.setImageBitmap(bitmap);

            }
            else if(resultCode == RESULT_CANCELED){
                Toast.makeText(this, "gagal ambil gambar", Toast.LENGTH_SHORT).show();
            }
        }

    }
}