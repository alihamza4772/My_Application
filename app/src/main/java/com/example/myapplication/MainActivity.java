package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;

import com.blundell.woody.Woody;
import com.camerakit.CameraKit;
import com.camerakit.CameraKitView;

import me.ibrahimsn.lib.BottomBarItem;
import me.ibrahimsn.lib.SmoothBottomBar;

import static android.hardware.Camera.CameraInfo.CAMERA_FACING_FRONT;

public class MainActivity extends AppCompatActivity {
    private CameraKitView cameraKitView;

SmoothBottomBar smoothBottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraKitView =findViewById(R.id.camera);
        Woody.onCreateMonitor(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
       cameraKitView.setGestureListener(new CameraKitView.GestureListener() {
           @Override
           public void onTap(CameraKitView cameraKitView, float v, float v1) {

           }

           @Override
           public void onLongTap(CameraKitView cameraKitView, float v, float v1) {

           }

           @Override
           public void onDoubleTap(CameraKitView cameraKitView, float v, float v1) {
               if(cameraKitView.getFacing()==CameraKit.FACING_FRONT)
               {
                   cameraKitView.setFacing(CameraKit.FACING_BACK);
               }
               else
                   cameraKitView.setFacing(CameraKit.FACING_FRONT);
           }

           @Override
           public void onPinch(CameraKitView cameraKitView, float v, float v1, float v2) {

           }
       });
cameraKitView.captureVideo(new CameraKitView.VideoCallback() {
    @Override
    public void onVideo(CameraKitView cameraKitView, Object o) {

    }
});
    }

    @Override
    protected void onResume() {
        super.onResume();
        cameraKitView.onResume();
    }

    @Override
    protected void onPause() {
        cameraKitView.onPause();
        super.onPause();
    }

    @Override
    protected void onStop() {
        cameraKitView.onStop();
        super.onStop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        cameraKitView.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
