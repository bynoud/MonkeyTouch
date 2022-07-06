package me.ductran.monkeytouch.output;

import android.os.Handler;
import android.os.Message;
import android.os.Parcel;
import android.view.KeyEvent;
import android.view.MotionEvent;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import me.ductran.monkeytouch.log.Log;
import me.ductran.monkeytouch.models.SocketMessage;

import static me.ductran.monkeytouch.EventInput.SOURCE_KEY;
import static me.ductran.monkeytouch.EventInput.SOURCE_MOVEMENT;

/**
 * Created by shyri on 08/09/17.
 */

public class InputReceiver {
    private Socket socket;
    private Handler handler;
    private Thread communicationThread;

    InputReceiver(Socket socket, Handler handler) {
        this.socket = socket;
        this.handler = handler;
    }

    void start() {
        communicationThread = new Thread(new CommunicationThread(socket));
        communicationThread.start();
    }

    class CommunicationThread implements Runnable {
        private Socket clientSocket;
        private DataInputStream input;

        public CommunicationThread(Socket clientSocket) {
            this.clientSocket = clientSocket;

            try {
                this.input = new DataInputStream(this.clientSocket.getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                Log.l("InputReciver running");
                int len = input.readInt();
                Log.l("get len " + len);
                byte[] data = new byte[len];
                if (len > 0) {
                    input.readFully(data);
                }

                Parcel parcel = unmarshall(data);

                Message message = new Message();

                message.obj = SocketMessage.CREATOR.createFromParcel(parcel);
                handler.sendMessage(message);

//                int type = parcel.readInt();
//                Log.l("IR type " + type);
//                parcel.setDataPosition(0);
//
//                if (type == SOURCE_MOVEMENT) {
//                    MotionEvent event = MotionEvent.CREATOR.createFromParcel(parcel);
//
//                    message.what = SOURCE_MOVEMENT;
//                    message.obj = event;
//                } else if (type == SOURCE_KEY) {
//                    KeyEvent event = KeyEvent.CREATOR.createFromParcel(parcel);
//
//                    message.what = SOURCE_KEY;
//                    message.obj = event;
//                } else {
//                    Log.l("Message not recognized" + message.obj);
//                }
//
//                handler.sendMessage(message);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private Parcel unmarshall(byte[] bytes) {
        Parcel parcel = Parcel.obtain();
        parcel.unmarshall(bytes, 0, bytes.length);
        parcel.setDataPosition(0);
        return parcel;
    }
}
