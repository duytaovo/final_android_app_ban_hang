package com.appbanlaptop.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.brand.AddBrandActivity;
import com.appbanlaptop.activity.admin.product.AdminActivity;
import com.appbanlaptop.activity.admin.user.DetailUserActivity;
import com.appbanlaptop.activity.admin.brand.QuanLyBrandActivity;
import com.appbanlaptop.activity.admin.user.QuanLyUsersActivity;
import com.appbanlaptop.model.BrandModel;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.User;
import com.appbanlaptop.model.UserModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

    List<User> arrayUser;
    private AppCompatActivity mActivity;
    public static int _idBrand;

    ApiShopLapTop apiShopLapTop;
    public UserAdapter(List<User> arrayUser, AppCompatActivity activity) { this.arrayUser = arrayUser;
        mActivity = activity;}
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnLongClickListener {
        Button btShowNameUser,btXemUser,btXoaUser;
        ApiShopLapTop apiShopLapTop;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btShowNameUser = itemView.findViewById(R.id.btNameUser);
            btXemUser = itemView.findViewById(R.id.btXemUser);
            btXoaUser = itemView.findViewById(R.id.btXoaUser);

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
            contextMenu.add(0,0,getAdapterPosition(),"Sửa");
            contextMenu.add(0,1,getAdapterPosition(),"Xoá");

            MenuItem viewItem = contextMenu.findItem(0);
            MenuItem deleteItem = contextMenu.findItem(1);
            // Thêm listener cho mục 1
            viewItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {

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

        @Override
        public boolean onLongClick(View view) {
//            itemClickListener.onItemClick();
            return false;
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent,false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        _idBrand = arrayUser.get(position).getId();
        holder.btShowNameUser.setText(arrayUser.get(position).getFullname()) ;

        holder.btXemUser.setOnClickListener(new View.OnClickListener() {
            int _id = arrayUser.get(position).getId();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailUserActivity.class);
                intent.putExtra("id", _id);
                v.getContext().startActivity(intent);
            }
        });
        holder.btXoaUser.setOnClickListener(new View.OnClickListener() {
            int _id = arrayUser.get(position).getId();
            @Override
            public void onClick(View view) {
                apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
                Call<UserModel> call = apiShopLapTop.deleteUser(_id);
                call.enqueue(new Callback<UserModel>() {
                    @Override
                    public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                        UserModel userModel = response.body();
                        if (userModel.isSuccess()) {
                            Toast.makeText(view.getContext(), "Xoá thành công.", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(view.getContext(), QuanLyUsersActivity.class);
                            view.getContext().startActivity(intent);
                            if (!mActivity.isFinishing()) {
                                mActivity.finish();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<UserModel> call, Throwable t) {
                        call.cancel();
                    }
                });
            }
        });
    }



    @Override
    public int getItemCount() {
        return arrayUser.size();
    }
}
