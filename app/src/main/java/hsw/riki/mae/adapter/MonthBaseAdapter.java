package hsw.riki.mae.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import hsw.riki.mae.databinding.FragmentMenuListItemBinding;
import hsw.riki.mae.enumTypes.Month;

public class MonthBaseAdapter extends BaseAdapter {

  private final Context context;
  private final LayoutInflater inflater;

  public MonthBaseAdapter(Context context) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
  }

  @Override
  public int getCount() {
    return Month.values().length;
  }

  @Override
  public Object getItem(int position) {
    return Month.values()[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    FragmentMenuListItemBinding month;
    if (null == convertView) {
      month = FragmentMenuListItemBinding.inflate(LayoutInflater.from(context));
    } else {
      month = FragmentMenuListItemBinding.bind(convertView);
    }

    /*if (convertView == null) {
      convertView =
          LayoutInflater.from(context).inflate(R.layout.fragment_menu_list_item, parent, false);
    }*/

    Month currentMonth = (Month) getItem(position);

    month.nameTextView.setText(currentMonth.getName());
    month.italianNameTextView.setText(currentMonth.getItalianName());
    month.adjective1TextView.setText(currentMonth.getAdjective1());
    month.adjective2TextView.setText(currentMonth.getAdjective2());
    month.adjective3TextView.setText(currentMonth.getAdjective3());

    /*       (TextView) monthName.setText(currentMonth.getName());
    TextView monthItalianName = (TextView) convertView.findViewById(R.id.italianNameTextView);
    monthItalianName.setText(currentMonth.getItalianName());
    TextView monthAdjective1 = (TextView) convertView.findViewById(R.id.adjective1TextView);
    monthAdjective1.setText(currentMonth.getAdjective1());
    TextView monthAdjective2 = (TextView) convertView.findViewById(R.id.adjective2TextView);
    monthAdjective2.setText(currentMonth.getAdjective2());
    TextView monthAdjective3 = (TextView) convertView.findViewById(R.id.adjective3TextView);
    monthAdjective3.setText(currentMonth.getAdjective3());*/
    return month.getRoot();
  }
}
