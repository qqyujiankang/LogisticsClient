package com.cn.logistics.ui.dialog;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.cn.logistics.R;
import com.cn.logistics.common.MyDialogFragment;
import com.hjq.dialog.base.BaseDialog;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2018/10/18
 *    desc   : 可进行拷贝的副本
 */
public final class SpecialDialog {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder> implements View.OnClickListener {

        private final EditText txt;
        private final TextView up;

        private SpecialDialog.OnListener mListener;

        public Builder(FragmentActivity activity) {
            super(activity);

            setContentView(R.layout.dialog_special);
            setAnimStyle(BaseDialog.AnimStyle.IOS);
            setGravity(Gravity.CENTER);


            txt = findViewById(R.id.et_special_txt);
            up = findViewById(R.id.tv_special_commit);


            up.setOnClickListener(this);


        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.tv_special_commit:
                    if (TextUtils.isEmpty(txt.getText().toString().trim())){
                        dismiss();
                    }else {
                        mListener.onUp(getDialog(),txt.getText().toString().trim());
                    }
                    break;


            }
        }
    }


    public interface OnListener {

        /**
         * 上传回调
         */
        void onUp(BaseDialog dialog, String text);
    }

}