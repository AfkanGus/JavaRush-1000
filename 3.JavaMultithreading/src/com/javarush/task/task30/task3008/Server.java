package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
    private static class Handler extends Thread{
        private Socket socket;
        public Handler (Socket socket){
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException{
            boolean notOk = true;
            String result = "";
            while (notOk) {
            connection.send(new Message(MessageType.NAME_REQUEST));
            Message answer = connection.receive();
                if (answer.getType()!=MessageType.USER_NAME){
                    continue;
                }
                else if (answer.getType()==MessageType.USER_NAME&&answer.getData()!=null&&answer.getData()!=""){
                    if(!connectionMap.containsKey(answer.getData())){
                        connectionMap.put(answer.getData(),connection);
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        ConsoleHelper.writeMessage(answer.getData() + " принято");
                        notOk = false;
                    }
                }
                result = answer.getData();

            }
            return result;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException{
            for (Map.Entry<String, Connection> entry:connectionMap.entrySet()){
                if (!entry.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException{
            while (true) {
                Message messaga = connection.receive();
                if (messaga.getType() == MessageType.TEXT) {
                    Server.sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + messaga.getData()));
                } else {
                    ConsoleHelper.writeMessage("Ошибочка!");
                }
            }

        }

        @Override
        public void run() {
            try {
                ConsoleHelper.writeMessage("Было установлено соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
                Connection connection = new Connection(socket);
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection,userName);
                serverMainLoop(connection,userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }



    public static void sendBroadcastMessage(Message message){
        for (ConcurrentHashMap.Entry<String,Connection>entry:connectionMap.entrySet()){
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Сообщение не было отправлено");
            }
        }
    }
    public static void main(String[] args) throws IOException {
        System.out.println("Введите порт:");
        int port = ConsoleHelper.readInt();
        ServerSocket ss = new ServerSocket(port);
        try {
                while (true){
                    Socket socket = ss.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            ss.close();
        }
    }
}
