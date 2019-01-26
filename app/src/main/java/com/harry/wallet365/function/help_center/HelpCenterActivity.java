package com.harry.wallet365.function.help_center;

import android.text.Html;
import android.widget.TextView;

import com.harry.wallet365.R;
import com.harry.wallet365.app_final.DisposableFinal;
import com.harry.wallet365.base.BaseActivity;
import com.harry.wallet365.network.entity.AboutEntity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Harry on 2019/1/25.
 * 帮助中心
 */
public class HelpCenterActivity extends BaseActivity<HelpCenterPresenter> {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_content)
    TextView tvContent;

    @Override
    protected int setupView() {
        return R.layout.activity_help_center;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        tvTitle.setText("帮助中心");

        mPresenter.about();
    }

    @Override
    protected ArrayList<Object> cancelNetWork() {
        ArrayList<Object> tags = new ArrayList<>();
        tags.add(DisposableFinal.MODIFY_PASSWORD_ACTIVITY_MODIFY);
        return tags;
    }

    @Override
    protected HelpCenterPresenter bindPresenter() {
        return new HelpCenterPresenter();
    }

    public void about(AboutEntity.DataBean data) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvContent.setText(Html.fromHtml(data.info, Html.FROM_HTML_MODE_LEGACY));
        } else {
            tvContent.setText(Html.fromHtml(data.info));
        }
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
