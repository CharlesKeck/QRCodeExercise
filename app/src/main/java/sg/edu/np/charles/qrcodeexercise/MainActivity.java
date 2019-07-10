package sg.edu.np.charles.qrcodeexercise;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeWriter;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btnGenerateQRCode;
    EditText editText;
    Button btnStartScanning;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        btnGenerateQRCode = (Button)findViewById(R.id.btnGenerateQRCode);
        editText = (EditText)findViewById(R.id.editText);
        btnStartScanning = (Button)findViewById(R.id.btnStartScanning);

        btnGenerateQRCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = QRCodeBmp(editText.getText().toString(), 300, 300);
                imageView.setImageBitmap(bitmap);
            }
        });

        btnStartScanning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SimpleScannerActivity.class);
                startActivity(intent);


            }
        });
    }

    Bitmap QRCodeBmp(String text, int width, int height){
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bmp;

        } catch (Exception e) {}
        return null;
    }



}
