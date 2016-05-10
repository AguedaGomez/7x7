package com.bubbles.an.ujibubbles.framework;

import android.graphics.Bitmap;

import com.bubbles.an.ujibubbles.framework.TouchHandler.TouchEvent;

import java.util.List;

/**
 * Created by jvilar on 29/03/16.
 */
public interface IGameController {
    void onUpdate(float deltaTime, List<TouchEvent> touchEvents);
    Bitmap onDrawingRequested();
}
