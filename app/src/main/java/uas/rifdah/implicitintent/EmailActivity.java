package uas.rifdah.implicitintent;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailActivity extends AppCompatActivity {

    @BindView(R.id.edt_email_tujuan)
    EditText edtEmailTujuan;
    @BindView(R.id.edt_subjek)
    EditText edtSubjek;
    @BindView(R.id.edt_body_email)
    EditText edtBodyEmail;
    @BindView(R.id.btn_kirim)
    Button btnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_kirim)
    public void onViewClicked() {
        String email = edtEmailTujuan.getText().toString().trim();
        String subjek = edtSubjek.getText().toString().trim();
        String bodyEmail = edtBodyEmail.getText().toString().trim();

        // todo validasi

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(subjek) || TextUtils.isEmpty(bodyEmail)){
            Toast.makeText(this, "tidak boleh kosong", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            intent.putExtra(Intent.EXTRA_SUBJECT,subjek);
            intent.putExtra(Intent.EXTRA_TEXT, bodyEmail);

            intent.setType("message/rfc822");
            startActivity(Intent.createChooser(intent, "Silahkan pilih aplikasi email"));
        }
    }
}
