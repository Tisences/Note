package com.tisen.note.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.tisen.note.bean.Person;
import com.tisen.note.bean.School;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tisen on 2016/10/30.
 */
public class DBHelper extends OrmLiteSqliteOpenHelper {

    private final static int DATABASE_VERSION = 1;
    private final static String DATABASE_NAME = "orm";

    private Map<String, Dao> daos = new HashMap<String, Dao>();

    private static DBHelper dbHelper;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            synchronized ((DBHelper.class)) {
                if (dbHelper == null)
                    dbHelper = new DBHelper(context);
            }
        }
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTableIfNotExists(connectionSource, Person.class);
            TableUtils.createTableIfNotExists(connectionSource, School.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public synchronized Dao getDao(Class clazz) throws SQLException {
        Dao dao = null;
        String className = clazz.getSimpleName();

        if (daos.containsKey(className)) {
            dao = daos.get(className);
        }
        if (dao == null) {
            dao = super.getDao(clazz);
            daos.put(className, dao);
        }
        return dao;
    }

    @Override
    public void close() {
        super.close();
    }
}
