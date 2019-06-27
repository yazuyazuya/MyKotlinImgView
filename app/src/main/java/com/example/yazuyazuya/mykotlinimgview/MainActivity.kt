package com.example.yazuyazuya.mykotlinimgview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageFrame : ImageView = findViewById(R.id.image1)
        val rotateButton : Button = findViewById(R.id.button1)
        val monoButton : Button = findViewById(R.id.button2)
        val resetButton : Button = findViewById(R.id.button3)
        val rotateButton2 : Button = findViewById(R.id.button4)
        val reverseButton : Button = findViewById(R.id.button5)

        val bitmap1 : Bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image1)
        var bitmap2 : Bitmap = Bitmap.createBitmap(bitmap1)

        rotateButton.setOnClickListener {
            val bitmap3 : Bitmap = Bitmap.createBitmap(bitmap2.height, bitmap2.width, Bitmap.Config.ARGB_8888)
            rotateBitmap(bitmap3, bitmap2)

            imageFrame.setImageBitmap(bitmap3)
            bitmap2 = bitmap3
        }

        rotateButton2.setOnClickListener {
            val bitmap3 : Bitmap = Bitmap.createBitmap(bitmap2.height, bitmap2.width, Bitmap.Config.ARGB_8888)
            rotateBitmap2(bitmap3, bitmap2)

            imageFrame.setImageBitmap(bitmap3)
            bitmap2 = bitmap3
        }

        reverseButton.setOnClickListener {
            val bitmap3 : Bitmap = Bitmap.createBitmap(bitmap2.width, bitmap2.height, Bitmap.Config.ARGB_8888)
            reverseBitmap(bitmap3, bitmap2)

            imageFrame.setImageBitmap(bitmap3)
            bitmap2 = bitmap3
        }

        monoButton.setOnClickListener {
            var bitmap3 : Bitmap = Bitmap.createBitmap(bitmap2.width, bitmap2.height, Bitmap.Config.ARGB_8888)
            monoBitmap(bitmap3, bitmap2)

            imageFrame.setImageBitmap(bitmap3)
            bitmap2 = bitmap3
        }

        resetButton.setOnClickListener {
            imageFrame.setImageBitmap(bitmap1)
            bitmap2 = bitmap1
        }

    }

    fun rotateBitmap (outBitmap: Bitmap, inBitmap: Bitmap) {
        val width : Int = inBitmap.width
        val height : Int = inBitmap.height
        var color : Int

        for (i in 0..height - 1 step 1) {
            for (j in 0..width - 1 step 1) {
                color = inBitmap.getPixel(j, i)
                outBitmap.setPixel(height - 1 - i, j, color)
            }
        }

    }

    fun rotateBitmap2 (outBitmap: Bitmap, inBitmap: Bitmap) {
        val width : Int = inBitmap.width
        val height : Int = inBitmap.height
        var color : Int

        for (i in 0..height - 1 step 1) {
            for (j in 0..width - 1 step 1) {
                color = inBitmap.getPixel(j, i)
                outBitmap.setPixel(i, width - 1 - j, color)
            }
        }

    }

    fun reverseBitmap (outBitmap: Bitmap, inBitmap: Bitmap) {
        val width : Int = inBitmap.width
        val height : Int = inBitmap.height
        var color : Int

        /*
        for (i in 0..height - 1 step 1) {
            for (j in 0..width - 1 step 1) {
                color = inBitmap.getPixel(j, i)
                outBitmap.setPixel(i, j, color)
            }
        }
        */

        for (i in 0..height - 1 step 1) {
            for (j in 0..width - 1 step 1) {
                color = inBitmap.getPixel(j, i)
                if (width < height || width == height) {
                    outBitmap.setPixel(width - 1 - j, i, color)
                } else if (width > height) {
                    outBitmap.setPixel(j, height - 1 - i, color)
                }

            }
        }

    }

    fun monoBitmap (outBitmap: Bitmap, inBitmap: Bitmap) {
        val width : Int = outBitmap.width
        val height : Int = outBitmap.height
        var color : Int
        var mono : Int

        for (i in 0..height - 1 step 1) {
            for (j in 0..width - 1 step 1) {
                color = inBitmap.getPixel(j, i)
                mono = (Color.red(color) + Color.green(color) + Color.blue(color)) / 3
                outBitmap.setPixel(j, i, Color.rgb(mono, mono, mono))
            }
        }
    }

}
