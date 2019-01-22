package com.harry.wallet365.function.nearby;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Harry on 2018/11/20.
 */
public class NearbyMultiItem implements MultiItemEntity {

    public static final int TOP = 1;
    public static final int BOTTOM = 2;

    private int itemType;

    public NearbyMultiItem(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
