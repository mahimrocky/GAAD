package com.aad.core.gaad.persistent_data_storage.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by w3E17 on 3/22/2018.
 */

public class MyProvider extends ContentProvider {

    // provider name

    private static final String PROVIDER_NAME = "com.aad.core.gaad.persistent_data_storage.content_provider.MyProvider";

    // Creating a URI

    private static final String CONTENT_URI = "content://" + PROVIDER_NAME + "/employee";

    // Creating Real Provider URI

    static Uri URI = Uri.parse(CONTENT_URI);

    //Projection map

    private static HashMap<String, String> EMPLOYEE_PROJECTION_MAP;

    // Define table component

    static String COLUMN_ID = "id";
    static String COLUMN_NAME = "name";
    static String COLUMN_DESIGNATION = "designation";

    // matcher id

    private static final int EMPLOYEE = 1;
    private static final int EMPLOYEE_ID = 2;

    // creating a UriMatcher

    private static UriMatcher mUriMatcher;

    // static method for uri matcher

    static {
        mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        mUriMatcher.addURI(PROVIDER_NAME, "employee", EMPLOYEE);
        mUriMatcher.addURI(PROVIDER_NAME, "employee/#", EMPLOYEE_ID);
    }

    // sqlite database

    private SQLiteDatabase database;

    // database name

    private static final String DB_NAME = "employee_entry";

    //table name

    private static final String TABLE_NAME = "employee";

    // version

    private static final int DB_VERSION = 1;

    // Query of creat a table

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME + " TEXT NOT NULL, " +
            COLUMN_DESIGNATION + " TEXT NOT NULL)";


    /*
    *
    * Create a database helper class to create a database
    *
    * It will inherit SqliteOpenHelper class
    * */

    private static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(sqLiteDatabase);
        }
    }


    @Override
    public boolean onCreate() {
        // Database helper object initiation

        Context context = getContext();

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
        return (database == null) ? false : true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(TABLE_NAME);

        switch (mUriMatcher.match(uri)) {
            case EMPLOYEE:
                queryBuilder.setProjectionMap(EMPLOYEE_PROJECTION_MAP);
                break;
            case EMPLOYEE_ID:
                queryBuilder.appendWhere(COLUMN_ID + "=" + uri.getPathSegments().get(1));
                break;

        }

        if (sortOrder == null || sortOrder == "") {
            /*
              By default sort on student names
             */
            sortOrder = COLUMN_NAME;
        }
        Cursor cursor = queryBuilder.query(database, projection, selection,
                selectionArgs, null, null, sortOrder);
        /**
         * register to watch a content URI for changes
         */
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (mUriMatcher.match(uri)) {
            /**
             * Get all student records
             */
            case EMPLOYEE:
                return "vnd.android.cursor.dir/vnd.example.students";
            /**
             * Get a particular student
             */
            case EMPLOYEE_ID:
                return "vnd.android.cursor.item/vnd.example.students";
            default:
                throw new IllegalArgumentException("Unsupported URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        /**
         * Add a new student record
         */
        long rowID = database.insert(TABLE_NAME, "", contentValues);

        /**
         * If record is added successfully
         */
        if (rowID > 0) {
            Uri _uri = ContentUris.withAppendedId(URI, rowID);
            getContext().getContentResolver().notifyChange(_uri, null);
            return _uri;
        }

        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case EMPLOYEE:
                count = database.delete(TABLE_NAME, selection, selectionArgs);
                break;

            case EMPLOYEE_ID:
                String id = uri.getPathSegments().get(1);
                count = database.delete(TABLE_NAME, COLUMN_ID + " = " + id +
                        (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String selection, @Nullable String[] selectionArgs) {
        int count = 0;
        switch (mUriMatcher.match(uri)) {
            case EMPLOYEE:
                count = database.update(TABLE_NAME, contentValues, selection, selectionArgs);
                break;

            case EMPLOYEE_ID:
                count = database.update(TABLE_NAME, contentValues,
                        COLUMN_ID + " = " + uri.getPathSegments().get(1) +
                                (!TextUtils.isEmpty(selection) ? " AND (" + selection + ')' : ""), selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
}
