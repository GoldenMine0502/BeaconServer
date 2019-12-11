package kr.goldenmine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class InitCommand implements ICommand{
    private ServerInfo info;

    public InitCommand(ServerInfo info) {
        this.info = info;
    }
    @Override
    public void onConnect(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        info.initUsers();
        oos.writeBoolean(true);
    }
}
