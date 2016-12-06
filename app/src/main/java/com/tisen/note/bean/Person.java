package com.tisen.note.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by tisen on 2016/10/30.
 */
@DatabaseTable(tableName = "person")
public class Person {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "name")
    private String name;

    @DatabaseField(columnName = "age")
    private int age;

    @DatabaseField(canBeNull = true,foreign = true,columnName = "school_id",foreignAutoRefresh = true)
    private School school;

    public School getSchool() {
        return school;
    }

    public Person() {
    }

    public Person(String name, int age, School school) {
        this.name = name;
        this.age = age;
        this.school = school;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school.toString() +
                '}';
    }

}
