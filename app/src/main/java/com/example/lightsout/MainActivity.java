package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final int GRID_SIZE = 3;
    private GridLayout grid;
    private boolean cellState[][];
    TextView score;
    Button lightsOff;
    Button randomize;
    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button current = (Button) v;

            for (int i = 0; i < grid.getChildCount(); i++) {
                Button gridButton = (Button) grid.getChildAt(i);

                if (gridButton == current) {
                    int row = i / GRID_SIZE;
                    int col = i % GRID_SIZE;

                    if (cellState[row][col]) {
                        cellState[row][col] = false;
                    } else {
                        cellState[row][col] = true;
                    }
                }
            }
            recolor();
            setScore();
        }
    };
    View.OnClickListener lightsOffListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button gridButton;
            int row, col;
            for (int i = 0; i< grid.getChildCount(); i++) {
                gridButton = (Button) grid.getChildAt(i);
                row = i / GRID_SIZE;
                col = i % GRID_SIZE;
                if (cellState[row][col]) {
                    cellState[row][col] = false;
                }
            }
            recolor();
            setScore();
        }
    };
    View.OnClickListener randomizeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            randomize();
            recolor();
            setScore();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cellState = new boolean[][]{{true, true, true}, {true, true, true}, {true, true, true}};

        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.light_grid);
        score = findViewById(R.id.scoreNum);
        randomize = findViewById(R.id.randomize);
        randomize.setOnClickListener(randomizeListener);
        lightsOff = findViewById(R.id.lights_off);
        lightsOff.setOnClickListener(lightsOffListener);
        randomize();
        recolor();
        setScore();
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button currButton = (Button) grid.getChildAt(i);
            currButton.setOnClickListener(buttonListener);
        }
    }

    public void recolor() {
        for (int i = 0; i < grid.getChildCount(); i++) {
            Button gridButton = (Button) grid.getChildAt(i);

            // Find the button's row and col
            int row = i / GRID_SIZE;
            int col = i % GRID_SIZE;

            if (cellState[row][col] == true) {
                gridButton.setBackgroundColor(getColor(R.color.blue_500));
            } else {
                gridButton.setBackgroundColor(getColor(R.color.black));
            }
        }
    }
    public void setScore() {
        int count = 0;
        for (int i = 0; i < cellState.length; i++) {
            for (int j = 0; j < cellState[i].length; j++) {
                if (cellState[i][j] == true) {
                    count++;
                }
            }
        }
        score.setText("" + count);
    }

    public void randomize() {
        Random random = new Random();
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                cellState[i][j] = random.nextBoolean();
            }
        }
    }


}