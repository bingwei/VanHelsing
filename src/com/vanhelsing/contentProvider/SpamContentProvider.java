package com.vanhelsing.contentProvider;

import java.util.HashMap;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class SpamContentProvider extends ContentProvider {

	private static final Uri CLASSIFICATION_URI = ClassificationTable.URI;
	public static final Uri CLASSIFICATION_FEATURE_COUNT_URI = Uri.parse(ClassificationFeatureCountTable.URI);

	public static final String AUTHORITY = "com.vanhelsing.contentProvider";


	private final HashMap<Uri, Table> tableMap;

	public SpamContentProvider() {
		tableMap = new HashMap<Uri, Table>();
		tableMap.put(FeatureTable.URI, new FeatureTable());
		tableMap.put(CLASSIFICATION_URI, new ClassificationTable());
		tableMap.put(CLASSIFICATION_FEATURE_COUNT_URI, new ClassificationFeatureCountTable());
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		Log.i("vanhelsing", "Inside SpamContentProvider.insert()");
		long insertedId = tableMap.get(uri).insert(values, getContext());

		return Uri.withAppendedPath(uri, String.valueOf(insertedId));
	}

	@Override
	public boolean onCreate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
		Log.i("vanhelsing", "Inside SpamContentProvider.query()");
		return tableMap.get(uri).query(projection, selection, selectionArgs, sortOrder, getContext());
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		return tableMap.get(uri).update(values, selection, getContext());
	}

}
