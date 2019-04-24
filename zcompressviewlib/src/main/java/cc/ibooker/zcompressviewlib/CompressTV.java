package cc.ibooker.zcompressviewlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 按压TextView
 *
 * @author 邹峰立
 */
public class CompressTV extends android.support.v7.widget.AppCompatTextView {
    private TranslateAnimation startTranslateAnimation, endTranslateAnimation;

    public CompressTV(Context context) {
        super(context);
    }

    CompressTV(Context context, int compressDis, int compressDuration) {
        this(context);
        initTranslateAnimation(compressDis, compressDuration);
    }

    // 设置动画
    public void initTranslateAnimation(int compressDis, int compressDuration) {
        // 开始动画
        startTranslateAnimation = new TranslateAnimation(0, 0, 0, compressDis);
        startTranslateAnimation.setDuration(compressDuration);
        startTranslateAnimation.setFillAfter(true);
        startTranslateAnimation.setInterpolator(new DecelerateInterpolator());

        // 结束动画
        endTranslateAnimation = new TranslateAnimation(0, 0, compressDis, 0);
        endTranslateAnimation.setDuration(compressDuration);
        endTranslateAnimation.setFillAfter(true);
        endTranslateAnimation.setInterpolator(new DecelerateInterpolator());
    }

    // 设置父控件不拦截CompressTextView事件
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(event);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (startTranslateAnimation != null)
                    startAnimation(startTranslateAnimation);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (onCompressClickListener != null)
                    onCompressClickListener.onCompressClick(this);
                else if (onClickListener != null)
                    onClickListener.onClick(this);
                if (endTranslateAnimation != null)
                    startAnimation(endTranslateAnimation);
                break;
        }
        // 拦截点击事件
        return true;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        this.onClickListener = l;
    }

    // 点击事件接口
    public interface OnCompressClickListener {
        void onCompressClick(View view);
    }

    private OnClickListener onClickListener;
    private OnCompressClickListener onCompressClickListener;

    public void setOnCompressClickListener(OnCompressClickListener onCompressClickListener) {
        this.onCompressClickListener = onCompressClickListener;
    }
}
