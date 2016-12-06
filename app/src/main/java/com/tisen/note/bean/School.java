package com.tisen.note.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by tisen on 2016/10/30.
 */
@DatabaseTable(tableName = "school")
public class School {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    public School() {
    }

    public School(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "School{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
