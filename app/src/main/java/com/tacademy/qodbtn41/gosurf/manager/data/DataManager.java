package com.tacademy.qodbtn41.gosurf.manager.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import com.tacademy.qodbtn41.gosurf.manager.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dongja94 on 2015-10-21.
 */
public class DataManager extends SQLiteOpenHelper {
    private static final String DB_NAME = "spot";
    private static final int DB_VERSION = 1;

    private static DataManager instance;
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    private DataManager() {
        super(MyApplication.getContext(), DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE "+ SpotDB.AddessTable.TABLE_NAME+"(" +
                SpotDB.AddessTable._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SpotDB.AddessTable.COLUMN_NAME+" TEXT NOT NULL," +
                SpotDB.AddessTable.COLUMN_WIND_DIR+" INTEGER NOT NULL," +
                SpotDB.AddessTable.COLUMN_WAVE_DIR+" INTEGER NOT NULL,";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void add(SpotItem item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.clear();
        values.put(SpotDB.AddessTable.COLUMN_NAME, item.name);
        values.put(SpotDB.AddessTable.COLUMN_WIND_DIR, item.wave_dir);
        values.put(SpotDB.AddessTable.COLUMN_WAVE_DIR, item.wind_dir);

        item._id = db.insert(SpotDB.AddessTable.TABLE_NAME, null, values);
    }

    public void update(SpotItem item) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.clear();
        values.put(SpotDB.AddessTable.COLUMN_NAME,item.name);
        values.put(SpotDB.AddessTable.COLUMN_WIND_DIR, item.wind_dir);
        values.put(SpotDB.AddessTable.COLUMN_WAVE_DIR, item.wave_dir);

        String selection = SpotDB.AddessTable._ID + " = ?";
        String[] args = new String[] {"" + item._id};
        db.update(SpotDB.AddessTable.TABLE_NAME,values, selection, args);

    }

    public List<SpotItem> getAddressList(String keyword) {
        List<SpotItem> list = new ArrayList<SpotItem>();
        Cursor c = getAddressCursor(keyword);
        while(c.moveToNext()) {
            SpotItem item = new SpotItem();
            item._id = c.getLong(c.getColumnIndex(SpotDB.AddessTable._ID));
            item.name = c.getString(c.getColumnIndex(SpotDB.AddessTable.COLUMN_NAME));
            item.wind_dir = c.getString(c.getColumnIndex(SpotDB.AddessTable.COLUMN_WIND_DIR));
            item.wave_dir = c.getString(c.getColumnIndex(SpotDB.AddessTable.COLUMN_WAVE_DIR));
            list.add(item);
        }
        c.close();
        return list;
    }

    public Cursor getAddressCursor(String keyword) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {SpotDB.AddessTable._ID,
                SpotDB.AddessTable.COLUMN_NAME,
                SpotDB.AddessTable.COLUMN_WIND_DIR,
                SpotDB.AddessTable.COLUMN_WAVE_DIR};
        String selection = null;
        String[] args = null;
        if (!TextUtils.isEmpty(keyword)) {
            selection = SpotDB.AddessTable.COLUMN_NAME + " LIKE ? OR "+
                    SpotDB.AddessTable.COLUMN_WIND_DIR+" LIKE ? OR "+
                    SpotDB.AddessTable.COLUMN_WAVE_DIR+" LIKE ?";
            args = new String[] {"%" + keyword + "%","%" + keyword + "%","%" + keyword + "%","%" + keyword + "%"};
        }
        String orderBy = SpotDB.AddessTable.COLUMN_NAME+" COLLATE LOCALIZED ASC";
        Cursor c = db.query(SpotDB.AddessTable.TABLE_NAME, columns, selection, args, null, null, orderBy);
        return c;
    }
}
