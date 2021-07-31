package io.swalitk.github.slhwalpaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.List;

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
        ImageView imageView=convertView.findViewById(R.id.listImageView);
        TextView title=convertView.findViewById(R.id.listImageTitle);

        DataHandler dataSet=dataList.get(position);

        title.setText(dataSet.getTitle());
        Glide.with(context).load("https://"+dataSet.getThumbnail()).placeholder(R.drawable.ic_loading).error(R.drawable.no_image).into(imageView);

        return convertView;
    }
}
