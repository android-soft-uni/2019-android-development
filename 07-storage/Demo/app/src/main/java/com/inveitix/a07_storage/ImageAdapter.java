package com.inveitix.a07_storage;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageHolder> {

    private List<ImageData> data = new ArrayList<>();
    private ImageListener listener;

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ImageHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.setup(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addItem(Bitmap bitmap, String path) {
        data.add(new ImageData(bitmap, path));
        notifyItemInserted(data.size() - 1);
    }

    public void addClickListener(ImageListener listener) {
        this.listener = listener;
    }

    public class ImageHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;
        private ImageListener listener;

        public ImageHolder(@NonNull View itemView, ImageListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_photo);
            textView = itemView.findViewById(R.id.txt_photo_path);
            this.listener = listener;
        }

        public void setup(ImageData imageData) {
            imageView.setImageBitmap(imageData.getImage());
            textView.setText(imageData.getImagePath());
            imageView.setOnClickListener(v -> {
                if(listener != null) listener.onImageClicked(imageData);
            });
        }
    }

    public class ImageData {
        private Bitmap image;
        private String imagePath;

        public ImageData(Bitmap image, String imagePath) {
            this.image = image;
            this.imagePath = imagePath;
        }

        public Bitmap getImage() {
            return image;
        }

        public String getImagePath() {
            return imagePath;
        }
    }

    public interface ImageListener {
        void onImageClicked(ImageData data);
    }
}
