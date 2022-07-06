package me.ductran.monkeytouch.output;

import android.os.Handler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import me.ductran.monkeytouch.Main;
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
            Log.l("Starting server 2");
            try {
                serverSocket = new ServerSocket(Main.DEFAULT_PORT);
                Log.l("socket");

                while (!Thread.currentThread()
                              .isInterrupted()) {
                    Log.l("socket accepting");
                    socket = serverSocket.accept();
                    Log.l("socket accepted");
                    InputReceiver inputReceiver = new InputReceiver(socket, handler);
                    Log.l("socket starting");
                    inputReceiver.start();
                }
                Log.l("End server");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
