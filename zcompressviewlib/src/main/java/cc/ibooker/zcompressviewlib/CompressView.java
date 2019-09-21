package cc.ibooker.zcompressviewlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * 琴键布局
 *
 * @author 邹峰立
 */
public class CompressView extends FrameLayout {
    private int defaultBg = R.drawable.bg_compress_54adaa_7,
            compressBg = R.drawable.bg_compress_57cac6_7,
            compressWidth = 140,
            compressHeight = 54,
            compressDis = 20,
            compressDuration = 100,
            compressTextColor = 0,
            compressTextGravity = 0;
    private CharSequence compressText = "";
    private float compressTextSize = 16;
    private boolean compressTextBold = false;
    private boolean compressIsEnabled = true;
    private CompressTV textView;
    private View view;
    private LayoutParams layoutParams1, layoutParams2;
    private int compressBackGroud;
    private int compressDrawableLeft, compressDrawableRight, compressDrawableTop, compressDrawableBottom;

    public int getDefaultBg() {
        return defaultBg;
    }

    public void setDefaultBg(int defaultBg) {
        this.defaultBg = defaultBg;
        if (view != null)
            view.setBackgroundResource(defaultBg);
    }

    public int getCompressBg() {
        return compressBg;
    }

    public void setCompressBg(int compressBg) {
        this.compressBg = compressBg;
        if (textView != null)
            textView.setBackgroundResource(compressBg);
    }

    public int getCompressWidth() {
        return compressWidth;
    }

    public void setCompressWidth(int width) {
        int compressWidth1 = width * 2;
        int compressWidth2 = dp2px(getContext(), width);
        compressWidth = compressWidth1 > compressWidth2 ? compressWidth1 : compressWidth2;
        if (layoutParams1 != null) {
            layoutParams1.width = compressWidth;
            view.setLayoutParams(layoutParams1);
        }
        if (layoutParams2 != null) {
            layoutParams2.width = compressWidth;
            textView.setLayoutParams(layoutParams2);
        }
    }

    public int getCompressHeight() {
        return compressHeight;
    }

    public void setCompressHeight(int height) {
        int compressHeight1 = height * 2;
        int compressHeight2 = dp2px(getContext(), height);
        compressHeight = compressHeight1 > compressHeight2 ? compressHeight1 : compressHeight2;
        if (layoutParams1 != null) {
            layoutParams1.height = compressHeight;
            view.setLayoutParams(layoutParams1);
        }
        if (layoutParams2 != null) {
            layoutParams2.height = compressHeight;
            textView.setLayoutParams(layoutParams2);
        }
    }

    public int getCompressDis() {
        return compressDis;
    }

    public void setCompressDis(int dis) {
        int compressDis1 = dis * 2;
        int compressDis2 = dp2px(getContext(), dis);
        compressDis = compressDis1 > compressDis2 ? compressDis1 : compressDis2;
        if (layoutParams1 != null) {
            layoutParams1.topMargin = compressDis;
            view.setLayoutParams(layoutParams1);
        }
        if (textView != null) {
            textView.initTranslateAnimation(compressDis, compressDuration);
        }
    }

    public int getCompressDuration() {
        return compressDuration;
    }

    public void setCompressDuration(int compressDuration) {
        this.compressDuration = compressDuration;
        if (textView != null) {
            textView.initTranslateAnimation(compressDis, compressDuration);
        }
    }

    public int getCompressTextColor() {
        return compressTextColor;
    }

    public int getCompressTextGravity() {
        return compressTextGravity;
    }

    public CharSequence getCompressText() {
        return compressText;
    }

    public float getCompressTextSize() {
        return compressTextSize;
    }

    public boolean isCompressTextBold() {
        return compressTextBold;
    }

    public CompressTV getTextView() {
        return textView;
    }

    public void setTextView(CompressTV textView) {
        this.textView = textView;
    }

    public CompressView(@NonNull Context context) {
        this(context, null);
    }

