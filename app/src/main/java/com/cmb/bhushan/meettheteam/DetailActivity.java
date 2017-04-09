package com.cmb.bhushan.meettheteam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DetailActivity extends AppCompatActivity {
    TextView textView;
    TextView textView2;
    TextView textView3;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textView = (TextView) findViewById(R.id.textView);
        // get the intent from which this activity is called.
        final Intent intent = getIntent();

        // fetch value from key-value pair and make it visible on TextView.
        String fName= intent.getStringExtra("firstName");
        String lName= intent.getStringExtra("lastName");

        textView.setText(fName+" "+lName);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView2.setText("Title: "+intent.getStringExtra("title"));

        textView3 = (TextView) findViewById(R.id.textView3);
        textView3.setText(intent.getStringExtra("bio"));

        imageView = (ImageView) findViewById(R.id.imageView);

        new Thread(new Runnable() {
            public void run() {
                final Bitmap b = loadImageFromNetwork(intent);
                imageView.post(new Runnable() {
                    public void run() {
                        imageView.setImageBitmap(b);
                    }
                });
            }
        }).start();
    }

    private Bitmap loadImageFromNetwork(Intent intent){
        URL url = null;
        try {
            url = new URL(intent.getStringExtra("image"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }
}
