package com.example.administrator.imageloadtest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by Administrator on 2019/4/23.
 *  图片压缩
 * @author Administrator
 */
public class ImageResizer {
    private static final String TAG = "ImageResizer";
    public ImageResizer() {
    }

    /**
     * 计算采样率
     *
     * @param options   图片的原始宽高
     * @param reqWidth  需要的宽度
     * @param reqHeight 需要的高度
     * @return
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {

        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;
            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;

    }

    /**
     * 从资源文件加载图片
     * @param res
     * @param resId  图片id
     * @param reqWidth imageview的宽度
     * @param reqHeight iamgeview的高度
     * @return
     */
    public  Bitmap decodeSampledBitmapFromResource(Resources res, int resId, int reqWidth, int reqHeight){
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;//为true是只解析图片的宽高，不会加载图片。
        BitmapFactory.decodeResource(res,resId,options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reqHeight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeResource(res,resId,options);
    }
    public Bitmap decodeSampledBitmapFromFileDescriptor(FileDescriptor fd,int reqWidth,int reHeight){
        final BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFileDescriptor(fd,null,options);
        options.inSampleSize=calculateInSampleSize(options,reqWidth,reHeight);
        options.inJustDecodeBounds=false;
        return BitmapFactory.decodeFileDescriptor(fd,null,options);
    }
}
