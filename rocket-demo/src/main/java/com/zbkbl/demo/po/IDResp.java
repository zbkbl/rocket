package com.zbkbl.demo.po;

import java.util.Set;

/**
 * @author liuyang
 * @description
 * @date 2020/05/19 10:16
 **/

public class IDResp<K> {

    private Set<K> idResp;

    public IDResp() {
    }

    public IDResp(Set<K> idResp) {
        this.idResp = idResp;
    }

    public Set<K> getIdResp() {
        return idResp;
    }

    public void setIdResp(Set<K> idResp) {
        this.idResp = idResp;
    }
}
