package kr.goldenmine;

public class Pair<Key, Value> {
    private Key key;
    private Value value;

    public Pair(Key t, Value t2) {
        this.key = t;
        this.value = t2;
    }

    public Key getKey() {
        return key;
    }

    public Value getValue() {
        return value;
    }

    public void setKey(Key t) {
        this.key = t;
    }

    public void setValue(Value t2) {
        this.value = t2;
    }
}
