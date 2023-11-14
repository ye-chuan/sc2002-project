package entity;
import java.util.*;

public class Database<T extends Identifiable> {

	protected String filePath;
	protected Map<String, T> items;

    public Database() {
        this.items = new HashMap<String, T>();
    }

	public void add(T item) {
        if (item.getID() == null) {
            throw new IllegalArgumentException("Item do not have ID");
        }
        if (items.containsKey(item.getID())) {
            throw new IllegalArgumentException("ID not unique in Database");
        }

        items.put(item.getID(), item);
	}

    public T getItem(String id) {
        return items.get(id);
    }

	public T remove(String id) {
        return items.remove(id);
	}

    public T remove(T item) {
        return remove(item.getID());
    }

}
