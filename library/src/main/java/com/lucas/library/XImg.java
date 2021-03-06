package com.lucas.library;

import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.bumptech.glide.request.target.NotificationTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade;

/**
 * author : 许进进
 * time   : 2021/6/16 12:41 PM
 * des    : 图处加载类
 * 支持为view,notifaication,appwidget加载图片
 */
public final class XImg {

    public static XImg getIns() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static XImg instance = new XImg();
    }


    // 常见的加载
    public void loadImg(ImageView imageView, String url) {
        this.loadImg(imageView, url, null);
    }
    public void loadImg(ImageView imageView, Drawable drawable) { this.loadImg(imageView, drawable, null); }

    //带回调的加载图片方法  待实现
    public void loadImg(ImageView imageView, String url, Object object) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(withCrossFade())
                .into(imageView);
    }
    public void loadImg(ImageView imageView, Drawable drawable, Object object) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(drawable)
                .apply(initCommonRequestOption())
                .transition(withCrossFade())
                .into(imageView);
    }


    // ---- 加载圆形图 ---
    public void loadImgCircle(final ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new BitmapImageViewTarget(imageView) {
                    @Override
                    protected void setResource(final Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(imageView.getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        imageView.setImageDrawable(circularBitmapDrawable);
                    }
                });

    }


    public void loadImgCircle(final ImageView imageView, String url, int radius) {
        loadImgCircle(imageView, url, radius, 0);
    }


    public void loadImgCircle(final ImageView imageView, String url, int radius, int margin) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .apply(RequestOptions.bitmapTransform(new RoundedCornersTransformation(radius, 0)))
                .into(imageView);
    }


    // 加载高斯模糊图
    public void loadImgBlur(final ImageView imageView, String url) {
        loadImgBlur(imageView, url, 25, 10);
    }


    public void loadImgBlur(final ImageView imageView, String url, int radius, int sampling) {
        Glide.with(imageView.getContext())
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(radius, sampling)))
                .into(imageView);
    }


    public void loadImg4ViewGroup(final ViewGroup group, String url) {
        Glide.with(group.getContext())
                .asDrawable()
                .load(url)
                .apply(initCommonRequestOption())
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        group.setBackground(resource);
                    }
                });
    }

    public void loadImg4ViewGroupBlur(final ViewGroup group, String url) {
        Glide.with(group.getContext())
                .asDrawable()
                .load(url)
                .apply(initCommonRequestOption())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 10)))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        group.setBackground(resource);
                    }
                });
    }
    public void loadImg4ViewGroupBlur(final ViewGroup group, String url,  int radius, int sampling) {
        Glide.with(group.getContext())
                .asDrawable()
                .load(url)
                .apply(initCommonRequestOption())
                .apply(RequestOptions.bitmapTransform(new BlurTransformation(radius, sampling)))
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        group.setBackground(resource);
                    }
                });
    }


    // 为非view加载图片
    private void loadImg4Target(Context context, Target target, String url) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .apply(initCommonRequestOption())
                .transition(withCrossFade())
                .fitCenter()
                .into(target);
    }

    // 为notification加载图片
    public void displayImageForNotification(Context context, RemoteViews rv, int id, Notification notification, int NOTIFICATION_ID, String url) {
        this.loadImg4Target(context, initNotificationTarget(context, id, rv, notification, NOTIFICATION_ID), url);
    }


    // 初始化Notification Target
    private NotificationTarget initNotificationTarget(Context context, int id, RemoteViews rv, Notification notification, int NOTIFICATION_ID) {
        NotificationTarget notificationTarget = new NotificationTarget(context, id, rv, notification, NOTIFICATION_ID);
        return notificationTarget;
    }


    private RequestOptions initCommonRequestOption() {
        return new RequestOptions()
                .placeholder(R.drawable.img_holder)
                .error(R.drawable.img_holder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(false)
                .priority(Priority.NORMAL);
    }

}
