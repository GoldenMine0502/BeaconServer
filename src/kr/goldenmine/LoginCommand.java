package kr.goldenmine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LoginCommand implements ICommand{
    private ServerInfo info;

    public LoginCommand(ServerInfo info) {
        this.info = info;
    }
    @Override
    public void onConnect(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        String id = ois.readUTF();
        String pw = ois.readUTF();

        oos.writeBoolean(info.login(id, pw));
    }
}