    public CompressView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @SuppressLint("ClickableViewAccessibility")
    public CompressView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        removeAllViews();
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);

        if (attrs != null) {
            TypedArray typeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CompressView, 0, 0);
            defaultBg = typeArray.getResourceId(R.styleable.CompressView_defaultBg, R.drawable.bg_compress_54adaa_7);
            compressBg = typeArray.getResourceId(R.styleable.CompressView_compressBg, R.drawable.bg_compress_57cac6_7);
            int width = typeArray.getInt(R.styleable.CompressView_compressWidth, 70);
            int compressWidth1 = width * 2;
            int compressWidth2 = dp2px(context, width);
            compressWidth = compressWidth1 > compressWidth2 ? compressWidth1 : compressWidth2;
            int height = typeArray.getInt(R.styleable.CompressView_compressHeight, 27);
            int compressHeight1 = height * 2;
            int compressHeight2 = dp2px(context, height);
            compressHeight = compressHeight1 > compressHeight2 ? compressHeight1 : compressHeight2;
            int dis = typeArray.getInt(R.styleable.CompressView_compressDis, 10);
            int compressDis1 = dis * 2;
            int compressDis2 = dp2px(context, dis);
            compressDis = compressDis1 > compressDis2 ? compressDis1 : compressDis2;

            compressDuration = typeArray.getInt(R.styleable.CompressView_compressDuration, 100);
            compressText = typeArray.getString(R.styleable.CompressView_compressText);
            compressTextColor = typeArray.getInt(R.styleable.CompressView_compressTextColor, Color.WHITE);
            compressTextSize = typeArray.getFloat(R.styleable.CompressView_compressTextSize, 16);
            compressTextBold = typeArray.getBoolean(R.styleable.CompressView_compressTextBold, false);
            compressTextGravity = typeArray.getInt(R.styleable.CompressView_compressTextGravity, Gravity.CENTER);
            compressIsEnabled = typeArray.getBoolean(R.styleable.CompressView_compressIsEnabled, true);
            compressBackGroud = typeArray.getResourceId(R.styleable.CompressView_compressBackGroud, 0);
            compressDrawableLeft = typeArray.getResourceId(R.styleable.CompressView_compressDrawableLeft, 0);
            compressDrawableRight = typeArray.getResourceId(R.styleable.CompressView_compressDrawableRight, 0);
            compressDrawableTop = typeArray.getResourceId(R.styleable.CompressView_compressDrawableTop, 0);
            compressDrawableBottom = typeArray.getResourceId(R.styleable.CompressView_compressDrawableBottom, 0);
            typeArray.recycle();
        }

        layoutParams1 = new LayoutParams(compressWidth, compressHeight, Gravity.BOTTOM);
        layoutParams1.topMargin = compressDis;
        view = new View(context);
        view.setBackgroundResource(defaultBg);
        view.setLayoutParams(layoutParams1);
        addView(view);

        layoutParams2 = new LayoutParams(compressWidth, compressHeight, Gravity.TOP);
        textView = new CompressTV(context, compressDis, compressDuration);
        textView.setBackgroundResource(compressBg);
        textView.setLayoutParams(layoutParams2);
        addView(textView);

        setCompressText(compressText);
        setCompressTextColor(compressTextColor);
        setCompressTextSize(compressTextSize);
        setCompressTextBold(compressTextBold);
        setCompressTextGravity(compressTextGravity);
        setCompressIsEnabled(compressIsEnabled);
        if (compressBackGroud != 0)
            setCompressBackGroud(compressBackGroud);
        if (compressDrawableLeft != 0
                || compressDrawableRight != 0
                || compressDrawableTop != 0
                || compressDrawableBottom != 0)
            setCompressCompoundDrawables(compressDrawableLeft, compressDrawableTop, compressDrawableRight, compressDrawableBottom);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.compressIsEnabled = enabled;
        setCompressIsEnabled(compressIsEnabled);
    }

    // 设置Drawable
    public CompressView setCompressCompoundDrawables(Drawable left, Drawable top, Drawable right, Drawable bottom) {
        textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return this;
    }

    public CompressView setCompressCompoundDrawables(int left, int top, int right, int bottom) {
        this.compressDrawableLeft = left;
        this.compressDrawableRight = right;
        this.compressDrawableTop = top;
        this.compressDrawableBottom = bottom;
        textView.setCompoundDrawablesWithIntrinsicBounds(left, top, right, bottom);
        return this;
    }

    // 设置背景
    public CompressView setCompressBackGroud(int backGroudReg) {
        compressBackGroud = backGroudReg;
        textView.setBackgroundColor(backGroudReg);
        return this;
    }

    public CompressView setCompressBackGroud(String backGroudColor) {
        if (!TextUtils.isEmpty(backGroudColor))
            try {
                int color = Color.parseColor(backGroudColor);
                textView.setBackgroundColor(color);
            } catch (Exception e) {
                e.printStackTrace();
            }
        return this;
    }

    public CompressView setCompressBackGroud(Drawable backGroudDraw) {
        if (backGroudDraw != null)
            textView.setBackgroundDrawable(backGroudDraw);
        return this;
    }

    // 设置按钮是否可以点击事件
    public CompressView setCompressIsEnabled(boolean compressIsEnabled) {
        this.compressIsEnabled = compressIsEnabled;
        textView.setEnabled(compressIsEnabled);
        return this;
    }

    // 设置按钮显示文本
    public CompressView setCompressText(CharSequence compressText) {
        if (!TextUtils.isEmpty(compressText) && textView != null) {
            this.compressText = compressText;
            textView.setText(compressText);
        }
        return this;
    }

    // 设置按钮显示文本颜色
    public CompressView setCompressTextColor(int color) {
        if (color != 0 && textView != null) {
            this.compressTextColor = color;
            textView.setTextColor(color);
        }
        return this;
    }

    // 设置按钮显示文本大小
    public CompressView setCompressTextSize(float size) {
        if (size > 0 && textView != null) {
            this.compressTextSize = size;
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
        return this;
    }

    // 设置字体是否加粗
    public CompressView setCompressTextBold(boolean b) {
        if (textView != null) {
            this.compressTextBold = b;
            TextPaint paint = textView.getPaint();
            paint.setFakeBoldText(b);
        }
        return this;
    }

    // 设置字体显示位置
    public CompressView setCompressTextGravity(int gravity) {
        if (textView != null) {
            if (gravity == Gravity.CENTER
                    || gravity == Gravity.CENTER_VERTICAL
                    || gravity == Gravity.END
                    || gravity == Gravity.START
                    || gravity == Gravity.TOP
                    || gravity == Gravity.BOTTOM
                    || gravity == Gravity.CENTER_HORIZONTAL) {
                this.compressTextGravity = gravity;
                textView.setGravity(gravity);
            }
        }
        return this;
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if (textView != null)
            textView.setOnClickListener(l);
    }

    public void setOnCompressClickListener(CompressTV.OnCompressClickListener onCompressClickListener) {
        if (textView != null)
            textView.setOnCompressClickListener(onCompressClickListener);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
