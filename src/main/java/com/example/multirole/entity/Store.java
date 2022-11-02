package com.example.multirole.entity;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "store")
public class Store implements Serializable{

    @Id
    @Column(name = "storeid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "storename")
    private String storename;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", storename='" + storename + '\'' +
                '}';
    }
}
