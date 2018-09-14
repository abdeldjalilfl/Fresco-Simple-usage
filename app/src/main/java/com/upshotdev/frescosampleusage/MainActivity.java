package com.upshotdev.frescosampleusage;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.view.SimpleDraweeView;
import com.upshotdev.frescosampleusage.helper.Fresco;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //loading and display images Using Fresco
        updateUi();

        //clear cache of images using ImagePipeline
        //Fresco.clearCache();
    }
    private void updateUi(){

        //first example : image_circle
        Fresco.newBuilder((SimpleDraweeView) findViewById(R.id.image_circle))
                .setPlaceholderImage(R.color.grey200)
                .withProgressBar(true) //with circular progressBar
                .roundAsCircle(true)
                .setProgressBarColor(R.color.colorAccent,R.color.grey500)
                .setRoundCircleColor(R.color.colorAccent)
                .setUri(Uri.parse("https://www.psdstack.com/wp-content/uploads/2016/10/featured-copyright-free-mages.jpg"))
                .build(this);

        //second example : image_simple
        Fresco.newBuilder((SimpleDraweeView) findViewById(R.id.image_simple))
                .setUri(Uri.parse("https://www.psdstack.com/wp-content/uploads/2016/10/featured-copyright-free-mages.jpg"))
                .build(this);

    }
}
