package com.javarush.task.task37.task3707;

import java.io.*;
import java.util.*;

public class AmigoSet <E> extends AbstractSet implements Serializable, Cloneable, Set {

    private static final Object PRESENT = new Object();

    private transient HashMap<E, Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<E, Object>(Math.max(Math.round((int) (collection.size() / .75f)) + 1, 16));
        for (E e:collection){
            map.put(e, PRESENT);
        }
    }

    @Override
    public Iterator<E> iterator(){
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean add(Object e) {
        if (!map.containsKey((E)e)){
            map.put((E)e,PRESENT);
            return true;
        }
        else return false;
    }

    @Override
    public boolean isEmpty(){
        return map.isEmpty();
    }

    @Override
    public boolean contains (Object o){
        return map.containsKey(o);
    }

    @Override
    public void clear(){
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return map.remove(o)==PRESENT;
    }

    @Override
    public Object clone() {
        try {
            AmigoSet<E> copy = (AmigoSet<E>) super.clone();
            copy.map = (HashMap<E, Object>) map.clone();
            return copy;
        }
        catch (Exception e) {
            throw new InternalError();
        }
    }

    private void writeObject (ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
        oos.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        oos.writeInt(map.size());
        for (E e : map.keySet()) {
            oos.writeObject(e);
        }
        oos.flush();
    }

    private void readObject(ObjectInputStream ois) throws Exception {
        ois.defaultReadObject();
        int capacity = ois.readInt();
        float loadFactor = ois.readFloat();
        int size = ois.readInt();
        map = new HashMap<>(capacity, loadFactor);
        for (int i = 0; i < size; i++) {
            E e = (E) ois.readObject();
            map.put(e, PRESENT);
        }
    }


}
