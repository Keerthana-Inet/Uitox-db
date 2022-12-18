package com.uitoux.uitoux_interview_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Class that wraps the most common database operations. This example assumes you want a single table and data entity
 * with two properties: a title and a priority as an integer. Modify in all relevant locations if you need other/more
 * properties for your data and/or additional tables.
 */
public class SimpleDatabaseHelper {
    private final SQLiteOpenHelper _openHelper;
    private static final String dbName = "classroom.db";
    private final String tableName = "Students";
    private final String sno="sno";
    private final String titleKey = "title";
    private final String mark1 = "mark1";
    private final String mark2 = "mark2";
    private final String mark3 = "mark3";
    private final String total = "total";
    private final String priorityKey = "priority";

    /**
     * Construct a new database helper object
     * @param context The current context for the application or activity
     */
    public SimpleDatabaseHelper(Context context) {
        _openHelper = new SimpleSQLiteOpenHelper(context);
    }

    /**
     * This is an internal class that handles the creation of all database tables
     */
   static class SimpleSQLiteOpenHelper extends SQLiteOpenHelper {
        SimpleSQLiteOpenHelper(Context context) {
            super(context, dbName, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table Students (_id integer primary key autoincrement, sno text, title text, priority integer, mark1 text, mark2 text, mark3 text, total text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }

    /**
     * Return a cursor object with all rows in the table.
     * @return A cursor suitable for use in a SimpleCursorAdapter
     */
    public ArrayList<DbModel> getAll() {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        Cursor cursorCourses = db.rawQuery("select * from Students order by priority, sno, title, mark1, mark2, mark3, total", null);
        // on below line we are creating a new array list.
        ArrayList<DbModel> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                System.out.println("id:: "+cursorCourses.getString(0));
                System.out.println("sno:: "+cursorCourses.getString(1));
                System.out.println("title:: "+cursorCourses.getString(2));
                System.out.println("priority:: "+cursorCourses.getInt(3));
                System.out.println("mark1:: "+cursorCourses.getString(4));
                System.out.println("mark2:: "+cursorCourses.getString(5));
                System.out.println("mark3:: "+cursorCourses.getString(6));
                System.out.println("total:: "+cursorCourses.getInt(7));
                courseModalArrayList.add(new DbModel(
                        cursorCourses.getString(0),
                        cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getInt(3),
                        cursorCourses.getString(4),
                        cursorCourses.getString(5),
                        cursorCourses.getString(6),
                        cursorCourses.getInt(7)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    /**
     * Return values for a single row with the specified id
     * @param id The unique id for the row o fetch
     * @return All column values are stored as properties in the ContentValues object
     */
    public ContentValues get(long id) {
        SQLiteDatabase db = _openHelper.getReadableDatabase();
        if (db == null) {
            return null;
        }
        ContentValues row = new ContentValues();
        Cursor cur = db.rawQuery("select title, priority from Students where _id = ?", new String[] { String.valueOf(id) });
        if (cur.moveToNext()) {
            row.put(sno, cur.getString(0));
            row.put(titleKey, cur.getString(1));
            row.put(priorityKey, cur.getInt(2));
            row.put(mark1, cur.getInt(3));
            row.put(mark2, cur.getInt(4));
            row.put(mark3, cur.getInt(5));
            row.put(total, cur.getInt(6));
        }
        cur.close();
        db.close();
        return row;
    }

    /**
     * Add a new row to the database table
     * @param title The title value for the new row
     * @param priority The priority value for the new row
     */
    public void add(String s_no,String title, int priority,String mark_1,String mark_2,String mark_3) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues row = new ContentValues();
        row.put(sno, s_no);
        row.put(titleKey, title);
        row.put(priorityKey, priority);
        row.put(mark1,mark_1 );
        row.put(mark2,mark_2);
        row.put(mark3,mark_3);
        long id = db.insert(tableName, null, row);
        System.out.println("db.insert:: "+ id);
        db.close();
    }
    /**
     * Delete the specified row from the database table. For simplicity reasons, nothing happens if
     * this operation fails.
     * @param id The unique id for the row to delete
     */
    public void delete(long id) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        db.delete(tableName, "_id = ?", new String[] { String.valueOf(id) });
        db.close();
    }

    /**
     * Updates a row in the database table with new column values, without changing the unique id of the row.
     * For simplicity reasons, nothing happens if this operation fails.
     * @param id The unique id of the row to update
     * @param title The new title value
     * @param priority The new priority value
     */
    public void update(String s_no,long id, String title, int priority,String mark1,String mark2,String mark_3) {
        SQLiteDatabase db = _openHelper.getWritableDatabase();
        if (db == null) {
            return;
        }
        ContentValues row = new ContentValues();
        row.put(sno, s_no);
        row.put(titleKey, title);
        row.put(priorityKey, priority);
        row.put(mark1, mark1);
        row.put(mark2, mark2);
        row.put(mark3, mark_3);
        db.update(tableName, row, "_id = ?", new String[] { String.valueOf(id) } );
        db.close();
    }
}
