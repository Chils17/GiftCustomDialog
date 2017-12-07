package com.webmyne.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chiragpatel on 07-11-2017.
 */

public class GiftDialog extends Dialog {
    private ImageView imgRightArrow;
    private ImageView imgLeftArrow;
    private Context context;
    private RecyclerView rvGift;
    private List<Gift> mData;
    private List<Gift> mDataOf6;
    private GiftAdapter adaptor;
    private int position = 0;
    private int count;
    private boolean IsHas3 = false;
    private int remainingData = 3;
    private RelativeLayout rLayout;
    private LinearLayout lLayout;
    private Animation LeftSwipe;
    private Animation rightSwipe;

    public GiftDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_gift);

        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        init();
        actionListener();
    }

    private void init() {
        context = getContext();
        LeftSwipe = AnimationUtils.loadAnimation(context, R.anim.slide_left);
        rightSwipe = AnimationUtils.loadAnimation(context, R.anim.slide_right);
        imgRightArrow = (ImageView) findViewById(R.id.imgRightArrow);
        imgLeftArrow = (ImageView) findViewById(R.id.imgLeftArrow);
        rvGift = (RecyclerView) findViewById(R.id.rvGift);
        rLayout = (RelativeLayout) findViewById(R.id.rLayout);
        lLayout = (LinearLayout) findViewById(R.id.lLayout);
        initRecyclerView();

    }

    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3);
        //gridLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL); // set Horizontal Orientation
        rvGift.setLayoutManager(gridLayoutManager);

        mData = new ArrayList<>();
        mDataOf6 = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Gift gift = new Gift();
            gift.setUrl("https://sc02.alicdn.com/kf/HTB1kmOeLXXXXXXOaFXX760XFXXXA/Cute-Emoji-Plush-Slipper-Expression-Men-And.png_350x350.png");
            gift.setName("Chapal");
            gift.setNumber(i + "");
            mData.add(gift);
        }
        adaptor = new GiftAdapter(context, mDataOf6, new GiftAdapter.OnclickItem() {
            @Override
            public void onClickItem() {
                rLayout.startAnimation(LeftSwipe);
            }
        });
        rvGift.setAdapter(adaptor);

        for (int i = 0; i < 6; i++) {
            mDataOf6.add(mData.get(i));
        }
        adaptor.setDataValues(mDataOf6);

        if ((mData.size() % 6) == 0) {
            count = mData.size() / 6;
            IsHas3 = false;
        } else {
            remainingData = (mData.size() % 6);
            IsHas3 = true;
            count = (mData.size() / 6) + 1;
        }

        updateButtonUI();
    }

    private void updateButtonUI() {
        if (position == 0) {
            imgLeftArrow.setVisibility(View.GONE);
        } else {
            imgLeftArrow.setVisibility(View.VISIBLE);
        }
        if (position == (count - 1)) {
            imgRightArrow.setVisibility(View.GONE);
        } else {
            imgRightArrow.setVisibility(View.VISIBLE);
        }
    }

    private void actionListener() {
        imgLeftArrow.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                if (position > 0) {
                                                    position--;
                                                }

                                                mDataOf6 = new ArrayList<>();
                                                for (int i = (position * 6); i < (position * 6) + 6; i++) {
                                                    mDataOf6.add(mData.get(i));
                                                }
                                                adaptor.setDataValues(mDataOf6);


                                                updateButtonUI();
                                            }
                                        }
        );

        imgRightArrow.setOnClickListener(new View.OnClickListener() {
                                             @Override
                                             public void onClick(View v) {
                                                 if (position < (count - 1)) {
                                                     position++;
                                                 }
                                                 Log.e("p", String.valueOf(position));
                                                 Log.e("c", String.valueOf(count));


                                                 if (IsHas3 && position == count - 1) {
                                                     mDataOf6 = new ArrayList<>();
                                                     for (int i = (position * 6); i < (position * 6) + remainingData; i++) {
                                                         mDataOf6.add(mData.get(i));
                                                     }

                                                 } else {
                                                     mDataOf6 = new ArrayList<>();
                                                     for (int i = (position * 6); i < (position * 6) + 6; i++) {
                                                         mDataOf6.add(mData.get(i));
                                                     }
                                                 }
                                                 adaptor.setDataValues(mDataOf6);
                                                 updateButtonUI();
                                             }
                                         }
        );

        LeftSwipe.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rLayout.setVisibility(View.GONE);
                lLayout.setVisibility(View.VISIBLE);
                lLayout.startAnimation(rightSwipe);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        rightSwipe.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
