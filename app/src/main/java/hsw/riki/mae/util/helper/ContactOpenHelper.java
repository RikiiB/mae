package hsw.riki.mae.util.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

public class ContactOpenHelper extends SQLiteOpenHelper {
  public ContactOpenHelper(Context context) {
    super(context, "ContactDB", null, 1);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    db.execSQL(
        "CREATE TABLE contacts (_id INTEGER PRIMARY KEY, firstname CHAR(255), lastname CHAR(255))");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.d(getClass().getSimpleName(), "Upgrades werden nicht unterstützt.");
  }

  public void insert(String firstname, String lastname) {
    ContentValues values = new ContentValues();
    values.put("firstname", String.valueOf(firstname));
    values.put("lastname", String.valueOf(lastname));
    getWritableDatabase().insert("contacts", null, values);
  }

  public int count() {
    Cursor cursor =
        getReadableDatabase()
            .query("contacts", new String[] {"COUNT(_id)"}, null, null, null, null, null);

    /*Map<String, Integer> result = new HashMap<>();
    while (cursor.moveToNext()) {
      result.put(cursor.getString(1), cursor.getInt(0));
    }*/
    cursor.moveToNext();
    int sum = cursor.getInt(0);
    cursor.close();
    return sum;
  }

  public Map<String, Integer> select() {
    Cursor cursor =
        getReadableDatabase()
            .query(
                "contacts",
                new String[] {"COUNT(_id), firstname, lastname"},
                null,
                null,
                null,
                null,
                null);

    Map<String, Integer> result = new HashMap<>();
    while (cursor.moveToNext()) {
      result.put(cursor.getString(1), cursor.getInt(0));
    }
    cursor.close();
    return result;
  }

  public Cursor selectCursor() {
    return getReadableDatabase().query("contacts", null, null, null, null, null, null);
  }
}
