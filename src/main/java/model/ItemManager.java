package model;

public interface ItemManager {
    void add(MenuItem item);
    void remove(String ID);
    void remove(int index);
    String getID();
}
