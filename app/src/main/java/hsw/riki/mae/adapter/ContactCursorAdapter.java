package hsw.riki.mae.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import hsw.riki.mae.databinding.FragmentDatabaseListItemBinding;

public class ContactCursorAdapter extends CursorAdapter {
  public ContactCursorAdapter(Context context) {
    super(context, null, 0);
  }

  @Override
  public View newView(Context context, Cursor cursor, ViewGroup parent) {
    return FragmentDatabaseListItemBinding.inflate(LayoutInflater.from(context)).getRoot();
  }

  @Override
  public void bindView(View view, Context context, Cursor cursor) {
    FragmentDatabaseListItemBinding binding = FragmentDatabaseListItemBinding.bind(view);
    binding.firstname.setText(cursor.getString(1));
    binding.lastname.setText(cursor.getString(2));
  }
}
