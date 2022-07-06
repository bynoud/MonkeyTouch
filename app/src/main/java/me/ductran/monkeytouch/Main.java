package me.ductran.monkeytouch;

//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello word");
//    }
//}

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import me.ductran.monkeytouch.log.Log;
import me.ductran.monkeytouch.models.SocketMessage;
import me.ductran.monkeytouch.output.Server;
import me.ductran.monkeytouch.output.TouchMapper;
import me.ductran.monkeytouch.output.config.ConfigParser;
import me.ductran.monkeytouch.output.config.TouchConfig;
import me.ductran.monkeytouch.output.touch.CircleMapping;

import static me.ductran.monkeytouch.EventInput.SOURCE_KEY;
import static me.ductran.monkeytouch.EventInput.SOURCE_MOVEMENT;

/**
 * Created by shyri on 06/09/17.
 */

public class Main {
//    static Looper looper;
    private TouchMapper touchMapper;

    public static final int DEFAULT_PORT = 6543;

//    Handler messageHandler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            Log.l("Message get " + msg.what);
//            switch (msg.what) {
//                case SOURCE_MOVEMENT:
//                    touchMapper.processEvent((MotionEvent) msg.obj);
//                    break;
//                case SOURCE_KEY:
//                    touchMapper.processEvent((KeyEvent) msg.obj);
//                    break;
//            }
//        }
//    };

    private Server server;

    public Main(Looper looper) {

//        TouchConfig touchConfig = null;
//        try {
//            touchConfig = readFile("/storage/self/primary/Android/data/es.shyri.touchmapper/files/mapping.json");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
        Log.l("Mainnnnn");
//        Log.d("Mainnnnn1");
        CircleMapping c = new CircleMapping(500,1400, 300);
        c.axis_x = MotionEvent.AXIS_X;
        c.axis_y = MotionEvent.AXIS_Y;
        TouchConfig touchConfig = new TouchConfig();
        touchConfig.deviceId = "a718a782d34bc767f4689c232d64d527998ea7fd";
        touchConfig.mappings = new ArrayList<>(Collections.singletonList(c));
        Log.l("Main 2");
//        Log.d("Main 223");

        try {
            touchMapper = new TouchMapper(touchConfig);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Handler messageHandler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                SocketMessage m = (SocketMessage) msg.obj;
                Log.l("Message get " + m.getType());
//                Log.l("Message get " + msg.what);
//                switch (msg.what) {
//                    case SOURCE_MOVEMENT:
//                        touchMapper.processEvent((MotionEvent) msg.obj);
//                        break;
//                    case SOURCE_KEY:
//                        touchMapper.processEvent((KeyEvent) msg.obj);
//                        break;
//                }
            }
        };

        server = new Server(messageHandler);
        server.start();
    }

    public static void main(String[] args) {
        Log.l("main start");
        Looper.prepare();

        Main main = new Main(Looper.myLooper());

        Looper.loop();
    }

    private TouchConfig readFile(String fileName) throws FileNotFoundException {
        ConfigParser configParser = new ConfigParser();
        return configParser.parseConfig(new File(fileName));
    }
}
