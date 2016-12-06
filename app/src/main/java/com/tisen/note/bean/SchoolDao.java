package com.tisen.note.bean;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.tisen.note.utils.DBHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by tisen on 2016/10/30.
 */
public class SchoolDao {
    private Context context;
    private Dao<School, Integer> schoolDao;
    private DBHelper dbHelper;


    public SchoolDao(Context context) {
        this.context = context;
        try {
            dbHelper = DBHelper.getInstance(context);
            schoolDao = dbHelper.getDao(School.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void add(School school) {
        try {
            schoolDao.create(school);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public School get(int id) throws SQLException {
        return schoolDao.queryForId(id);
    }

    public List<School> queryForAll() throws SQLException {
        return schoolDao.queryForAll();
    }
}
