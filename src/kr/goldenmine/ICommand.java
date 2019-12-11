package kr.goldenmine;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public interface ICommand {
    void onConnect(ObjectInputStream ois, ObjectOutputStream oos) throws IOException;
}
