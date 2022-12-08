package com.example.shen_doodle_cisc682;

import android.Manifest;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.io.File;
import java.security.Permission;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String filename;
    File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/myDoodles");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DoodleView doodleCanvas = findViewById(R.id.doodleView);
        Button clearButton = findViewById(R.id.clearButton);
        Button redoButton = findViewById(R.id.redoButton);
        Button undoButton = findViewById(R.id.undoButton);
        SeekBar brushBar = findViewById(R.id.brushBar);
        brushBar.setMax(45);

        askPermission();

        SimpleDateFormat format = new SimpleDateFormat("yyyMMdd_HHmmss", Locale.getDefault());
        String date = format.format(new Date());
        filename = path + "/" + date + ".png";

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleCanvas.clear();
            }
        });

        // Button colorButton = findViewById(R.id.colorButton);

        /*
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                public void onColorPicked(int color) {
                    doodleCanvas.changeColor(color);
                }
                // doodleCanvas.changeColor(color);
            }
        });
        */

        redoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleCanvas.redo();
            }
        });

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleCanvas.undo();
            }
        });

        brushBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                doodleCanvas.changeWidth(seekBar.getProgress());
            }
        });

        /*
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!doodleCanvas.getView().isEmpty()) {

                }
            }
        });
         */
    }
    private void askPermission() {
        Dexter.withContext(this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE).withListener(new MultiplePermissionsListener() {
            @Override
            public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {
                Toast.makeText(MainActivity.this, "Granted!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        });
    }
}