package com.appbanlaptop.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.shipper.DetailOrderShipperActivity;
import com.appbanlaptop.model.Order;
import com.appbanlaptop.model.OrderHistory;
import com.appbanlaptop.model.OrderModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OrderAdapterAdminShipper extends RecyclerView.Adapter<OrderAdapterAdminShipper.ViewHolder>{

    List<OrderHistory> arrayOrder;
    ApiShopLapTop apiShopLapTop;
    private AppCompatActivity mActivity;
    public OrderAdapterAdminShipper(List<OrderHistory> arrayOrder, AppCompatActivity activity) { this.arrayOrder = arrayOrder;  mActivity = activity;}
    public static int _idBrand;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnLongClickListener {
        Button btAcceptOrder,btXemOrder;
        TextView nameReceive, addressReceive, nameProduct;
        ApiShopLapTop apiShopLapTop;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btXemOrder = itemView.findViewById(R.id.btXemOrderAdmin);
            btAcceptOrder = itemView.findViewById(R.id.btAcceptOrderAdmin);
            nameReceive = itemView.findViewById(R.id.etOrderNameReceiveAdmin);
            addressReceive = itemView.findViewById(R.id.etOrderAddressReceiveAdmin);
            nameProduct = itemView.findViewById(R.id.etOrderNameProductReceiveAdmin);

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
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_admin_shipper, parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nameReceive.setText(arrayOrder.get(position).getName_receive()) ;
        holder.addressReceive.setText(arrayOrder.get(position).getAddress_receive()) ;
        holder.nameProduct.setText(arrayOrder.get(position).getName()) ;

        holder.btXemOrder.setOnClickListener(new View.OnClickListener() {
            int _id = arrayOrder.get(position).getOrder_id();
            @Override
            public void onClick(View v) {
                System.out.println(_id);
                Intent intent = new Intent(v.getContext(), DetailOrderShipperActivity.class);
                intent.putExtra("id", _id);
                v.getContext().startActivity(intent);
            }
        });
        holder.btAcceptOrder.setOnClickListener(view -> {
            apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
            int _id = arrayOrder.get(position).getOrder_id();
            Call<OrderModel> call = apiShopLapTop.updateStatusDonGiaoChoShipper(_id);
            call.enqueue(new Callback<OrderModel>() {
                @Override
                public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                    OrderModel laptopModel = response.body();
                    if (laptopModel.isSuccess()) {
                        Toast.makeText(view.getContext(), "Đã giao cho đơn vị vận chuyển thành công.", Toast.LENGTH_LONG).show();
//                        Intent intent = new Intent(view.getContext(), DonDaXacNhanActivity.class);
//                        view.getContext().startActivity(intent);
//                        if (!mActivity.isFinishing()) {
//                            mActivity.finish();
//                        }
//                        System.out.println("nhận đơn thành công");
//
                    }

                }
                @Override
                public void onFailure(Call<OrderModel> call, Throwable t) {
                    call.cancel();
                    Toast.makeText(view.getContext(), "thất bại.", Toast.LENGTH_LONG).show();

                }

            });
        });
    }



    @Override
    public int getItemCount() {
        return arrayOrder.size();
    }
}
