package io.swalitk.github.slhwalpaper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowMetrics;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public class ViewWallpaper extends AppCompatActivity {

    ImageView imageView;
    Button btn_setWallpaper_homeScreen, btn_setWallpaper_lockScreen;
    String imageUrl, title;
    Bitmap bitmap;
    WallpaperManager wallpaperManager;
    BitmapDrawable bitmapDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wallpaper);

        Intent intent=getIntent();
        imageUrl=intent.getStringExtra("imageUrl");
        title=intent.getStringExtra("title");

        imageView=findViewById(R.id.wallpperImageFullScreen);
        btn_setWallpaper_homeScreen=findViewById(R.id.btn_set_wallpaper_homescreen);
        btn_setWallpaper_lockScreen=findViewById(R.id.btn_set_wallpaper_lockscreen);

        Glide.with(getApplicationContext()).load("https://"+imageUrl).placeholder(R.drawable.ic_loading).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                btn_setWallpaper_homeScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setWallpaper("Display");
                    }
                });
                btn_setWallpaper_lockScreen.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setWallpaper("Lock");
                    }
                });
                return false;
            }
        }).into(imageView);
    }

    private void setWallpaper(String type){
        int[] window_sizes=getWindowSize();
        bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
        bitmap=Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), window_sizes[0], window_sizes[1], false);
        wallpaperManager=WallpaperManager.getInstance(getApplicationContext());
        try{
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.N){
                if(type.equals("Lock")){
                    wallpaperManager.setBitmap(bitmap,null , true, WallpaperManager.FLAG_LOCK);
                    Toast.makeText(this, "LockScreen Wallpaper Updated.", Toast.LENGTH_SHORT).show();
                }else{
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_SYSTEM);
                    Toast.makeText(this, "HomeScreen Wallpaper Updated.", Toast.LENGTH_SHORT).show();
                }

            }else{
                    wallpaperManager.setBitmap(bitmap);
                Toast.makeText(this, "Wallpaper has been updated.", Toast.LENGTH_SHORT).show();
            }

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(this, "Error while updating wallpaper.", Toast.LENGTH_SHORT).show();
        }
    }

    private int[] getWindowSize(){
        int[] size=new int[2];
        DisplayMetrics metrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        size[0]=metrics.widthPixels;
        size[1]=metrics.heightPixels;
        return size;
    }
}