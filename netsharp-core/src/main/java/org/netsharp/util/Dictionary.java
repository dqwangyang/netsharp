package org.netsharp.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Dictionary<K,V> implements Map<K,V> {
	
	private ArrayList<K> keys=new ArrayList<K>();
	private ArrayList<V> values=new ArrayList<V>();

	public Dictionary(){}
	
	public Dictionary(Dictionary<K,V> dic){
		for(int i =0;i<dic.size();i++){
			this.put(dic.keys.get(i), dic.values.get(i));
		}
	}
	
	public int size() {
		return this.keys.size();
	}

	
	public boolean isEmpty() {
		return this.keys.size()==0;
	}

	
	public boolean containsKey(Object key) {
		int index=this.keys.indexOf(key);
		return index>=0;
	}

	
	public boolean containsValue(Object value) {

		int index=this.values.indexOf(value);
		return index>=0;
	}

	
	public V get(Object key) {
		int index= this.keys.indexOf(key);
		
		if(index<0){
			return null;
		}
		
		return this.values.get(index);
	}

	
	public V put(K key, V value) {
		
		this.keys.add(key);
		this.values.add(value);
		
		return value;
	}

	
	public V remove(Object key) {
		
		int index= this.keys.indexOf(key);
		
		if(index<0){
			return null;
		}
		
		V value= this.values.get(index);
		
		this.keys.remove(index);
		this.values.remove(index);
		
		return value;
	}

	
	public void putAll(Map<? extends K, ? extends V> m) {
		
	}

	
	public void clear() {
		this.keys.clear();
		this.values.clear();
	}

	
	public Set<K> keySet() {
		LinkedHashSet<K> set = new LinkedHashSet <K>();
		
		for(K k : this.keys){
			set.add(k);
		}
		return set;
	}

	
	public Collection<V> values() {
		return this.values;
	}

	
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}
    
}
