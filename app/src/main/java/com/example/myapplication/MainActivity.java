package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.camerakit.CameraKit;
import com.camerakit.CameraKitView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    private CameraKitView cameraKitView;
ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cameraKitView = findViewById(R.id.camera);
//        imageView =findViewById(R.id.imageView);
//        FloatingActionButton floatingActionButton = new FloatingActionButton.Builder(this).setContentView(imageView).build();
//        SubActionButton.Builder builder = new SubActionButton.Builder(MainActivity.this);
//        ImageView live =new ImageView(this);
//        live.setImageResource(R.drawable.video);
//        SubActionButton livebtn = builder.setContentView(live).build();
//
//        ImageView upload =new ImageView(this);
//        live.setImageResource(R.drawable.upload);
//        SubActionButton uploadbtn = builder.setContentView(upload).build();
//
//        FloatingActionMenu floatingActionMenu = new FloatingActionMenu.Builder(this).addSubActionView(livebtn).addSubActionView(uploadbtn).attachTo(floatingActionButton).build();
    }


    @Override
    protected void onStart() {
        super.onStart();
        cameraKitView.onStart();
       cameraKitView.setGestureListener(new CameraKitView.GestureListener() {
           @Override
           public void onTap(CameraKitView cameraKitView, float v, float v1) {
          cameraKitView.captureImage(new CameraKitView.ImageCallback() {
    @Override
    public void onImage(CameraKitView cameraKitView, final byte[] bytes) {
        File savedPhoto = new File(Environment.getExternalStorageDirectory(), "photo.jpg");
        try {
            FileOutputStream outputStream = new FileOutputStream(savedPhoto.getPath());
            outputStream.write(bytes);
            outputStream.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
});
           }

           @Override
           public void onLongTap(CameraKitView cameraKitView, float v, float v1) {
     if(cameraKitView.getFlash()==CameraKit.FLASH_OFF)
     {
         cameraKitView.setFlash(CameraKit.FLASH_TORCH);
     }
     else
     {
         cameraKitView.setFlash(CameraKit.FLASH_OFF);
     }
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
