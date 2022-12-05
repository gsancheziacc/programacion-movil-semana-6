package com.example.gabriel_sanchez_s6.providers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.gabriel_sanchez_s6.db.DBHandler;

public class AppProvider extends ContentProvider {

    private DBHandler dbHandler;

    @Override
    public boolean onCreate() {
        dbHandler = new DBHandler(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        Cursor cursor = null;

        switch (TicketContract.URI_MATCHER.match(uri)) {
            case TicketContract.QUERY_ALL_TICKETS:
                cursor = db.query(TicketContract.TABLE_NAME, projection, selection, selectionArgs, null ,null, sortOrder);
                cursor.setNotificationUri(getContext().getContentResolver(), TicketContract.CONTENT_URI);
        }
        
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        //NOT IMPLEMENTED
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        //NOT IMPLEMENTED
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        //NOT IMPLEMENTED
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        //NOT IMPLEMENTED
        return 0;
    }
}
