package com.appbanlaptop.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appbanlaptop.R;

import com.appbanlaptop.activity.admin.brand.AddBrandActivity;
import com.appbanlaptop.model.Brand;

import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

public class BrandAdapter extends RecyclerView.Adapter<BrandAdapter.ViewHolder> {
    List<Brand> arrayBrand;
    public static int _idBrand;
    public static ApiShopLapTop apiShopLapTop;

    public static MenuItem deleteItem;
    public BrandAdapter(List<Brand> arrayBrand) {
        this.arrayBrand = arrayBrand;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        Button btSuaBrand;
        ImageView item_brand_image2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            item_brand_image2 = itemView.findViewById(R.id.item_brand_image2);
            btSuaBrand = itemView.findViewById(R.id.btSuaBrand);
            itemView.setOnCreateContextMenuListener(this);
            apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(0,0,getAdapterPosition(),"Sửa");
            contextMenu.add(0,1,getAdapterPosition(),"Xoá");
            MenuItem editItem = contextMenu.findItem(0);
            deleteItem = contextMenu.findItem(1);
            // Thêm listener cho mục 1
            editItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent intent = new Intent(view.getContext(), AddBrandActivity.class);
                    view.getContext().startActivity(intent);
                    return true;
                }
            });
            deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

                    return true;
                }
            });
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand_admin, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.itemView.getContext()).load(arrayBrand.get(position).getImage_url()).into(holder.item_brand_image2);
        View view = holder.itemView;
        _idBrand = arrayBrand.get(position).getId();

        holder.btSuaBrand.setOnClickListener(new View.OnClickListener() {
            int _id = arrayBrand.get(position).getId();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddBrandActivity.class);
                intent.putExtra("id", _id);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayBrand.size();
    }

}
