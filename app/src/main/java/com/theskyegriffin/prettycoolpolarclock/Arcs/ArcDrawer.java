package com.theskyegriffin.prettycoolpolarclock.Arcs;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;

public class ArcDrawer {
    private SurfaceHolder surfaceHolder;
    private float viewHeightMidpoint = 0;
    private float viewWidthMidpoint = 0;

    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    public void setViewDimensions(float viewHeight, float viewWidth) {
        viewHeightMidpoint = viewHeight / 2;
        viewWidthMidpoint = viewWidth / 2;
    }

    public void drawArcs(ArrayList<Arc> arcs) {
        for (Arc arc : arcs) {
            draw(arc.getDrawable(viewHeightMidpoint, viewWidthMidpoint));
        }
    }

    public void draw(ArcDrawable drawable) {
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
