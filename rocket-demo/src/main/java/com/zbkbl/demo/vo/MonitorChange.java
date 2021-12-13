package com.zbkbl.demo.vo;

import java.util.Arrays;

/**
 * @Title: MonitorChange
 * @Description:
 * @author: LiuYang
 * @date: 2021/6/7 8:33 下午
 */
public class MonitorChange {
    private String dataBase;

    private String table;

    private String[] fields;

    public String getDataBase() {
        return dataBase;
    }

    public void setDataBase(String dataBase) {
        this.dataBase = dataBase;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String[] getFields() {
        return fields;
    }

    public void setFields(String[] fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return "MonitorChange{" +
                "dataBase='" + dataBase + '\'' +
                ", table='" + table + '\'' +
                ", fields=" + Arrays.toString(fields) +
                '}';
    }
}