package com.aad.core.gaad.persistent_data_storage.content_provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by w3E17 on 3/22/2018.
 */

public class MyProvider extends ContentProvider {

    // provider name

    private static final String PROVIDER_NAME = "com.aad.core.gaad.persistent_data_storage.content_provider.MyProvider";

    // Creating a URI

    private static final String CONTENT_URI = "content://"+PROVIDER_NAME+"/employee";

    // Creating Real Provider URI

    private static Uri URI = Uri.parse(CONTENT_URI);

    // Define table component

    private static String COLUMN_ID = "id";
    private static String COLUMN_NAME = "name";
    private static String COLUMN_DESIGNATION = "designation";

    // matcher id

    private static int EMPLOYEE = 1;
    private static int EMPLOYEE_ID = 2;

    // creating a UriMatcher

    private static UriMatcher mUriMatcher;

    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s, @Nullable String[] strings1, @Nullable String s1) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
