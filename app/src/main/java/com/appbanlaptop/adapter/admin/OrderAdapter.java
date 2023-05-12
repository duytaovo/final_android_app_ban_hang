package com.appbanlaptop.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.order.DetailOrderActivity;

import com.appbanlaptop.model.Order;
import com.appbanlaptop.model.OrderHistory;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.List;



public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{

    List<OrderHistory> arrayOrder;
    public OrderAdapter(List<OrderHistory> arrayOrder) { this.arrayOrder = arrayOrder; }
    public static int _idBrand;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnLongClickListener {
        Button btXemOrder;
        TextView nameReceive, addressReceive, nameProduct;
        ApiShopLapTop apiShopLapTop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btXemOrder = itemView.findViewById(R.id.btXemOrder_Admin);
            nameReceive = itemView.findViewById(R.id.etOrderName_Admin);
            addressReceive = itemView.findViewById(R.id.etOrderAddressReceive_Admin);
            nameProduct = itemView.findViewById(R.id.etOrderNameProductReceive_Admin);

            //admin
            itemView.setOnCreateContextMenuListener(this);
            apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);

        }

        @Override
        public void onClick(View view) {
//            itemClickListener.onClick(view,getAdapterPosition(),false);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {


        }

        @Override
        public boolean onLongClick(View view) {
//            itemClickListener.onItemClick();
            return false;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        _idBrand = arrayOrder.get(position).getOrder_id();
        holder.nameReceive.setText(arrayOrder.get(position).getName_receive()) ;
        holder.addressReceive.setText(arrayOrder.get(position).getAddress_receive()) ;
        holder.nameProduct.setText(arrayOrder.get(position).getName()) ;

        holder.btXemOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailOrderActivity.class);
                intent.putExtra("id", arrayOrder.get(position).getOrder_id());
                v.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayOrder.size();
    }
}
