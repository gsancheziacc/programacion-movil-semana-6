package com.example.gabriel_sanchez_s6.providers;

import android.content.UriMatcher;
import android.net.Uri;

public class TicketContract {
    public static final String TABLE_NAME = "TICKET";
    public static final int QUERY_ALL_TICKETS = 101;
    public static final String AUTHORITY = "com.example.gabriel_sanchez_s6.providers.AppProvider";
    public static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
    public final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + TABLE_NAME);

    static {
        URI_MATCHER.addURI(AUTHORITY, TicketContract.TABLE_NAME, QUERY_ALL_TICKETS);
    }

    public class Columns {
        public static final String ID = "id";
        public static final String CAR_NUMBER = "car_number";
        public static final String IN_DATETIME = "in_datetime";
        public static final String PLACE_NUMBER = "place_number";
    }
}
