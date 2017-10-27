package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.ImageItemSelectedObserver;
import com.globant.equattrocchio.data.response.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class ImageItemAdapter extends RecyclerView.Adapter<ImageItemAdapter.ImageItemViewHolder> {
    private List<Image> mData;

    public ImageItemAdapter(List<Image> data) {
        mData = data;
    }

    @Override
    public ImageItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_imageitem, parent, false);

        ImageItemViewHolder vh = new ImageItemViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ImageItemViewHolder holder, int position) {
        holder.idLabel.setText(String.valueOf(mData.get(position).getId()));
        Glide
                .with(holder.view)
                .load(mData.get(position).getUrl())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ImageItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_splashbase_img) ImageView image;
        @BindView(R.id.text_splashbase_img_id)
        public TextView idLabel;

        public View view;

        public ImageItemViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.card_splashbase_img_item)
        public void onImageItemSelected(){
            RxBus.post(new ImageItemSelectedObserver.ImageItemSelected(idLabel.getText().toString()));
        }
    }

}
