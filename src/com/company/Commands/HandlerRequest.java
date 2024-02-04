package com.company.Commands;


import com.company.data.Flat;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

public class HandlerRequest {
    private int count = 0;
    private String command;
    public ByteBuffer handelr(Stack<Flat> st, ByteBuffer bufferRead) throws IOException, ClassNotFoundException {
        try {

            ICommand request = (ICommand) new ObjectInputStream(new ByteArrayInputStream(bufferRead.array())).readObject();
            System.out.println(request);

            command = request.execute(st);

            byte[] bs = command.getBytes(StandardCharsets.UTF_8);
            ByteBuffer bufferWriter = ByteBuffer.wrap(bs);
            return bufferWriter;
        } catch (StreamCorruptedException e) {
            return bufferRead;
        }
    }
}
