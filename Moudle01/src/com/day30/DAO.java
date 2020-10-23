package com.day30;

import java.util.*;

/**
 * @ClassName DAO
 * @Description TODO
 * @Author 李玉龙
 * @Date 2020/8/30 20:52
 * @Version 1.0
 **/
public class DAO<T> {
    private Map<String, T> map = new HashMap<>();

    public void save(String id, T entity){
        this.map.put(id, entity);
    }

    public T get(String id){
        return map.get(id);
    }

    public void update(String id, T entity){
        if(map.containsKey(id)){
            map.put(id, entity);
        }
    }

    public List<T> list(){
        ArrayList<T> ts = new ArrayList<>();
        Collection<T> values = map.values();
        for(T v : values){
            ts.add(v);
        }
        return ts;
    }

    public void delect(String id){
        map.remove(id);
    }
}
