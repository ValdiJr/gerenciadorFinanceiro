package com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.utils;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.smartcase.vfnj_jbsn.gerenciadorfinanceiro.R;

/**
 * Created by Dinho-PC on 21/06/2017.
 */

public class MonthAdapter extends CursorAdapter {


    public static class ViewHolder {
//        public final ImageView iconView;
//        public final TextView dataEntryView;
//        public final TextView descriptionEntryView;
//        public final TextView valueEntryView;
        public final TextView monthView;

        public ViewHolder(View view) {
//            iconView = (ImageView) view.findViewById(R.id.list_item_icon);
//            dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
//            descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
//            valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
              monthView = (TextView) view.findViewById(R.id.list_item_month_textview);
        }
    }
    public MonthAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {


        View view = LayoutInflater.from(context).inflate(R.layout.month_view_entry, parent, false);



        ViewHolder viewHolder = new ViewHolder(view);
        view.setTag(viewHolder);

        return view;

    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        ViewHolder viewHolder = (ViewHolder) view.getTag();

//        //ImageView iconView = (ImageView) view.findViewById(R.id.list_item_icon);
//        viewHolder.iconView.setImageResource(R.drawable.default_icon);
//
//        TextView valueEntryView = (TextView) view.findViewById(R.id.list_item_high_textview);
//        viewHolder.valueEntryView.setText(cursor.getDouble(1)+"");
//
//        TextView dataEntryView = (TextView) view.findViewById(R.id.list_item_detail);
//        viewHolder.dataEntryView.setText(cursor.getString(2));
//
//        TextView descriptionEntryView = (TextView) view.findViewById(R.id.list_item_entryfinance_textview);
//        viewHolder.descriptionEntryView.setText(cursor.getString(3));

 //       TextView categoryEntryView = (TextView) view.findViewById(R.id.list_item_month_textview);

        String ano = (cursor.getString(5)).substring(0,4);
        switch ((cursor.getString(5)).substring(5,7)){
            case "01":
                viewHolder.monthView.setText(ano+" Janeiro");
                break;
            case "02":
                viewHolder.monthView.setText(ano+" Fevereiro");
                break;
            case "03":
                viewHolder.monthView.setText(ano+" Março");
                break;
            case "04":
                viewHolder.monthView.setText(ano+" Abril");
                break;
            case "05":
                viewHolder.monthView.setText(ano+" Maio");
                break;
            case "06":
                viewHolder.monthView.setText(ano+" Junho");
                break;
            case "07":
                viewHolder.monthView.setText(ano+" Julho");
                break;
            default:
                viewHolder.monthView.setText(ano+" Desconhedido");


        }

    }
}
