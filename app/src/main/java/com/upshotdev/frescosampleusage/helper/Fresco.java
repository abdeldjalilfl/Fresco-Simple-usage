/*
 * Copyright (c) 2018.
 * This code created by Abdeldjalil Elaaeieida
 */

package com.upshotdev.frescosampleusage.helper;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.upshotdev.frescosampleusage.R;

/**
 * @author Abdeldjalil
 * Fresco Simple usage takes care of image loading and display Using Fresco
 * dependencies implementation 'com.facebook.fresco:fresco:1.10.0'
 * how to use this class
 * example

        Fresco.newBuilder((SimpleDraweeView) findViewById(R.id.my_image_view))
            .setPlaceholderImage(R.color.grey200)
            .withProgressBar(true)
            .roundAsCircle(true)
            .setProgressBarColor(R.color.colorAccent,R.color.grey500)
            .setRoundCircleColor(R.color.colorAccent)
            .setUri(Uri.parse("https://www.google.dz/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"))
            .build(this);
 *
 */
public class Fresco {
    private static Fresco loadImage;
    private SimpleDraweeView imageView;
    private Uri uri;
    private boolean withProgressBar=true;
    private boolean roundAsCircle=false;
    //
    private int ID_RES_PLACEHOLDER_IMAGE= R.color.grey200;
    //
    private int ID_COLOR_BORDER_CIRCLE=R.color.colorAccent;
    //
    private int ID_COLOR_PROGRESS=R.color.colorAccent;
    private int ID_COLOR_BACKGROUND_PROGRESS=R.color.grey500;



    public static Fresco newBuilder(@NonNull SimpleDraweeView imageView) {
        loadImage=new Fresco(imageView);
        return loadImage;
    }
    private Fresco(@NonNull SimpleDraweeView imageView) {
        this.imageView=imageView;
    }

    /**
     * progress bar
     * @param withProgressBar default value: true
     *
     */
    public Fresco withProgressBar(boolean withProgressBar){
        this.withProgressBar=withProgressBar;
        return loadImage;
    }

    /**
     *
     * @param placeholderImage The placeholder is shown until the image is ready.
     *                         default value : R.color.grey200
     */
    public Fresco setPlaceholderImage(@DrawableRes int placeholderImage){

        this.ID_RES_PLACEHOLDER_IMAGE=placeholderImage;
        return loadImage;
    }

    /**
     *
     * @param roundAsCircle default value : false
     *
     */
    public Fresco roundAsCircle(boolean roundAsCircle){
        this.roundAsCircle =roundAsCircle;
        return loadImage;
    }

    /**
     *
     * @param color R.color.colorAccent
     * @param backgroundColor R.color.grey500
     *
     */
    public Fresco setProgressBarColor(@ColorRes int color, @ColorRes int backgroundColor){
        this.ID_COLOR_PROGRESS=color;
        this.ID_COLOR_BACKGROUND_PROGRESS=backgroundColor;
        return loadImage;
    }

    /**
     *
     * @param color default value: R.color.colorAccent
     *
     */
    public Fresco setRoundCircleColor(@ColorRes int color){
        this.ID_COLOR_BORDER_CIRCLE=color;
        return loadImage;
    }

    public Fresco setUri(Uri uri) {

        this.uri = uri;
        return loadImage;
    }

    /**
     * start downloading the image
     */
    public void build(@NonNull Context context){
        if (imageView!=null) {

            imageView.getHierarchy().setPlaceholderImage(ID_RES_PLACEHOLDER_IMAGE);

            if(uri!=null){
                imageView.setImageURI(uri);
            }
            if (withProgressBar){
                final CircleProgressBarDrawable progressBarDrawable=new CircleProgressBarDrawable();
                progressBarDrawable.setColor(context.getResources().getColor(ID_COLOR_PROGRESS));
                progressBarDrawable.setBackgroundColor(context.getResources().getColor(ID_COLOR_BACKGROUND_PROGRESS));
                progressBarDrawable.setRadius(4);
                //
                imageView.getHierarchy().setProgressBarImage(progressBarDrawable);
            }
            if (roundAsCircle){
                int color = context.getResources().getColor(ID_COLOR_BORDER_CIRCLE);
                RoundingParams roundingParams = RoundingParams.fromCornersRadius(6f);
                roundingParams.setBorder(color, 7.0f);
                roundingParams.setRoundAsCircle(true);
                imageView.getHierarchy().setRoundingParams(roundingParams);
            }
        }
    }
    //
    /**
     * clear cache of images using ImagePipeline
     */
    public static void clearCache(){
        //
        ImagePipeline imagePipeline = com.facebook.drawee.backends.pipeline.Fresco.getImagePipeline();
        imagePipeline.clearMemoryCaches();
        imagePipeline.clearDiskCaches();
        // combines above two lines
        imagePipeline.clearCaches();
    }




}
