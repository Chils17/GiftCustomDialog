package com.webmyne.customdialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chiragpatel on 07-11-2017.
 */

public class GiftAdapter extends RecyclerView.Adapter<GiftAdapter.MyViewHolder> {
    private Context context;
    private OnclickItem onclickItem;
    private List<Gift> giftList;

    public GiftAdapter(Context context, List<Gift> giftList, OnclickItem onclickItem) {
        this.context = context;
        this.giftList = giftList;
        this.onclickItem = onclickItem;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.gift_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setValues(giftList.get(position));
    }

    @Override
    public int getItemCount() {
        return giftList.size();
    }

    public void setDataValues(List<Gift> mDataOf6) {
        this.giftList = new ArrayList<>();
        this.giftList = mDataOf6;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;
        private final TextView txtValue;
        private ImageView imgGift;

        public MyViewHolder(View itemView) {
            super(itemView);
            imgGift = (ImageView) itemView.findViewById(R.id.imgGift);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtValue = (TextView) itemView.findViewById(R.id.txtValue);

        }

        public void setValues(Gift gift) {
            txtName.setText(gift.getName());
            txtValue.setText(gift.getNumber());
            Function.loadImage(context, gift.getUrl(), imgGift);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickItem.onClickItem();
                }
            });
        }
    }

    public interface OnclickItem {
        void onClickItem();
    }

}
