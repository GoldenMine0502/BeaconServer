package kr.goldenmine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class RegisterCommand implements ICommand {
    private ServerInfo info;

    public RegisterCommand(ServerInfo info) {
        this.info = info;
    }

    @Override
    public void onConnect(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        String id = ois.readUTF();
        String pw = ois.readUTF();
        String nickname = ois.readUTF();

        if(!info.isContainsId(id)) {
            info.addUser(id, pw, nickname);
            info.saveUsers();
            oos.writeBoolean(true);
        } else {
            oos.writeBoolean(false);
        }
    }
}
