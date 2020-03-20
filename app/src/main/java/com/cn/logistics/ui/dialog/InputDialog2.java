package com.cn.logistics.ui.dialog;

import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.fragment.app.FragmentActivity;

import com.cn.logistics.R;
import com.cn.logistics.common.MyDialogFragment;
import com.hjq.dialog.base.BaseDialog;
import com.hjq.toast.ToastUtils;

/**
 *    author : Android 轮子哥
 *    github : https://github.com/getActivity/AndroidProject
 *    time   : 2019/02/27
 *    desc   : 输入对话框
 */
public final class InputDialog2 {

    public static final class Builder
            extends MyDialogFragment.Builder<Builder>
            implements View.OnClickListener,
            BaseDialog.OnShowListener,
            BaseDialog.OnDismissListener {

        private OnListener mListener;
        private boolean mAutoDismiss = true;

        private final TextView mTitleView;
        private final EditText mInputView;

        private final TextView mCancelView;
        private final View mLineView;
        private final TextView mConfirmView;
        private final EditText mInputView2;

        public Builder(FragmentActivity activity) {
            super(activity);
            setContentView(R.layout.dialog_input2);
            setAnimStyle(BaseDialog.AnimStyle.IOS);

            mTitleView = findViewById(R.id.tv_input_title2);
            mInputView = findViewById(R.id.tv_input_message21);
            mInputView2 = findViewById(R.id.tv_input_message22);

            mCancelView  = findViewById(R.id.tv_input_cancel2);
            mLineView = findViewById(R.id.v_input_line2);
            mConfirmView  = findViewById(R.id.tv_input_confirm2);

            mCancelView.setOnClickListener(this);
            mConfirmView.setOnClickListener(this);

            addOnShowListener(this);
            addOnDismissListener(this);
        }

        public Builder setTitle(@StringRes int id) {
            return setTitle(getString(id));
        }
        public Builder setTitle(CharSequence text) {
            mTitleView.setText(text);
            return this;
        }

        public Builder setHint(@StringRes int id) {
            return setHint(getString(id));
        }
        public Builder setHint(CharSequence text) {
            mInputView.setHint(text);
            return this;
        }
        public Builder setHint2(@StringRes int id) {
            return setHint2(getString(id));
        }
        public Builder setHint2(CharSequence text) {
            mInputView2.setHint(text);
            return this;
        }

        public Builder setContent(@StringRes int id) {
            return setContent(getString(id));
        }
        public Builder setContent(CharSequence text) {
            mInputView.setText(text);
            int index = mInputView.getText().toString().length();
            if (index > 0) {
                mInputView.requestFocus();
                mInputView.setSelection(index);
            }
            return this;
        }
        public Builder setContent2(@StringRes int id) {
            return setContent2(getString(id));
        }
        public Builder setContent2(CharSequence text) {
            mInputView2.setText(text);
            int index = mInputView2.getText().toString().length();
            if (index > 0) {
                mInputView2.requestFocus();
                mInputView2.setSelection(index);
            }
            return this;
        }

        public Builder setConfirm(@StringRes int id) {
            return setConfirm(getString(id));
        }
        public Builder setConfirm(CharSequence text) {
            mConfirmView.setText(text);
            return this;
        }

        public Builder setCancel(@StringRes int id) {
            return setCancel(getString(id));
        }
        public Builder setCancel(CharSequence text) {
            mCancelView.setText(text);

            mLineView.setVisibility((text == null || "".equals(text.toString())) ? View.GONE : View.VISIBLE);
            return this;
        }

        public Builder setAutoDismiss(boolean dismiss) {
            mAutoDismiss = dismiss;
            return this;
        }

        public Builder setListener(OnListener listener) {
            mListener = listener;
            return this;
        }

        /**
         * {@link BaseDialog.OnShowListener}
         */
        @Override
        public void onShow(BaseDialog dialog) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    getSystemService(InputMethodManager.class).showSoftInput(mInputView, 0);
                }
            }, 500);
        }

        /**
         * {@link BaseDialog.OnDismissListener}
         */
        @Override
        public void onDismiss(BaseDialog dialog) {
            getSystemService(InputMethodManager.class).hideSoftInputFromWindow(mInputView.getWindowToken(), 0);
        }

        /**
         * {@link View.OnClickListener}
         */
        @Override
        public void onClick(View v) {
//            if (mAutoDismiss) {
//                dismiss();
//            }

            if (mListener != null) {
                if (v == mConfirmView) {
                    // 判断输入是否为空
                    if (TextUtils.isEmpty(mInputView.getText().toString())){
                        ToastUtils.show("请输入账号！！！");
                        return;
                    }
                    if (TextUtils.isEmpty(mInputView2.getText().toString())){
                        ToastUtils.show("请输入密码！！！");
                        return;
                    }
                    dismiss();
                    mListener.onConfirm(getDialog(), mInputView.getText().toString(), mInputView2.getText().toString());
                } else if (v == mCancelView) {
                    dismiss();
                    mListener.onCancel(getDialog());
                }
            }
        }
    }

    public interface OnListener {

        /**
         * 点击确定时回调
         */
        void onConfirm(BaseDialog dialog, String content, String content2);

        /**
         * 点击取消时回调
         */
        void onCancel(BaseDialog dialog);
    }
}