package com.appbanlaptop.retrofit;


import com.appbanlaptop.model.Brand;
import android.content.Intent;

import com.appbanlaptop.model.BrandModel;
import com.appbanlaptop.model.FeedbackModel;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.model.MessageModel;
import com.appbanlaptop.model.OrderModel;
import com.appbanlaptop.model.ThongKeModel;
import com.appbanlaptop.model.Order;
import com.appbanlaptop.model.OrderModel;
import com.appbanlaptop.model.UserModel;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiShopLapTop {

    @GET("brand/get_brands.php")
    Observable<BrandModel> getBrands();

    @GET("laptop/get_laptops.php?")
    Call<LaptopModel> getLaptops(@Query("type") String type);

    @GET("laptop/get_laptops.php?")
    Observable<LaptopModel> getLaptopsAdmin(@Query("type") String type);

    @GET("laptop/get_laptop_detail.php?")
    Call<LaptopModel> getLaptopDetail(@Query("id") String id);


    @FormUrlEncoded
    @POST("user/login.php?")
    Call<UserModel> login(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("user/signup.php?")
    Call<UserModel> signup(
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email,
            @Field("fullname") String fullname,
            @Field("address") String address,
            @Field("phone_number") String phone_number
    );

//------------------------------------------------------------
//    Admin_product
    @FormUrlEncoded
    @POST("admin/product/insert.php?")
    Call<LaptopModel> insertSP(
            @Field("name_brand") String name_brand,
            @Field("name") String name,
            @Field("screen") String screen,
            @Field("cpu") String cpu,
            @Field("card") String card,
            @Field("ram") String ram,
            @Field("rom") String rom,
            @Field("pin") String pin,
            @Field("weight") float weight,
            @Field("os") String os,
            @Field("connector") String connector,
            @Field("price") int price,
            @Field("sale_price") int sale_price,
            @Field("special") String special,
            @Field("year_launch") int year_launch,
            @Field("image_url") String image_url,
            @Field("description") String description
    );


    @FormUrlEncoded
    @POST("admin/product/update.php?")
    Call<LaptopModel> updateSP(
            @Field("id") int id,
            @Field("name_brand") String name_brand,
            @Field("name") String name,
            @Field("screen") String screen,
            @Field("cpu") String cpu,
            @Field("card") String card,
            @Field("ram") String ram,
            @Field("rom") String rom,
            @Field("pin") String pin,
            @Field("weight") float weight,
            @Field("os") String os,
            @Field("connector") String connector,
            @Field("price") int price,
            @Field("sale_price") int sale_price,
            @Field("special") String special,
            @Field("year_launch") int year_launch,
            @Field("image_url") String image_url,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("admin/product/delete.php?")
    Call<LaptopModel> deleteSP(
            @Field("id") int id
    );

    //Brand_Admin
    @FormUrlEncoded
    @POST("admin/brand/insert_brand.php?")
    Call<BrandModel> insertBrand(
            @Field("name") String name,
            @Field("address") String address,
            @Field("image_url") String image_url

    );
    @FormUrlEncoded
    @POST("admin/brand/update_brand.php?")
    Call<BrandModel> updateBrand(
            @Field("id") int id,
            @Field("name") String name,
            @Field("address") String address,
            @Field("image_url") String image_url
    );
    @GET("admin/brand/get_brand_detail.php?")
    Call<BrandModel> getBrandDetail(@Query("id") String id);
    @GET("brand/get_brands.php")
    Call<BrandModel> getBrandsAdmin();
    @FormUrlEncoded
    @POST("admin/brand/delete_brand.php?")
    Call<BrandModel> deleteBrand(
                    @Field("id") int id);

    //User
    @GET("admin/user/get_list_user.php")
    Call<UserModel> getUsers();
    @GET("admin/user/get_user_detail.php?")
    Call<UserModel> getUserDetail(@Query("id") String id);
    @FormUrlEncoded
    @POST("admin/user/delete_user.php?")
    Call<UserModel> deleteUser(
            @Field("id") int id);

    //Order
    @GET("admin/order/get_list_oder.php")
    Call<OrderModel> getOrders(@Query("status") int status);
    @GET("admin/order/get_order_detail.php?")
    Call<OrderModel> getOrderDetail(@Query("id") String id);

    @FormUrlEncoded
    @POST("admin/order/update_order.php")
    Call<OrderModel> updateOrderStatus(
            @Field("id") int id,
            @Field("status") int status
    );
    @GET("admin/thongke/thongkesanphamchitiet.php")
    Call<ThongKeModel> getThongKe();

    @GET("admin/thongke/thongkedoanhthu.php")
    Observable<ThongKeModel> getThongKeDoanhThu();

    @GET("user/get_user.php?")
    Call<UserModel> getUser(@Query("id") String id);

    @POST("order/order.php")
    Call<OrderModel> createOrder(@Body Order order);

    @FormUrlEncoded
    @POST("user/update_user.php?")
    Call<UserModel> updateProfile(
            @Field("id") int id,
            @Field("fullname") String fullname,
            @Field("address") String address,
            @Field("phone_number") String phone_number,
            @Field("image_url") String image_url
    );

    @FormUrlEncoded
    @POST("user/change_password.php?")
    Call<UserModel> changePassword(
            @Field("id") int id,
            @Field("present_password") String present_password,
            @Field("new_password") String new_password
    );



    //Shipper
    @GET("shipper/get_don_da_xac_nhan.php")
    Call<OrderModel> getDonDaXacNhan();

    @FormUrlEncoded
    @POST("shipper/insert_transport.php")
    Call<OrderModel> insertTransport(
            @Field("shipper_id") int shipper_id,@Field("order_id") int order_id
    );

    @GET("shipper/get_order_detail_shipper.php?")
    Call<OrderModel> getOrderDetailShipper(@Query("id") String id);

    @GET("shipper/get_don_da_giao.php")
    Call<OrderModel> getDonDaGiao(@Query("shipper_id") int shipper_id);

    //admin-order
    @GET("shipper/get_don_shipper_da_nhan.php")
    Call<OrderModel> getDonShipperDaNhan();

    @FormUrlEncoded
    @POST("shipper/update_don_giao_cho_shipper.php")
    Call<OrderModel> updateStatusDonGiaoChoShipper(
            @Field("order_id") int order_id

    );

    @FormUrlEncoded
    @POST("shipper/update_don_shipper_giao_thanh_cong.php")
    Call<OrderModel> updateStatusShipperGiaoThanhCong(
            @Field("order_id") int order_id

    );

    @FormUrlEncoded
    @POST("shipper/update_don_shipper_giao_khong_thanh_cong.php")
    Call<OrderModel> updateStatusShipperGiaoKhongThanhCong(
            @Field("order_id") int order_id

    );

    @GET("shipper/get_don_da_nhan_cua_shipper.php")
    Call<OrderModel> getDonDaNhanCuaShipper(@Query("shipper_id") int shipper_id);

    @GET("shipper/get_don_da_huy.php")
    Call<OrderModel> getDonDaHuyCuaShipper(@Query("shipper_id") int shipper_id);

    @GET("order/get_order.php?")
    Call<OrderModel> getOrder(@Query("id") String id);

    @FormUrlEncoded
    @POST("order/update_order_received.php?")
    Call<OrderModel> updateOderReceive(@Field("detail_id") String detail_id);

    @FormUrlEncoded
    @POST("order/feedback.php?")
    Call<OrderModel> sendFeedback(
            @Field("detail_id") String detail_id,
            @Field("star") String star,
            @Field("comment") String comment,
            @Field("user_fullname") String user_fullname,
            @Field("user_avatar") String user_avatar,
            @Field("laptop_id") String laptop_id
    );

    @GET("order/get_feedback.php?")
    Call<FeedbackModel> getFeedbacks(@Query("id") String id);
}

