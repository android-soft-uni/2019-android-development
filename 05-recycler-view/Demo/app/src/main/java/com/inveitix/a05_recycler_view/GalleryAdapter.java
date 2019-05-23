package com.inveitix.a05_recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;

class GalleryAdapter extends RecyclerView.Adapter<GalleryItemViewHolder> {

    private GalleryItemViewHolder.GalleryItemListener clickListener;

    public GalleryAdapter(GalleryItemViewHolder.GalleryItemListener clickListener) {
        this.clickListener = clickListener;
    }

    private ImageItem[] images = new ImageItem[]{
            new ImageItem(R.drawable.brian, "Brian 1"),
            new ImageItem(R.drawable.wedding, "Wd 1"),
            new ImageItem(R.drawable.doctor, "Doc 1"),
            new ImageItem(R.drawable.brian, "Brian 1")
    };

    @NonNull
    @Override
    public GalleryItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_image, viewGroup, false);
        GalleryItemViewHolder holder = new GalleryItemViewHolder(v, clickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryItemViewHolder galleryItemViewHolder, int position) {
        ImageItem imageItem = images[position];
        galleryItemViewHolder.image.setImageResource(imageItem.imageRes);
        galleryItemViewHolder.txtDescription.setText(imageItem.imageDescription);
        galleryItemViewHolder.setDataItem(imageItem);
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
