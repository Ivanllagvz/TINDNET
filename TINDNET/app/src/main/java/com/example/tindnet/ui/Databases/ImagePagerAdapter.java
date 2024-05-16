package com.example.tindnet.ui.Databases;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import com.bumptech.glide.Glide;
import com.example.tindnet.R;

import java.net.CookieHandler;
import java.util.List;

public class ImagePagerAdapter extends PagerAdapter {

    private Context context;
    private List<String> imageUrls;

    public ImagePagerAdapter(Context context, List<String> imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }

    @Override
    public int getCount() {
        return imageUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image, container, false);
        ImageView imageView = view.findViewById(R.id.image_view);

        Log.d("ImagePagerAdapter", "Cargando imagen URI: " + imageUrls.get(position));

        Glide.with(context)
                .load(imageUrls.get(position))
                .placeholder(R.drawable.logo)
                .error(R.drawable.logo_webstyles)
                .into(imageView);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}