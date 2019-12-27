package com.india.projectstructure;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class GlideConnector {
    private static GlideConnector instance = null;

    private GlideConnector() {

    }

    public static GlideConnector getInstance() {
        if (instance == null) {
            instance = new GlideConnector();
        }
        return instance;
    }


    //create the progressDialog object
    public ProgressDialog createProgressDialog(Context context) {
        final ProgressDialog dlg = new ProgressDialog(context);
        dlg.setTitle("Loading...");
        dlg.setIndeterminate(false);                            //indeterminate= circular progress
        dlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return dlg;
    }


//    public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
//        RequestOptions options = new RequestOptions().disallowHardwareConfig();
//        Glide.with(context)
//                .load(imageURL)
//                .apply(options)
//                .into(imageView);
//    }

//    /**Adding Placeholder, and better adjustment to image */
//    public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
//        RequestOptions options = new RequestOptions().disallowHardwareConfig();
//        RequestOptions requestOptionPlaceHolder = new RequestOptions().placeholder(R.drawable.ic_placeholder).autoClone();
//        Glide.with(context)
//                .load(imageURL)
//                .apply(options)
//                .apply(requestOptionPlaceHolder)
//                .into(imageView);
//    }

    /**
     * Adding some fading animation
     */
//    public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
//        RequestOptions options = new RequestOptions().disallowHardwareConfig();
//        RequestOptions requestOptionPlaceHolder = new RequestOptions().placeholder(R.drawable.ic_placeholder).autoClone();
//        Glide.with(context)
//                .load(imageURL)
//                .apply(options)
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .apply(requestOptionPlaceHolder)
//                .into(imageView);
//    }


    /**lower resolution image (i.e. similar image with smaller size) for loading.
     * Some Image Server do provide you with such capability.
     *  This allow you to load your interim image faster due to it’s smaller size.*/
    public void loadImageDirectly(Context context, String imageURL, ImageView imageView) {
        RequestOptions options = new RequestOptions().disallowHardwareConfig();
        RequestOptions requestOptionPlaceHolder = new RequestOptions().placeholder(R.drawable.ic_placeholder).autoClone();
        Glide.with(context)
                .load(imageURL)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(Glide.with(context)
                        .load(imageURL)
                .apply(requestOptionPlaceHolder))
                .into(imageView);
    }

    public void loadImageDirectly(Context context, String imageURL, CircleImageView imageView) {
        RequestOptions options = new RequestOptions().disallowHardwareConfig();
        RequestOptions requestOptionPlaceHolder = new RequestOptions().placeholder(R.drawable.ic_placeholder).autoClone();
        Glide.with(context)
                .load(imageURL)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .thumbnail(Glide.with(context)
                        .load(imageURL)
                        .apply(requestOptionPlaceHolder))
                .into(imageView);
    }

}
