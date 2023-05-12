package com.appbanlaptop.activity.admin.product;

import android.content.Context;
import android.graphics.Paint;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.appbanlaptop.R;
import com.appbanlaptop.databinding.ActivityAddSpBinding;
import com.appbanlaptop.fragment.LaptopDetailFragment;
import com.appbanlaptop.model.Laptop;
import com.appbanlaptop.model.LaptopModel;
import com.appbanlaptop.retrofit.ApiShopLapTop;
import com.appbanlaptop.retrofit.RetrofitClient;
import com.appbanlaptop.utils.Utils;
import com.bumptech.glide.Glide;

import org.commonmark.node.Image;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.HtmlRenderer;

import java.text.DecimalFormat;
import java.util.Map;

import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class DetailProductAdminActivity extends AppCompatActivity {
    View layoutView;
    ApiShopLapTop apiShopLapTop;
    String id;
    TextView tvLaptopBrandName, tvLaptopName, tvLaptopPrice, tvLaptopSalePrice, tvLaptopCpu;
    TextView tvLaptopRam, tvLaptopRom, tvLaptopScreen, tvLaptopCard, tvLaptopConnector;
    TextView tvLaptopSpecial, tvLaptopOs, tvLaptopWeight, tvLaptopPin, tvLaptopYearLaunch;
    ImageView imageLaptop;
    WebView webView;
    Button btnAddToCart, btnBuyNow;
    Integer laptopId, quantityOld = 0;;
    String laptopImageUrl;
    int productId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_laptop_detail_admin);
        apiShopLapTop = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiShopLapTop.class);
        productId = getIntent().getIntExtra("id", -1);

        // getArguments from parent
//        id = getArguments().getString("id");
        System.out.println(productId);

        //Anh xa
        AnhXa();
        getLaptopDetail(String.valueOf(productId));
    }

    private void getLaptopDetail(String id) {
        Call<LaptopModel> call = apiShopLapTop.getLaptopDetail(id);
        call.enqueue(new Callback<LaptopModel>() {
            @Override
            public void onResponse(Call<LaptopModel> call, Response<LaptopModel> response) {
                LaptopModel laptopModel = response.body();
                if (laptopModel.isSuccess()) {
                    setDataResponseToView(laptopModel);
                }
            }
            @Override
            public void onFailure(Call<LaptopModel> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void setDataResponseToView(LaptopModel laptopModel) {
        Laptop laptop = laptopModel.getResult().get(0);

        laptopId = laptop.getId();
        laptopImageUrl = laptop.getImage_url();

        tvLaptopBrandName.setText("Laptop / Laptop " + laptop.getBrand().getName());
//        Glide.with(layoutView.getContext()).load(laptop.getImage_url()).into(imageLaptop);
        tvLaptopName.setText(laptop.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvLaptopPrice.setText(decimalFormat.format(laptop.getPrice()) + "đ");
        tvLaptopPrice.setPaintFlags(tvLaptopPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        tvLaptopSalePrice.setText(decimalFormat.format(laptop.getSale_price()) + "đ");
        tvLaptopCpu.setText(laptop.getCpu());
        tvLaptopRam.setText(laptop.getRam());
        tvLaptopRom.setText(laptop.getRom());
        tvLaptopScreen.setText(laptop.getScreen());
        tvLaptopCard.setText(laptop.getCard());
        tvLaptopSpecial.setText(laptop.getSpecial());
        tvLaptopOs.setText(laptop.getOs());
        tvLaptopWeight.setText(Float.toString(laptop.getWeight()));
        tvLaptopPin.setText(laptop.getPin());
        tvLaptopYearLaunch.setText(String.valueOf(laptop.getYear_launch()));
        tvLaptopConnector.setText(laptop.getConnector());

        //markdown
        String markdown = laptop.getDescription();
        Parser parser = Parser.builder().build();
        Node document = parser.parse(markdown);
        HtmlRenderer renderer = HtmlRenderer.builder().attributeProviderFactory(context -> new DetailProductAdminActivity.CustomAttributeProvider()).build();
        String html = renderer.render(document);

        webView.setWebViewClient(new WebViewClient());
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    public static class CustomAttributeProvider implements AttributeProvider {
        public void setAttributes(Node node, String tagName, Map<String, String> attributes) {
            if (node instanceof Image) {
                attributes.put("width", "100%");
                attributes.put("height", "auto");
            }
        }
    }

    private void AnhXa() {
        tvLaptopBrandName = findViewById(R.id.tvLaptopBrandNameAdmin);
        tvLaptopName = findViewById(R.id.tvLaptopNameAdmin);
        tvLaptopPrice = findViewById(R.id.tvLaptopPriceAdmin);
        tvLaptopSalePrice = findViewById(R.id.tvLaptopSalePriceAdmin);
        tvLaptopCpu = findViewById(R.id.tvLaptopCpuAdmin);
        tvLaptopRam = findViewById(R.id.tvLaptopRamAdmin);
        tvLaptopRom = findViewById(R.id.tvLaptopRomAdmin);
        tvLaptopScreen = findViewById(R.id.tvLaptopScreenAdmin);
        tvLaptopConnector = findViewById(R.id.tvLaptopConnectorAdmin);
        tvLaptopCard = findViewById(R.id.tvLaptopCardAdmin);
        tvLaptopSpecial = findViewById(R.id.tvLaptopSpecialAdmin);
        tvLaptopOs = findViewById(R.id.tvLaptopOsAdmin);
        tvLaptopWeight = findViewById(R.id.tvLaptopWeightAdmin);
        tvLaptopPin = findViewById(R.id.tvLaptopPinAdmin);
        tvLaptopYearLaunch = findViewById(R.id.tvLaptopYearLaunchAdmin);
        imageLaptop = findViewById(R.id.imageLaptopAdmin);

        webView = findViewById(R.id.webViewAdmin);

//        btnAddToCart = findViewById(R.id.btnAddToCartAdmin);
//        btnBuyNow = findViewById(R.id.btnBuyNowAdmin);
    }
}
