package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class ArcDrawer {
    public void draw(ArcDrawable drawable, SurfaceHolder surfaceHolder) {
        Canvas canvas = null;

        try {
            canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                Log.d("DRAWER", "drawing");
                canvas.drawColor(Color.BLACK);
                canvas.drawArc(drawable.rect, drawable.startAngle, drawable.sweepAngle, false, drawable.paint);
            }
        }
        catch (Exception e) {
            Log.d("DRAWER", "Error getting canvas from surface holder on draw" + System.lineSeparator() +
                    "Message: " + e.getMessage() + System.lineSeparator() +
                    "Stack trace: " + e.getStackTrace());
        }
        finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
