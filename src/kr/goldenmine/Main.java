package kr.goldenmine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server(12344);
        server.start();
    }
}
