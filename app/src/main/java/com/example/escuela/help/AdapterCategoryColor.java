package com.example.escuela.help;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.escuela.R;
import com.example.escuela.database.myDbAdapter;

import java.util.ArrayList;

/**
 * Created by alex on 14/12/2017.
 */

public class AdapterCategoryColor extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<CategoryColor> items;
    myDbAdapter helper;

    public AdapterCategoryColor(Activity activity, ArrayList<CategoryColor> items) {
        this.activity = activity;
        this.items = items;
        this.helper=new myDbAdapter(activity.getApplicationContext());
    }

    public int getCount() {
        return this.items.size();
    }

    public void clear() {
        this.items.clear();
    }

    public void addAll(ArrayList<CategoryColor> category) {
        for (int i = 0; i < category.size(); i++) {
            this.items.add(category.get(i));
        }
    }
    public CategoryColor getItemCategory(int arg0) {
        return this.items.get(arg0);
    }
    public Object getItem(int arg0) {
        return this.items.get(arg0);
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_list2, null);
        }
        CategoryColor dir = (CategoryColor) this.items.get(position);
        ((TextView) v.findViewById(R.id.gmailitem_title)).setText(dir.getTitle());
        ((TextView) v.findViewById(R.id.list_desc)).setText(dir.getDesc());
        ((RelativeLayout) v.findViewById(R.id.gmailtem_container)).setBackgroundColor(dir.getColor());
        ImageButton imgBtn=(ImageButton) v.findViewById(R.id.imgBtnDelete2);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View parent=(View)v.getParent();
                ListView lista=(ListView) parent.getParent();
                final int position=lista.getPositionForView(parent);
                AdapterCategoryColor adapadorc=(AdapterCategoryColor) lista.getAdapter();
                CategoryColor objCategory=new CategoryColor();
                objCategory=adapadorc.getItemCategory(position);

                //Log.d("Test",String.valueOf(objCategory.getCategoryId()));
                createDialog(objCategory.getCategoryId(),objCategory.getTitle(),v);

            }
        });
        return v;
    }

    public void createDialog(final String idU, String title, final View v)
    {
        Toast.makeText(activity, idU, Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder=new AlertDialog.Builder(v.getContext());

        builder.setMessage("¿Desea eliminar la materia "+title+"?").setTitle(R.string.eliminarMateria);

        builder.setPositiveButton(R.string.aceptar,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                helper.deleteMateria(idU);
                Activity actividad=((Activity)v.getContext());
                actividad.finish();
                actividad.overridePendingTransition(0, 0);
                actividad.startActivity(actividad.getIntent());
                actividad.overridePendingTransition(0, 0);
            }
        });
        builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog=builder.create();

        dialog.show();
    }
}
