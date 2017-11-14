package com.lupw.tablelayout.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.lupw.tablelayout.R;
import com.lupw.tablelayout.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lupengwei on 2017/7/21.
 * Admin Lupw
 *
 * 自定义的圆形指示器
 */

public class DotView extends View {
    private Context context;
    private Paint paint;
    private List<Rect> subDotRects;
    private int subDotCount;
    private float r1, r2;  // r1-底部圆点，r2-滑动的圆点
    private int currentX, currentY;

    public DotView(Context context) {
        super(context);
        init(context);
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int subDotRectWidth = w / subDotCount;

        subDotRects = new ArrayList<>();
        int subDotStartX, subDotStartY, subDotEndX, subDotEndY;
        for (int i = 0; i < subDotCount; i++) {
            subDotStartX = subDotRectWidth * i;
            subDotStartY = 0;
            subDotEndX = subDotRectWidth * (i + 1);
            subDotEndY = h;
            subDotRects.add(new Rect(subDotStartX, subDotStartY, subDotEndX, subDotEndY));
        }

        currentX = subDotRects.get(0).centerX();
        currentY = subDotRects.get(0).centerY();
    }


    /**
     * 初始化
     *
     * @param context 上下文
     */
    private void init(Context context) {
        this.context = context;

        subDotCount = 6;
        r1 = 1.5f;
        r2 = 3f;

        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.colorGreyBg));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);  // 填充并且描边
        paint.setStrokeWidth(DensityUtil.dp2px(context, 1.5f));
        paint.setFilterBitmap(true);                  // 防止绘制Bitmap时出现锯齿
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        // 底部圆
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        for (int i = 0; i < subDotRects.size(); i++) {
            Rect rect = subDotRects.get(i);
            canvas.drawCircle(rect.centerX(), rect.centerY(), DensityUtil.dp2px(context, r1), paint);
        }

        // 可移动的圆
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        canvas.drawCircle(currentX, currentY,
                DensityUtil.dp2px(context, r2), paint);

        canvas.restore();
    }


    /**
     * 设置指示器移动的位置
     *
     * @param positionOffset 当前移动的百分比
     * @param isLeft         是否是向左滑动
     */
    public void setValue(int positionTemp, float positionOffset, boolean isLeft) {
        Rect rect = subDotRects.get(positionTemp);
        if (isLeft) {
            currentX = (int) (rect.centerX() + positionOffset * rect.width());
        } else {
            currentX = (int) (rect.centerX() - positionOffset * rect.width());
        }
        invalidate();
    }
}
