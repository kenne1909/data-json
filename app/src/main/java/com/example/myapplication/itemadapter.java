package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class itemadapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<item> itemList;

    public itemadapter(Context context, int layout, List<item> itemList) {
        this.context = context;
        this.layout = layout;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return itemList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTieude,txtMota,txtLoai,txtGia,txtThuonghieu;

        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view==null){
            LayoutInflater inflater= (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view=inflater.inflate(layout,null);

            holder = new ViewHolder();
            //ánh xạ
            holder.txtTieude=view.findViewById(R.id.txttieude);
            holder.txtMota=view.findViewById(R.id.txtmota);
            holder.txtLoai=view.findViewById(R.id.txtloai);
            holder.txtThuonghieu=view.findViewById(R.id.txtthuonghieu);
            holder.txtGia=view.findViewById(R.id.txtgia);
            holder.imgHinh=view.findViewById(R.id.imghinh);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }

        //gasn gia tri

        item Item=itemList.get(i);
        holder.txtTieude.setText(Item.getTitle());
        holder.txtMota.setText(Item.getDescription());
        holder.txtThuonghieu.setText(Item.getBrand());
        holder.txtLoai.setText(Item.getCategory());
        holder.txtGia.setText(Item.getPrice());
        Picasso.get().load(Item.getImages()).into(holder.imgHinh);


        return view;
    }
}
