package com.harry.wallet365.function.address;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.harry.wallet365.R;
import com.harry.wallet365.network.entity.AddressEntity;

/**
 * Created by Harry on 2019/2/19.
 */
public class AddressAdapter extends BaseQuickAdapter<AddressEntity.DataBean.ListBean, BaseViewHolder> {

    public AddressAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, AddressEntity.DataBean.ListBean item) {
        TextView tvDefault = helper.getView(R.id.tv_default);
        if (item.status == 1) {
            tvDefault.setVisibility(View.VISIBLE);
        } else {
            tvDefault.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_name, item.realname)
                .setText(R.id.tv_phone, item.mobile)
                .addOnClickListener(R.id.tv_edit)
                .addOnClickListener(R.id.tv_delete)
                .setText(R.id.tv_address, item.content);
    }
}
