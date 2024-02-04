package com.company.ServerConnection;

import com.company.Commands.HandlerRequest;
import com.company.Commands.PasswordCheckDB;
import com.company.Threads.CachedThreadPoll;
//import com.company.Threads.FThread;

import com.company.Threads.LThread;
import com.company.data.Flat;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerAccepter implements Runnable {
    private Stack<Flat> st;

    public ServerAccepter(Stack<Flat> st) {
        this.st = st;
    }

    public void run() {
        try {
            try {
                Selector selector = Selector.open();
                ServerSocketChannel ssChannel1 = ServerSocketChannel.open();

                ssChannel1.configureBlocking(false);

                ssChannel1.socket().bind(new InetSocketAddress(6123));
                HandlerRequest handlerRequest = new HandlerRequest();

                ssChannel1.register(selector, SelectionKey.OP_ACCEPT);


                while (true) {
                    selector.select();

                    Iterator it = selector.selectedKeys().iterator();

                    while (it.hasNext()) {
                        SelectionKey selKey = (SelectionKey) it.next();

                        ServerSocketChannel ssChannel = (ServerSocketChannel) selKey.channel();
                        SocketChannel sChannel = ssChannel1.accept();


                        ByteBuffer bufferRaed = ByteBuffer.allocate(30000);
                        sChannel.read(bufferRaed);
                        bufferRaed.flip();

                        ExecutorService executorService = Executors.newCachedThreadPool();
                        executorService.submit(new CachedThreadPoll(bufferRaed, sChannel, st));

                    }
                }
            } catch (ClosedChannelException e) {

            }
        } catch (IOException e) {
        }
    }
}
