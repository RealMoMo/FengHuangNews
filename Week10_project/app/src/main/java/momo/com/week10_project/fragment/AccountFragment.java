package momo.com.week10_project.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import momo.com.week10_project.R;
import momo.com.week10_project.ui.LoginActivity;
import momo.com.week10_project.utils.Constant;

/**
 * Mob第三方登录,数据不做保存.
 */
public class AccountFragment extends Fragment implements View.OnClickListener {


    ImageView iv_head;
    TextView accountName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViews(view);
    }

    private void setupViews(View view) {
        iv_head = (ImageView) view.findViewById(R.id.account_head);
        accountName = (TextView) view.findViewById(R.id.account_username);
        iv_head.setOnClickListener(this);
        accountName.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent,1);


    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(data!=null) {
                String headUrl = data.getStringExtra(Constant.LOGIN_ICON);
                String name = data.getStringExtra(Constant.LOGIN_NAME);

                Glide.with(this).load(headUrl).asBitmap().centerCrop().into(new BitmapImageViewTarget(iv_head) {
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable circularBitmapDrawable =
                                RoundedBitmapDrawableFactory.create(getActivity().getResources(), resource);
                        circularBitmapDrawable.setCircular(true);
                        iv_head.setImageDrawable(circularBitmapDrawable);
                    }
                });
                accountName.setText(name);
            }


    }
}
