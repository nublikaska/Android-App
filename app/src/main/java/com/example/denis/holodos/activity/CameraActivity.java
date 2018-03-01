package com.example.denis.holodos.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.example.denis.holodos.ParseTask.ParseTaskReceipt;
import com.example.denis.holodos.R;
import com.example.denis.holodos.modules.receipts.Receipt;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Denis on 02.01.2018.
 */

public class CameraActivity extends finishedAsync {

    SurfaceView cameraPreview;
    TextView txtResult;
    BarcodeDetector barcodeDetector;
    CameraSource cameraSource;
    final int RequestCameraPermissionID = 1001;
    Camera camera;

    private boolean isDetected = false;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        cameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        cameraPreview = (SurfaceView) findViewById(R.id.cameraPreview);
        txtResult = (TextView) findViewById(R.id.txtResult);

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        cameraSource = new CameraSource
                .Builder(this, barcodeDetector)
                .setRequestedPreviewSize(640, 480)
                .setAutoFocusEnabled(true)
                .build();
        //Add Event
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    //Request permission
                    ActivityCompat.requestPermissions(CameraActivity.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }
                try {
                    cameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                cameraSource.stop();

            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
                    txtResult.post(new Runnable() {
                        @Override
                        public void run() {
                            //Create vibrate
//                            Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//                            vibrator.vibrate(1000);
                            txtResult.setText(qrcodes.valueAt(0).displayValue);

                            Intent answerIntent = new Intent();
                            answerIntent.putExtra("receiptStr", (qrcodes.valueAt(0).displayValue));
                            setResult(RESULT_OK, answerIntent);
                            finish();

//                            String[] str = qrcodes.valueAt(0).displayValue.split("&");
//                            Map m = new HashMap<String, String>();
//
//                            for (String s : str) {
//                                String[] split = s.split("=");
//                                switch (s.split("=")[0]) {
//                                    case "fn":
//                                        m.put("fn", split[1]);
//                                        break;
//                                    case "i":
//                                        m.put("i", split[1]);
//                                        break;
//                                    case "fp":
//                                        m.put("fp", split[1]);
//                                        break;
//                                }
//                            }
//                            SharedPreferences preferences = getSharedPreferences(MainActivity.APP_PREFERENCES, android.content.Context.MODE_PRIVATE);
//                            String login = preferences.getString("login", "");
//                            ParseTaskReceipt parseTaskReceipt = new ParseTaskReceipt(CameraActivity.this);
//                            parseTaskReceipt.execute(login, (String) m.get("fn"), (String) m.get("i"), (String) m.get("fp"));
                        }
                    });
                }
                }
        });
    }

    @Override
    public void finishedAsyncTask(Receipt receipt) {
        super.finishedAsyncTask(receipt);

//        Intent answerIntent = new Intent();
//        answerIntent.putExtra("receipt", receipt);
//        setResult(RESULT_OK, answerIntent);
//        finish();
    }
}
