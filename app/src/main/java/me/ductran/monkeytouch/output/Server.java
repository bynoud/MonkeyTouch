package me.ductran.monkeytouch.output;

import android.os.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import me.ductran.monkeytouch.MainADB;
import me.ductran.monkeytouch.log.Log;

/**
 * Created by shyri on 08/09/17.
 */

public class Server {
    private ServerSocket serverSocket;
    private Handler handler;
    private Thread serverThread;

    public Server(Handler handler) {
        this.handler = handler;
    }

    public void start() {
        serverThread = new Thread(new ServerThread());
        serverThread.start();
    }

    class ServerThread implements Runnable {

        public void run() {
            Socket socket = null;
            Log.l("Starting server");
            try {
                serverSocket = new ServerSocket(MainADB.DEFAULT_PORT);

                while (!Thread.currentThread()
                              .isInterrupted()) {
                    socket = serverSocket.accept();
                    InputReceiver inputReceiver = new InputReceiver(socket, handler);
                    inputReceiver.start();
                }
                Log.l("Starting server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
