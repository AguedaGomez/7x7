package an.an7x7.framework;

import android.graphics.Bitmap;

import an.an7x7.framework.TouchHandler.TouchEvent;

import java.util.List;

/**
 * Created by jvilar on 29/03/16.
 */
public interface IGameController {
    void onUpdate(float deltaTime, List<TouchEvent> touchEvents);
    Bitmap onDrawingRequested();
}
