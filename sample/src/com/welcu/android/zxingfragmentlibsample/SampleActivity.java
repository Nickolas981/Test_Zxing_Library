package com.welcu.android.zxingfragmentlibsample;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

import com.welcu.android.zxingfragmentlib.BarCodeScannerFragment;

/**
 * Created by mito on 9/17/13.
 */
public class SampleActivity extends FragmentActivity {

    boolean torchState = false;

    Button mToggleButton;
    Button on;
    Button off;
    BarCodeScannerFragment mScannerFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.welcu.android.zxingfragmentlibsample.R.layout.activity_sample);

        FragmentManager fm = getSupportFragmentManager();
        mScannerFragment = (BarCodeScannerFragment) fm.findFragmentById(R.id.scanner_fragment);

        mToggleButton = (Button) findViewById(R.id.button_flash);
        mToggleButton.setOnClickListener(createToggleFlashListener());
        on = (Button) findViewById(R.id.on);
        off = (Button) findViewById(R.id.off);
        on.setOnClickListener(view -> mScannerFragment.startScan());
        off.setOnClickListener(view -> mScannerFragment.stopScan());
    }

    private View.OnClickListener createToggleFlashListener() {
        return v -> {
          torchState = !torchState;
          mScannerFragment.setTorch(torchState);
        };
    }
}