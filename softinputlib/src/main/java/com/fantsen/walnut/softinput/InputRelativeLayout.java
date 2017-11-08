package com.fantsen.walnut.softinput;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

/**
 * Author: zyl
 * Create time: 2017/11/8
 */

public class InputRelativeLayout extends RelativeLayout {
    private int originalHeight = 0;
    private int lastHeight = 0;
    private View moveView;

    public InputRelativeLayout(Context context) {
        super(context);
    }

    public InputRelativeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InputRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (0 != originalHeight) {
            int height = MeasureSpec.getSize(heightMeasureSpec);
            if (originalHeight / 4 < Math.abs(lastHeight - height)) {
                onLayoutMoveEvent(lastHeight > height, Math.abs(lastHeight - height));
            }
            lastHeight = height;
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(originalHeight, MeasureSpec.getMode(heightMeasureSpec));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setMoveView(View v) {
        moveView = v;
    }

    private void onLayoutMoveEvent(boolean isUp, int moveDistance) {
        if (null == moveView) {
            return;
        }
        ValueAnimator anim;
        float startY;
        float endY;
        long duration;
        TimeInterpolator interpolator;
        if (isUp) {
            startY = -moveDistance / 3;
            endY = -moveDistance;
            duration = 200;
            interpolator = new DecelerateInterpolator(1.0f);
        } else {
            startY = moveView.getTranslationY();
            endY = 0;
            duration = 350;
            interpolator = new DecelerateInterpolator(1.0f);
        }
        anim = ValueAnimator
                .ofFloat(startY, endY)
                .setDuration(duration);
        anim.setInterpolator(interpolator);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                moveView.setTranslationY(cVal);
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (0 == originalHeight) {
            originalHeight = getHeight();
            lastHeight = originalHeight;
        }
    }
}
