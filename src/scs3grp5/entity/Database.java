package scs3grp5.entity;
import java.io.Serializable;
import java.util.*;

/**
 * A generic Database that contains items that has a unique key, this will be
 * it's primary key for accessing the database by ID.
 * @author Lee Ye Chuan
 * @version 1.0
 * @since 2023-11-26
 */
public class Database<T extends Identifiable> implements Serializable {

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

    public void addMany(Collection<T> items) {
        for (T item : items) {
            add(item);
        }
    }

    /** 
     * @return A Collection view of the items in the database
     */
    public Collection<T> getAll() {
        return items.values();
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
