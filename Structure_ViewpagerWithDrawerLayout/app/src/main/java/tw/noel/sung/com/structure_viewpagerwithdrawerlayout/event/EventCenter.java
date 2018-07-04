package tw.noel.sung.com.structure_viewpagerwithdrawerlayout.event;


import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by noel on 2018/7/2.
 */

public class EventCenter {


    public static int EVENT_FILE = 11;



    //--------------------------------------------------
    private static EventCenter ourInstance = new EventCenter();

    //--------------------------------------------------
    public static EventCenter getInstance() {
        return ourInstance;
    }

    //--------------------------------------------------
    private EventCenter() {

    }

    //--------------------------------------------------
    private void sendListEvent(int type, List<?> dataList) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", type);
        data.put("data", dataList);
        EventBus.getDefault().post(data);
    }

    //--------------------------------------------------
    private void sendObjectEvent(int type, Object object) {
        Map<String, Object> data = new HashMap<>();
        data.put("type", type);
        data.put("data", object);
        EventBus.getDefault().post(data);
    }

    //--------------------------------------------------

    /**
     * 發送 檔案
     */
    public void sendPhotoFile(int type, File fileg) {
        sendObjectEvent(type, fileg);
    }
}
