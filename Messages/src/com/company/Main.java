package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {

        Message message = new Message();
        (new Thread(new Writer(message))).start();
        (new Thread(new Reader(message))).start();
    }
}
class Message{
    private String message;
    private boolean empty = true;
    public synchronized String read(){
        while (empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        empty= true;
        notifyAll();
        return message;

    }
    public  synchronized  void  write(String message){
        while (!empty){
            try {
                wait();
            }catch (InterruptedException e){

            }
        }
        empty =false;
        notifyAll();
        this.message =message;
    }
}

class Writer implements Runnable{
    private Message message;
    public Writer(Message message){
        this.message = message;
    }

    @Override
    public void run() {
        String messages [] = {"HELLO BOY", " Bye Boy", "YOgesh Bot", "Super Goku"};
        Random random = new Random();
        for (String s : messages) {
            message.write(s);
            try {
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {

            }
        }
        message.write("Finished");
    }
}

class Reader implements Runnable{
        private Message message;

    public Reader(Message message) {
        this.message = message;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (String latesMessage = message.read();!latesMessage.equals("Finished");latesMessage = message.read())
        {
            System.out.println(latesMessage);
            try {
                Thread.sleep(random.nextInt(2000));
            }catch (InterruptedException e){

            }
        }
    }
}