package com.welcu.android.zxingfragmentlibsample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.LinearLayout;

import com.welcu.android.zxingfragmentlib.BarCodeScannerFragment;

/**
 * Created by joyarzun on 4/8/14.
 */
public class SampleStretchedActivity extends FragmentActivity {
    boolean torchState = false;

    LinearLayout layoutContent;
    Button mToggleButton;
    BarCodeScannerFragment mScannerFragment;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.welcu.android.zxingfragmentlibsample.R.layout.activity_stretched_sample);

        FragmentManager fm = getSupportFragmentManager();
        mScannerFragment = (BarCodeScannerFragment) fm.findFragmentById(R.id.scanner_fragment);

        layoutContent = findViewById(R.id.layout_content);

        final ViewTreeObserver observer = layoutContent.getViewTreeObserver();

        observer.addOnGlobalLayoutListener(() -> {
            // We're assuming that the other layout is under the scanner
            int activityWidth = layoutContent.getWidth();
            int activityHeight = findViewById(R.id.scanner_fragment).getHeight();

            int usableWidth = layoutContent.getWidth();
            int usableHeight = activityHeight - layoutContent.getHeight();

            int desiredHeight = (int) (usableHeight * 0.8);
            int desiredWidth = (int) (usableWidth * 0.75);

            Rect framingRect = new Rect(
                    (usableWidth - desiredWidth) / 2, // left
                    (usableHeight - desiredHeight) / 2, // top
                    (usableWidth - desiredWidth) / 2 + desiredWidth, // right
                    (usableHeight - desiredHeight) / 2 + desiredHeight// bottom
            );
            Log.v("RECT", "left: " + framingRect.left + " top: " + framingRect.top + " right: " + framingRect.right + " bottom: " + framingRect.bottom + " activityHeight: " + activityHeight + " activitiWidth: " + activityWidth);
            mScannerFragment.setFramingRect(framingRect);
        });

//    mToggleButton = (Button) findViewById(R.id.button_flash);
//    mToggleButton.setOnClickListener(createToggleFlashListener());

    }

    private View.OnClickListener createToggleFlashListener() {
        return v -> {
            torchState = !torchState;
            mScannerFragment.setTorch(torchState);
        };
    }
}
