package cc.ibooker.zcompressviewlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;

/**
 * 按压TextView
 *
 * @author 邹峰立
 */
public class CompressTV extends android.support.v7.widget.AppCompatTextView {
    private TranslateAnimation startTranslateAnimation, endTranslateAnimation;
    private boolean enabled = true;
    private boolean lock = false;// 锁动画
    private boolean isCanExecute = true;// 点击事件是否可执行

    public CompressTV(Context context) {
        super(context);
    }

    CompressTV(Context context, int compressDis, int compressDuration) {
        this(context);
        this.setGravity(Gravity.CENTER);
        initTranslateAnimation(compressDis, compressDuration);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.enabled = enabled;
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
        endTranslateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (!lock) {
                    lock = true;
                    isCanExecute = true;
                } else
                    isCanExecute = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束-执行点击
                if (isCanExecute)
                    executeClick();
                lock = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
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
        if (!enabled) return true;
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                if (startTranslateAnimation != null)
                    startAnimation(startTranslateAnimation);
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (endTranslateAnimation != null)
                    startAnimation(endTranslateAnimation);
                else
                    executeClick();
                break;
        }
        // 拦截点击事件
        return true;
    }

    // 执行点击事件
    private void executeClick() {
        if (ClickUtil.isFastClick()) return;
        if (onCompressClickListener != null)
            onCompressClickListener.onCompressClick(this);
        else if (onClickListener != null)
            onClickListener.onClick(this);
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
