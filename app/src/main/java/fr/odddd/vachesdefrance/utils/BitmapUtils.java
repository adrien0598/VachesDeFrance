package fr.odddd.vachesdefrance.utils;

import android.graphics.Bitmap;
import android.media.ThumbnailUtils;

public class BitmapUtils {

    private static final int val_max = 1000;

    public Bitmap resize(Bitmap oldBitmap) {
        int height = oldBitmap.getHeight();
        int width = oldBitmap.getWidth();
        Bitmap newBitmap;
        if (Math.max(height, width) > val_max) {
            if (height > val_max && height > width) {
                newBitmap = ThumbnailUtils.extractThumbnail(
                        oldBitmap,
                        (val_max * width) / height,
                        val_max
                );
            } else {
                newBitmap = ThumbnailUtils.extractThumbnail(
                        oldBitmap,
                        val_max,
                        (val_max * height) / width
                );
            }
        } else {
            newBitmap = oldBitmap;
        }
        return newBitmap;
    }
}
