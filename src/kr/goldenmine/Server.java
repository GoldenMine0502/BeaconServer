package kr.goldenmine;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class Server {
    private HashMap<String, ICommand> commands = new HashMap<>();

    private ServerSocket serverSocket;
    private ServerInfo info;

    public Server(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);
        this.info = new ServerInfo("default");

        addCommand("register", new RegisterCommand(info));
        addCommand("stateUpdate", new StateUpdateCommand(info));
        addCommand("userState", new GetAllUserStateCommand(info));
        addCommand("login", new LoginCommand(info));
        addCommand("init", new InitCommand(info));

        info.loadUsers();
    }

    public void start() {
        new SocketThread().start();
    }

    public void addCommand(String key, ICommand command) {
        commands.put(key, command);
    }

    public class SocketThread extends Thread {
        @Override
        public void run() {
            while(!serverSocket.isClosed()) {
                try {
                    Socket socket = serverSocket.accept();
                    System.out.println("새 요청");
                    new SocketOneThread(socket).start();
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public class SocketOneThread extends Thread {

        private Socket socket;

        public SocketOneThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

                System.out.print(".");
                ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
                oos.flush();

                System.out.print(".");
                String key = ois.readUTF();

                System.out.print(".");
                ICommand command = commands.get(key);
                if(command != null) {
                    command.onConnect(ois, oos);
                }

                System.out.println(".완료");

                oos.flush();
                socket.close();
            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
