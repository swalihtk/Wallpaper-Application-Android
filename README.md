# Wallpaper Application Android

SwipeRefreshLayout
-----------------
- Helps to refresh the list

Scale
------
- It is an option in ImageView in xml thal helps to resize and set images acording to scree.

List Adapter
-----------

```java
public class WallpaperAdapter extends ArrayAdapter<DataHandler> {

    List<DataHandler> dataList;
    Context context;

    WallpaperAdapter(Context context, int resource, List<DataHandler> list){
        super(context, resource, list);
        this.context=context;
        this.dataList=list;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView= LayoutInflater.from(context).inflate(R.layout.image_list, parent, false);
        return convertView;
    }
}
```

Glide Dependency
---------------
- Helps to load image from url
- Multiple options

```java
Glide.with(context).load(url).placeholder(R.drawable.ic_loading).error(R.drawable.no_image).into(imageView);
```

Wallpaper Manger
----------------
- Android library for set Wallpaper for Mobile Phones

DisplayMetrics
---------------
- Android inbuilt library
- Used to get H/W related info
- WidthPixels, HeightPixels etc.

BitmapDrawable and Bitmap
-------------------------
- Bitmap make file into bits

- BitmapDrawable helpas to make a drawable file into bitmap

eg:
```java
 bitmapDrawable=(BitmapDrawable) imageView.getDrawable();
```

- Bitmap helps to make scaled files and others

eg:
```java
 Bitmap.createScaledBitmap(bitmapDrawable.getBitmap(), window_sizes[0], window_sizes[1], false);
```

