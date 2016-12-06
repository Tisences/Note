package com.tisen.note.bean;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.tisen.note.utils.DBHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tisen on 2016/10/30.
 */
public class PersonDao {
    private Context context;
    private Dao<Person, Integer> dao;
    private DBHelper dbHelper;

    @SuppressWarnings("unchecked")
    public PersonDao(Context context) {
        this.context = context;
        try {
            dbHelper = DBHelper.getInstance(context);
            dao = dbHelper.getDao(Person.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void add(Person person) {
        try {
            dao.create(person);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(List<Person> persons) {
        try {
            for (Person one :
                    persons) {
                dao.create(one);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<Person> queryForAll() throws SQLException {
        return dao.queryForAll();
    }

    public Person getPersonWithSchool(int id) {
        Person person = null;
        try {
            person = dao.queryForId(id);
            dbHelper.getDao(School.class).refresh(person.getSchool());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }

    public Dao getDao() {
        return dao;
    }
}
