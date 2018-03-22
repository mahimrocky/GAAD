package com.aad.core.gaad.persistent_data_storage.content_provider;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.aad.core.gaad.R;

public class ContentProviderTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider_test);
    }

    public void onClickAddName(View view) {
        // Add a new student record
        ContentValues values = new ContentValues();
        values.put(MyProvider.COLUMN_NAME,
                ((EditText) findViewById(R.id.editText2)).getText().toString());

        values.put(MyProvider.COLUMN_DESIGNATION,
                ((EditText) findViewById(R.id.editText3)).getText().toString());

        Uri uri = getContentResolver().insert(
                MyProvider.URI, values);

        Toast.makeText(getBaseContext(),
                uri.toString(), Toast.LENGTH_LONG).show();
    }

    public void onClickRetrieveStudents(View view) {
        // Retrieve student records
        String URL = "content://com.aad.core.gaad.persistent_data_storage.content_provider.MyProvider";

        Uri students = Uri.parse(URL);
        Cursor c = managedQuery(students, null, null, null, "name");

        if (c.moveToFirst()) {
            do {
                Toast.makeText(this,
                        c.getString(c.getColumnIndex(MyProvider.COLUMN_ID)) +
                                ", " + c.getString(c.getColumnIndex(MyProvider.COLUMN_NAME)) +
                                ", " + c.getString(c.getColumnIndex(MyProvider.COLUMN_DESIGNATION)),
                        Toast.LENGTH_SHORT).show();
            } while (c.moveToNext());
        }
    }
}
