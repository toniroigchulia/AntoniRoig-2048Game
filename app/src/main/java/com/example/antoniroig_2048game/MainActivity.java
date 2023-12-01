package com.example.antoniroig_2048game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private int[][] tileNumbers = new int[4][4];
    private GridView tileList;
    private Adaptador tileAdapter;
    private float startX, startY, endX, endY;
    private static final int MIN_DISTANCE = 150;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tileList = findViewById(R.id.tileList);

        for (int i = 0; i < tileNumbers.length; i++){
            for (int j = 0; j < tileNumbers[i].length; j ++){

                tileNumbers[i][j] = 0;
            }
        }

        tileNumbers = generateNew2(tileNumbers);
        tileNumbers = generateNew2(tileNumbers);

        tileAdapter = new Adaptador(this, matrizToArray(tileNumbers));
        tileList.setAdapter(tileAdapter);
        tileList.setOnTouchListener(this);
    }

    private void adapterUpdate(Integer[] arrayNumeros) {
        this.tileAdapter.setNumeros(arrayNumeros);
        this.tileAdapter.notifyDataSetChanged();
    }

    private Integer[] matrizToArray (int[][] numbers) {
        Integer[] array = new Integer[16];
        int arrayIndex = 0;

        for (int i = 0; i < numbers.length; i++){
            for (int j = 0; j < numbers[i].length; j ++){
                array[arrayIndex] = numbers[i][j];
                arrayIndex = arrayIndex + 1;
            }
        }

        return array;
    }

    private int[][] generateNew2(int[][] numbers){
        Random rand = new Random();
        boolean continueTrying = true;

        while (continueTrying){
            int posXrand = rand.nextInt(4);
            int posYrand = rand.nextInt(4);

            if(numbers[posYrand][posXrand] == 0){
                numbers[posYrand][posXrand] = 2;
                continueTrying = false;
            }
        }

        return numbers;
    }

    private int[][] moveTiles(int direccion, int[][] numbers){
        boolean cambio = true;
        switch (direccion) {
            // Derecha
            case 1:
                while (cambio) {
                    cambio = false;
                    for (int i = 0; i < numbers.length; i++){
                        for (int j = numbers[i].length-2; j >= 0; j--){
                            if(numbers[i][j] != 0 && numbers[i][j+1] == 0){
                                numbers[i][j+1] = numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            } else if (numbers[i][j] != 0 && numbers[i][j+1] == numbers[i][j]) {
                                numbers[i][j+1] = numbers[i][j] + numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            }
                        }
                    }
                }
                break;
            // Izquierda
            case 2:
                while (cambio){
                    cambio = false;
                    for (int i = 0; i < numbers.length; i++){
                        for (int j = 1; j < numbers.length; j++){
                            if(numbers[i][j] != 0 && numbers[i][j-1] == 0){
                                numbers[i][j-1] = numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            } else if (numbers[i][j] != 0 && numbers[i][j-1] == numbers[i][j]) {
                                numbers[i][j-1] = numbers[i][j] + numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            }
                        }
                    }
                }
                break;
            // Arriba
            case 3:
                while (cambio){
                    cambio = false;
                    for (int i = 1; i < numbers.length; i++){
                        for (int j = 0; j < numbers.length; j++){
                            if(numbers[i][j] != 0 && numbers[i-1][j] == 0){
                                numbers[i-1][j] = numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            } else if (numbers[i][j] != 0 && numbers[i-1][j] == numbers[i][j]) {
                                numbers[i-1][j] = numbers[i][j] + numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            }
                        }
                    }
                }
                break;
            // Abajo
            case 4:
                while (cambio){
                    cambio = false;
                    for (int i = numbers.length-2; i >= 0; i--){
                        for (int j = 0; j < numbers.length; j++){
                            if(numbers[i][j] != 0 && numbers[i+1][j] == 0){
                                numbers[i+1][j] = numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            } else if (numbers[i][j] != 0 && numbers[i+1][j] == numbers[i][j]) {
                                numbers[i+1][j] = numbers[i][j] + numbers[i][j];
                                numbers[i][j] = 0;
                                cambio = true;
                            }
                        }
                    }
                }
                break;
        }
        generateNew2(numbers);
        adapterUpdate(matrizToArray(numbers));
        return numbers;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                endX = event.getX();
                endY = event.getY();

                float deltaX = endX - startX;
                float deltaY = endY - startY;

                if (Math.abs(deltaX) > Math.abs(deltaY)) {
                    if (Math.abs(deltaX) > MIN_DISTANCE) {
                        if (deltaX > 0) {
                            System.out.println("Derecha");
                            this.tileNumbers = moveTiles(1, this.tileNumbers);
                        } else {
                            System.out.println("Izquierda");
                            this.tileNumbers = moveTiles(2, this.tileNumbers);
                        }
                    }
                } else {
                    if (Math.abs(deltaY) > MIN_DISTANCE) {
                        if (deltaY > 0) {
                            System.out.println("Abajo");
                            this.tileNumbers = moveTiles(4, this.tileNumbers);
                        } else {
                            System.out.println("Arriba");
                            this.tileNumbers = moveTiles(3, this.tileNumbers);
                        }
                    }
                }
                break;
        }
        return true;
    }
}