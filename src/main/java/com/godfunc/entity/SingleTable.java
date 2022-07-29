package com.godfunc.entity;

import java.io.Serializable;

public class SingleTable implements Serializable {

    private Integer id;
    private String key1;
    private Integer key2;
    private String key3;

    public SingleTable(Integer id, String key1, Integer key2, String key3) {
        this.id = id;
        this.key1 = key1;
        this.key2 = key2;
        this.key3 = key3;
    }
    public SingleTable(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public Integer getKey2() {
        return key2;
    }

    public void setKey2(Integer key2) {
        this.key2 = key2;
    }

    public String getKey3() {
        return key3;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }
}
