package kr.goldenmine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GetAllUserStateCommand implements ICommand {
    private ServerInfo info;

    public GetAllUserStateCommand(ServerInfo info) {
        this.info = info;
    }

    @Override
    public void onConnect(ObjectInputStream ois, ObjectOutputStream oos) throws IOException {
        oos.writeInt(info.getConnects().size());

        for(String id : info.getConnects().keySet()) {
            Pair<String, String> data = info.getUsers().get(id);
            String room = info.getRoom(info.getConnects().get(id));

            oos.writeUTF(data.getValue());
            oos.writeUTF(room);
        }
    }
}
