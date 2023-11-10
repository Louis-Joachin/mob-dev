package com.example.pokemongpt.user;

import java.util.ArrayList;
import java.util.HashMap;

public class Inventory {
    private HashMap<Item,Integer> items = new HashMap<Item,Integer>();

    public Inventory(){
    }

    public void addItem(Item itemAdd){
        for (Item item : this.items.keySet()){
            if(item.equals(itemAdd)){
                int quantity = this.items.get(item);
                this.items.remove(item);
                this.items.put(itemAdd,quantity+1);
                return;
            }
        }
        this.items.put(itemAdd,1);
    }

    public void removeOneItem(Item itemAdd){
        for (Item item : this.items.keySet()){
            if(item.equals(itemAdd)){
                int quantity = this.items.get(item);
                this.items.remove(item);
                if (quantity != 1) {
                    this.items.put(itemAdd, quantity + 1);
                }
            }
        }
    }
}
