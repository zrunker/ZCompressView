package cc.ibooker.zcompressviewlib;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
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
    private CompressTV textView;

    public int getDefaultBg() {
        return defaultBg;
    }

    public void setDefaultBg(int defaultBg) {
        this.defaultBg = defaultBg;
    }

    public int getCompressBg() {
        return compressBg;
    }

    public void setCompressBg(int compressBg) {
        this.compressBg = compressBg;
    }

    public int getCompressWidth() {
        return compressWidth;
    }

    public void setCompressWidth(int compressWidth) {
        this.compressWidth = compressWidth;
    }

    public int getCompressHeight() {
        return compressHeight;
    }

    public void setCompressHeight(int compressHeight) {
        this.compressHeight = compressHeight;
    }

    public int getCompressDis() {
        return compressDis;
    }

    public void setCompressDis(int compressDis) {
        this.compressDis = compressDis;
    }

    public int getCompressDuration() {
        return compressDuration;
    }

    public void setCompressDuration(int compressDuration) {
        this.compressDuration = compressDuration;
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
            compressWidth = typeArray.getInt(R.styleable.CompressView_compressWidth, 70) * 2;
            compressHeight = typeArray.getInt(R.styleable.CompressView_compressHeight, 27) * 2;
            compressDis = typeArray.getInt(R.styleable.CompressView_compressDis, 10) * 2;
            compressDuration = typeArray.getInt(R.styleable.CompressView_compressDuration, 100);
            compressText = typeArray.getString(R.styleable.CompressView_compressText);
            compressTextColor = typeArray.getInt(R.styleable.CompressView_compressTextColor, Color.WHITE);
            compressTextSize = typeArray.getFloat(R.styleable.CompressView_compressTextSize, 16);
            compressTextBold = typeArray.getBoolean(R.styleable.CompressView_compressTextBold, false);
            compressTextGravity = typeArray.getInt(R.styleable.CompressView_compressTextGravity, Gravity.CENTER);
            typeArray.recycle();
        }

        LayoutParams layoutParams1 = new LayoutParams(compressWidth, compressHeight, Gravity.BOTTOM);
        layoutParams1.topMargin = compressDis;
        View view = new View(context);
        view.setBackgroundResource(defaultBg);
        view.setLayoutParams(layoutParams1);
        addView(view);

        LayoutParams layoutParams2 = new LayoutParams(compressWidth, compressHeight, Gravity.TOP);
        textView = new CompressTV(context, compressDis, compressDuration);
        textView.setBackgroundResource(compressBg);
        textView.setLayoutParams(layoutParams2);
        addView(textView);

        setCompressText(compressText);
        setCompressTextColor(compressTextColor);
        setCompressTextSize(compressTextSize);
        setCompressTextBold(compressTextBold);
        setCompressTextGravity(compressTextGravity);
    }

    // 设置按钮显示文本
    public void setCompressText(CharSequence compressText) {
        if (!TextUtils.isEmpty(compressText) && textView != null) {
            this.compressText = compressText;
            textView.setText(compressText);
        }
    }

    // 设置按钮显示文本颜色
    public void setCompressTextColor(int color) {
        if (color != 0 && textView != null) {
            this.compressTextColor = color;
            textView.setTextColor(color);
        }
    }

    // 设置按钮显示文本大小
    public void setCompressTextSize(float size) {
        if (size > 0 && textView != null) {
            this.compressTextSize = size;
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
        }
    }

    // 设置字体是否加粗
    public void setCompressTextBold(boolean b) {
        if (textView != null) {
            this.compressTextBold = b;
            TextPaint paint = textView.getPaint();
            paint.setFakeBoldText(b);
        }
    }

    // 设置字体显示位置
    public void setCompressTextGravity(int gravity) {
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
}
