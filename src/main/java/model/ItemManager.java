package model;

public interface ItemManager {
    void add(MenuItem item);
    void remove(String id);
    void remove(int index);
    String getID();
}
