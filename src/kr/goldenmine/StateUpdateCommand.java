package kr.goldenmine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class StateUpdateCommand implements ICommand {
    private ServerInfo info;

    public StateUpdateCommand(ServerInfo info) {
        this.info = info;
    }

    @Override
    public void onConnect(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        String id = ois.readUTF();
        String pw = ois.readUTF();
        String uuid = ois.readUTF();

        if(info.login(id, pw)) {
            info.updateState(id, uuid);
        }
    }
}
