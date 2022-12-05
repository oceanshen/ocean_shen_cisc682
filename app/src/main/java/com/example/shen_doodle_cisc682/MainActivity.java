package com.example.shen_doodle_cisc682;

import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DoodleView doodleCanvas = findViewById(R.id.doodleView);
        Button clearButton = findViewById(R.id.clearButton);
        SeekBar brushBar = findViewById(R.id.brushBar);
        brushBar.setMax(25);

        // Button colorButton = findViewById(R.id.colorButton);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doodleCanvas.clear();
            }
        });

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

        brushBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                doodleCanvas.changeWidth(seekBar.getProgress());
            }
        });
    }

}