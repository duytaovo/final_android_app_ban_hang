package com.appbanlaptop.adapter.admin;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.appbanlaptop.R;
import com.appbanlaptop.activity.admin.product.AddSPActivity;
import com.appbanlaptop.activity.admin.product.AdminActivity;
import com.appbanlaptop.activity.admin.product.DetailProductAdminActivity;
import com.appbanlaptop.model.Brand;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;
import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.ViewHolder> {

    List<Brand> arrayBrand;
    List<Laptop> arrayLaptop,arrayLaptopGaming;
    ApiShopLapTop apiShopLapTop;
    private AppCompatActivity mActivity;

    public LaptopAdapter(List<Laptop> arrayLaptop,AppCompatActivity activity) {
        this.arrayLaptop = arrayLaptop;
        mActivity = activity;}

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, View.OnLongClickListener {
        ImageView imageLaptop;

        TextView tvLaptopName, tvLaptopRam, tvLaptopRom, tvLaptopPrice, tvLaptopDescPercent, tvLaptopSalePrice;
        TextView tvLaptopScreen, tvLaptopCpu, tvLaptopCard, tvLaptopPin, tvLaptopWeight;
        Button btnShowDetailLaptop,btnSuaDetailLaptop,btnXoaLaptop;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageLaptop = itemView.findViewById(R.id.imageLaptopAdmin);
            tvLaptopName = itemView.findViewById(R.id.tvLaptopName);
            tvLaptopRam = itemView.findViewById(R.id.tvLaptopRam);
            tvLaptopRom = itemView.findViewById(R.id.tvLaptopRom);
            tvLaptopPrice = itemView.findViewById(R.id.tvLaptopPrice);
            tvLaptopDescPercent = itemView.findViewById(R.id.tvLaptopDescPercent);
            tvLaptopSalePrice = itemView.findViewById(R.id.tvLaptopSalePrice);
            tvLaptopScreen = itemView.findViewById(R.id.tvLaptopScreen);
            tvLaptopCpu = itemView.findViewById(R.id.tvLaptopCpu);
            tvLaptopCard = itemView.findViewById(R.id.tvLaptopCard);
            tvLaptopPin = itemView.findViewById(R.id.tvLaptopPin);
            tvLaptopWeight = itemView.findViewById(R.id.tvLaptopWeight);
            btnShowDetailLaptop = itemView.findViewById(R.id.btnShowDetailLaptop);
            btnSuaDetailLaptop = itemView.findViewById(R.id.btnSuaDetailLaptop);
            btnXoaLaptop = itemView.findViewById(R.id.btnXoaDetailLaptop);

            //admin
            itemView.setOnClickListener((this));
            itemView.setOnCreateContextMenuListener(this);
            itemView.setOnLongClickListener(this);

        }

        @Override
        public void onClick(View view) {
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            contextMenu.add(0,0,getAdapterPosition(),"Sửa");
            contextMenu.add(0,1,getAdapterPosition(),"Xoá");

        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laptop_admin, parent,false);
        return new ViewHolder(inflate);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(holder.itemView.getContext()).load(arrayLaptop.get(position).getImage_url()).into(holder.imageLaptop);
        holder.tvLaptopName.setText(arrayLaptop.get(position).getName());
        holder.tvLaptopRam.setText(arrayLaptop.get(position).getRam());
        holder.tvLaptopRom.setText(arrayLaptop.get(position).getRom());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.tvLaptopPrice.setText(decimalFormat.format(arrayLaptop.get(position).getPrice()) + "đ");
        holder.tvLaptopPrice.setPaintFlags(holder.tvLaptopPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        int desPercent = (arrayLaptop.get(position).getPrice() - arrayLaptop.get(position).getSale_price())*100/arrayLaptop.get(position).getPrice();
        holder.tvLaptopDescPercent.setText("-" + String.valueOf(desPercent) + "%");
        holder.tvLaptopSalePrice.setText(decimalFormat.format(arrayLaptop.get(position).getSale_price()) + "đ");
        holder.tvLaptopScreen.setText("Màn hình: " + arrayLaptop.get(position).getScreen());
        holder.tvLaptopCpu.setText("CPU: " + arrayLaptop.get(position).getCpu());
        holder.tvLaptopCard.setText("Card: " + arrayLaptop.get(position).getCard());
        holder.tvLaptopPin.setText("Pin: " + arrayLaptop.get(position).getPin());
        holder.tvLaptopWeight.setText("Khối lượng: " + Float.toString(arrayLaptop.get(position).getWeight()) + "kg");

        holder.btnShowDetailLaptop.setOnClickListener(new View.OnClickListener() {
            int _id = arrayLaptop.get(position).getId();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailProductAdminActivity.class);
                intent.putExtra("id", _id);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnSuaDetailLaptop.setOnClickListener(new View.OnClickListener() {
            int _id = arrayLaptop.get(position).getId();
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), AddSPActivity.class);
                intent.putExtra("id", _id);
                v.getContext().startActivity(intent);
            }
        });

        holder.btnXoaLaptop.setOnClickListener(view -> {
            apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
            int _id = arrayLaptop.get(position).getId();
            System.out.println(_id);
            Call<LaptopModel> call = apiShopLapTop.deleteSP(_id);
            call.enqueue(new Callback<LaptopModel>() {
                @Override
                public void onResponse(Call<LaptopModel> call, Response<LaptopModel> response) {
                    LaptopModel laptopModel = response.body();
                    if (laptopModel.isSuccess()) {
                        Toast.makeText(view.getContext(), "Xoá thành công.", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(view.getContext(), AdminActivity.class);
                        view.getContext().startActivity(intent);
                        if (!mActivity.isFinishing()) {
                            mActivity.finish();
                        }
                        System.out.println("xoá thành công");
//
                    }

                }
                @Override
                public void onFailure(Call<LaptopModel> call, Throwable t) {
                    call.cancel();
                    Toast.makeText(view.getContext(), "Xoá thất bại.", Toast.LENGTH_LONG).show();

                }

            });
        });

    }

    @Override
    public int getItemCount() {
        return arrayLaptop.size();
    }
}