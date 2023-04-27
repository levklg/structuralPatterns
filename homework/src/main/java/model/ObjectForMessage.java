package model;

import java.util.ArrayList;
import java.util.List;

public class ObjectForMessage {
    private List<String> data;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public ObjectForMessage copy() {
        ObjectForMessage newObject = new ObjectForMessage();
        if (this.data != null) {
            newObject.setData(new ArrayList<>(this.data));
        }
        return newObject;
    }
}
