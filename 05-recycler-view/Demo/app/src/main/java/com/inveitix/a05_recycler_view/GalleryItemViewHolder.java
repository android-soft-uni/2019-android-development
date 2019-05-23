package com.inveitix.a05_recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GalleryItemViewHolder extends RecyclerView.ViewHolder {

    ImageView image;
    TextView txtDescription;
    ImageItem dataItem;

    public GalleryItemViewHolder(@NonNull View itemView, final GalleryItemListener listener) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        txtDescription = itemView.findViewById(R.id.txt_description);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onGalleryItemClicked(dataItem);
            }
        });
    }

    public void setDataItem(ImageItem dataItem) {
        this.dataItem = dataItem;
    }

    public interface GalleryItemListener {
        void onGalleryItemClicked(ImageItem item);
    }
}
