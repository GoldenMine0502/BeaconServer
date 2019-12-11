package kr.goldenmine;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class ServerInfo {
    private HashMap<String, Pair<String, String>> users = new HashMap<>();
    private HashMap<String, String> connects = new HashMap<>();

    private HashMap<String, String> uuidToPos = new HashMap<>();

    {
        uuidToPos.put("89C88CCF-0FEA-44FB-AB10-4C4FC9248B7B", "장소1");
        uuidToPos.put("89C88CCF-0FEA-44FB-AB10-4C4FC9248B7C", "장소2");
    }

    private String key;

    public ServerInfo(String key) {
        this.key = key;
    }

    public HashMap<String, Pair<String, String>> getUsers() {
        return users;
    }

    public HashMap<String, String> getConnects() {
        return connects;
    }

    public void updateState(String id, String uuid) {
        connects.put(id, uuid);
    }

    public void addUser(String id, String pw, String nickName) {
        users.put(id, new Pair<>(pw, nickName));
    }

    public boolean isContainsId(String id) {
        return users.containsKey(id);
    }

    public void saveUsers() throws IOException {
        File file = new File(key + ".txt");
        if(!file.exists())
            file.createNewFile();

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        int count = 0;

        for(String id : users.keySet()) {
            Pair<String, String> pw = users.get(id);
            writer.append(id);
            writer.append(", ");
            writer.append(pw.getKey());
            writer.append(", ");
            writer.append(pw.getValue());

            count++;
            if(count < users.size()) {
                writer.newLine();
            }
        }

        writer.flush();
        writer.close();
    }

    public boolean login(String id, String pw) {
        return users.containsKey(id) && users.get(id).getKey().equals(pw);
    }

    public void loadUsers() throws IOException {
        File file = new File(key + ".txt");
        if(file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String str;
            while((str = reader.readLine()) != null) {
                String[] split = str.split(", ");
                addUser(split[0], split[1], split[2]);
            }
        }
    }

    public String getRoom(String s) {
        return uuidToPos.get(s);
    }

    public void initUsers() {
        connects.clear();
    }
}
