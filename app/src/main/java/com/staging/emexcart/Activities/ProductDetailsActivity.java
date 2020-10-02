package com.staging.emexcart.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bogdwellers.pinchtozoom.ImageMatrixTouchHandler;
import com.bumptech.glide.Glide;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.staging.emexcart.Network.RestServiceBuilder;
import com.staging.emexcart.R;
import com.staging.emexcart.models.get_tax.Product;
import com.staging.emexcart.models.product_model.ProductDetails;
import com.staging.emexcart.models.product_model.ProductImages;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener {
    private WebView priceWv, descWv,showmoreWebView;
    private TextView prodName;
    private final String specsKey ="_specifications";
    private final String cbpKey ="_product_cbp";
    private String specifications,cbp;
    private SliderLayout sliderLayout;
    private PagerIndicator pagerIndicator;
    private ImageView sliderImageView;
    private Button showMore,showLess;
    private ProductDetails productDetails;
    private TextView tvProdBrand,tvProdCode,tvProdPrice,tvRewards,tvAvailabity;

    List<ProductImages> itemImages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        initView();
        loadData(id);
        initController();
        //push
    }

    private void initView() {
//        priceWv = findViewById(R.id.product_price_webView);
        descWv = findViewById(R.id.product_description_webView);
        showmoreWebView = findViewById(R.id.product_more_description_webView);
        prodName = findViewById(R.id.prodName);
        tvProdBrand =findViewById(R.id.brandName);
        tvProdCode =findViewById(R.id.prodCode);
        tvProdPrice =findViewById(R.id.Price);
        tvRewards =findViewById(R.id.Rewards);
        tvAvailabity =findViewById(R.id.Availablity);
        showMore =findViewById(R.id.btnshowMore);
        showLess =findViewById(R.id.btnshowLess);
        sliderLayout = (SliderLayout) findViewById(R.id.product_cover_slider);
        pagerIndicator = (PagerIndicator) findViewById(R.id.product_slider_indicator);
    }

    private void loadData(String id) {
        RestServiceBuilder.getService(getApplicationContext()).getSingleProducts(id).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {
                if (response.isSuccessful()) {
                    productDetails = response.body();
                    if (productDetails!= null) {
                        itemImages.addAll(productDetails.getImages());
                        addData(productDetails);
                        ImageSlider("", itemImages);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }

    private void itrateMetaData(ProductDetails productDetails){
        for (int i=0;i<productDetails.getMetaData().size();i++){
            if (productDetails.getMetaData().get(i).getKey().equals(specsKey)){
                specifications = productDetails.getMetaData().get(i).getValue().toString();
                Log.e("TAG", specifications);
            }
        }
    }
    private void itrateMetaData2(ProductDetails productDetails){
        for (int i=0;i<productDetails.getMetaData().size();i++){
            if (productDetails.getMetaData().get(i).getKey().equals(cbpKey)){
                cbp = productDetails.getMetaData().get(i).getValue().toString();
                Log.e("TAG", cbp);
            }
        }
    }

    private void addData(ProductDetails productDetails) {
        itrateMetaData(productDetails);
        initDescriptionWebView();
        itrateMetaData2(productDetails);
        if (!TextUtils.isEmpty(productDetails.getName())) {
            prodName.setText(productDetails.getName());
        }
        if (!TextUtils.isEmpty(productDetails.getName())) {
            tvProdBrand.setText(productDetails.getName());
        }
        if (!TextUtils.isEmpty(productDetails.getSku())) {
            tvProdCode.setText(productDetails.getSku()+"");
        }
        if (!TextUtils.isEmpty(productDetails.getStockStatus())) {
            tvAvailabity.setText(productDetails.getStockStatus());
        }
        if (!TextUtils.isEmpty(productDetails.getSalePrice())) {
            tvProdPrice.setText(productDetails.getSalePrice());
        }
        if (!TextUtils.isEmpty(cbp)) {
            tvRewards.setText(cbp);
        }

    }

    private void initController(){
        showMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLess.setVisibility(View.VISIBLE);
                showMore.setVisibility(View.GONE);
                showmoreWebView.setVisibility(View.VISIBLE);
                initMoreDescriptionWebView();
            }
        });

        showLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLess.setVisibility(View.GONE);
                showMore.setVisibility(View.VISIBLE);
                showmoreWebView.setVisibility(View.GONE);
            }
        });
    }

    private void initPriceWebView(String description){
        String price_styleSheet = "<style> " +
                "body{margin:0; padding:0} " +
                "p{color:#757575;} " +
                "img{display:inline; height:auto; max-width:100%;}" +
                "</style>";

        priceWv.setVerticalScrollBarEnabled(false);
        priceWv.setHorizontalScrollBarEnabled(false);
        priceWv.setBackgroundColor(Color.TRANSPARENT);
        priceWv.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        priceWv.loadDataWithBaseURL(null, price_styleSheet + description, "text/html", "utf-8", null);
    }

    private void initDescriptionWebView(){
        if (TextUtils.isEmpty(specifications)) {
            descWv.setVisibility(View.GONE);
        } else {
            descWv.setVisibility(View.VISIBLE);

//            String description = productDetails.getDescription();
            String desc_styleSheet = "<style> " +
                    "body{margin:0; padding:0} " +
                    "p{color:#757575;} " +
                    "img{display:inline; height:auto; max-width:100%;}" +
                    "</style>";

            descWv.setVerticalScrollBarEnabled(false);
            descWv.setNestedScrollingEnabled(true);
            descWv.setHorizontalScrollBarEnabled(false);
            descWv.setBackgroundColor(Color.TRANSPARENT);
            descWv.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            descWv.loadDataWithBaseURL(null, desc_styleSheet + specifications, "text/html", "utf-8", null);
        }
    }
    private void initMoreDescriptionWebView(){
        if (TextUtils.isEmpty(specifications)) {
            showmoreWebView.setVisibility(View.GONE);
        } else {
            showmoreWebView.setVisibility(View.VISIBLE);

//            String description = productDetails.getDescription();
            String desc_styleSheet = "<style> " +
                    "body{margin:0; padding:0} " +
                    "p{color:#757575;} " +
                    "img{display:inline; height:auto; max-width:100%;}" +
                    "</style>";

            showmoreWebView.setVerticalScrollBarEnabled(false);
            showmoreWebView.setNestedScrollingEnabled(true);
            showmoreWebView.setHorizontalScrollBarEnabled(false);
            showmoreWebView.setBackgroundColor(Color.TRANSPARENT);
            showmoreWebView.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
            showmoreWebView.loadDataWithBaseURL(null, desc_styleSheet + productDetails.getDescription(), "text/html", "utf-8", null);
        }
    }
    private void ImageSlider(String itemThumbnail, List<ProductImages> itemImages) {

        sliderLayout.removeAllSliders();

        // Initialize new HashMap<ImageName, ImagePath>
        final LinkedHashMap<String, String> slider_covers = new LinkedHashMap<>();
        // Initialize new Array for Image's URL
        final String[] images = new String[itemImages.size()];


        if (itemImages.size() > 0) {
            for (int i=0;  i< itemImages.size();  i++) {
                // Get Image's URL at given Position from itemImages List
                images[i] = itemImages.get(i).getSrc();
            }
        }


        // Put Image's Name and URL to the HashMap slider_covers
        if (images.length == 0) {
            if ("".equalsIgnoreCase(itemThumbnail)) {
                slider_covers.put("a", ""+R.drawable.placeholder);
            }
            else {
                slider_covers.put("a", itemThumbnail);
            }
        }
        else {
            for (int i=0;  i<images.length;  i++) {
                slider_covers.put("b"+i, images[i]);
            }
        }



        for(String name : slider_covers.keySet()) {

            // Initialize DefaultSliderView
            DefaultSliderView defaultSliderView = new DefaultSliderView(getApplicationContext()) {
                @Override
                public View getView() {
                    View v = LayoutInflater.from(getContext()).inflate(com.daimajia.slider.library.R.layout.render_type_default,null);

                    // Get daimajia_slider_image ImageView of DefaultSliderView
                    sliderImageView = (ImageView)v.findViewById(com.daimajia.slider.library.R.id.daimajia_slider_image);

                    // Set ScaleType of ImageView
                    sliderImageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    bindEventAndShow(v, sliderImageView);

                    return v;
                }
            };

            // Set Attributes(Name, Placeholder, Image, Type etc) to DefaultSliderView
            defaultSliderView
                    .description(name)
                    .empty(R.drawable.placeholder)
                    .image(slider_covers.get(name))
                    .setScaleType(DefaultSliderView.ScaleType.CenterInside)
                    .setOnSliderClickListener(this::onSliderClick);

            // Add DefaultSliderView to the SliderLayout
            sliderLayout.addSlider(defaultSliderView);
        }

        // Set PresetTransformer type of the SliderLayout
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);


        // Check if the size of Images in the Slider is less than 2
        if (slider_covers.size() < 2) {
            // Disable PagerTransformer
            sliderLayout.setPagerTransformer(false, new BaseTransformer() {
                @Override
                protected void onTransform(View view, float v) {
                }
            });

            // Hide Slider PagerIndicator
            sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);

        }
        else {
            // Set custom PagerIndicator to the SliderLayout
            sliderLayout.setCustomIndicator(pagerIndicator);
            // Make PagerIndicator Visible
            sliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        }
    }



    //*********** Handle the Click Listener on BannerImages of Slider ********//

    @Override
    public void onSliderClick(BaseSliderView slider) {

        int position = sliderLayout.getCurrentPosition();
        String img_url = itemImages.get(position).getSrc();

        final Dialog dialog = new Dialog(ProductDetailsActivity.this, android.R.style.Theme_Black_NoTitleBar_Fullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_image_view);

        ImageView dialog_image = dialog.findViewById(R.id.dialog_image);
        ImageButton dialog_cancel = dialog.findViewById(R.id.dialog_cancel);

        Glide.with(getApplicationContext())
                .load(img_url)
                .error(R.drawable.placeholder)
                .into(dialog_image);

        dialog_image.setOnTouchListener(new ImageMatrixTouchHandler(dialog.getContext()));

        dialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



}