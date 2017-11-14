package com.lupw.tablelayout.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import com.lupw.tablelayout.OnTabSelectedListener;
import com.lupw.tablelayout.R;
import com.lupw.tablelayout.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lupengwei on 2017/7/26.
 * Admin Lupw
 *
 * 自定义的TableLayout，顶部导航栏
 */

public class TableView extends View {
    private Context context;
    private Paint paint;
    private Paint textPaint;
    private List<Rect> tabRects;
    private List<Integer> numList;
    private List<String> dataList;
    private int indLineWidth;        // 底部直线指示器的长度，二分之一的tabRect的宽度
    private int currentX, currentY;  // 底部直线指示器的中心点
    private OnTabSelectedListener listener;


    public TableView(Context context) {
        super(context);
        init(context);
    }


    public TableView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public TableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context) {
        this.context = context;

        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);  // 填充并且描边
        paint.setStrokeWidth(DensityUtil.dp2px(context, 5f));
        paint.setFilterBitmap(true);                  // 防止绘制Bitmap时出现锯齿

        textPaint = new Paint();
        textPaint.setColor(context.getResources().getColor(R.color.colorGreyFont));
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(DensityUtil.dp2px(context, 14f));
        textPaint.setTextAlign(Paint.Align.CENTER);

        dataList = new ArrayList<>();

        // 自定义的View如果想捕捉到move和up事件需要设置为true
        setClickable(true);
    }


    /**
     * 由于这个控件要被包含在ScrollView中使用，需要重写这个方法，否则不会显示
     *
     * @param widthMeasureSpec  widthMeasureSpec
     * @param heightMeasureSpec heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureDimension(DensityUtil.dp2px(context, 80) * dataList.size(), widthMeasureSpec);
        int height = measureDimension(DensityUtil.dp2px(context, 40), heightMeasureSpec);
        setMeasuredDimension(width, height);
    }


    /**
     * 计算View的大小
     *
     * @param defaultSize 默认大小
     * @param measureSpec measureSpec
     * @return            View的大小
     */
    private int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            result = Math.min(defaultSize, specSize);
        } else {
            result = defaultSize;
        }
        return result;
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int tabWidth;
        int size = dataList.size();
        tabRects = new ArrayList<>();
        if (size > 5) tabWidth = DensityUtil.dp2px(context, 80);
        else tabWidth = w / size;
        for (int i = 0; i < size; i++) {
            tabRects.add(new Rect(tabWidth * i, 0, tabWidth * (i + 1), h));
        }

        Rect rect = tabRects.get(0);
        indLineWidth = rect.width() / 2;
        currentX = rect.width() / 2;
        currentY = rect.height();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;        // 为基线到字体上边框的距离
        float bottom = fontMetrics.bottom;  // 为基线到字体下边框的距离
        int baseLineY = (int) (tabRects.get(0).centerY() - top/2 - bottom/2);  // 基线中间点的y轴计算公式

        // 画文字和底部的线
        textPaint.setColor(context.getResources().getColor(R.color.colorGreyFont));
        textPaint.setTextSize(DensityUtil.dp2px(context, 14f));
        for (int i = 0; i < dataList.size(); i++) {
            Rect rect = tabRects.get(i);
            int startX = currentX - indLineWidth / 2;
            int endX = currentX + indLineWidth / 2;

            canvas.drawText(dataList.get(i), rect.centerX(), baseLineY, textPaint);
            canvas.drawText(dataList.get(i), rect.centerX(), baseLineY, textPaint);
            canvas.drawLine(startX, rect.height(), endX, rect.height(), paint);
        }

        // 显示移动的区域的内容
        textPaint.setColor(context.getResources().getColor(R.color.colorPrimary));
        canvas.clipRect(currentX - tabRects.get(0).width() / 2, 0, currentX + tabRects.get(0).width() / 2, currentY);
        for (int i = 0; i < dataList.size(); i++) {
            Rect rect = tabRects.get(i);
            int startX = currentX - indLineWidth / 2;
            int endX = currentX + indLineWidth / 2;

            canvas.drawText(dataList.get(i), rect.centerX(), baseLineY, textPaint);
            canvas.drawLine(startX, rect.height(), endX, rect.height(), paint);
        }

        canvas.restore();
    }


    /**
     * 设置指示器移动的位置
     *
     * @param positionOffset 当前移动的百分比
     * @param isLeft         是否是向左滑动
     */
    public void setValue(int positionTemp, float positionOffset, boolean isLeft) {
        if (tabRects == null) return;
        Rect rect = tabRects.get(positionTemp);
        if (isLeft) {
            currentX = (int) (rect.centerX() + positionOffset * rect.width());
        } else {
            currentX = (int) (rect.centerX() - positionOffset * rect.width());
        }
        invalidate();
    }



    public void setValue(int positionTemp) {
        currentX = tabRects.get(positionTemp).centerX();
        invalidate();
    }


    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                for (int i = 0; i < tabRects.size(); i++) {
                    if (tabRects.get(i).contains((int) x, (int) y)) {
                        if (listener != null) listener.tabSelected(i);
                        else setValue(i);
                        return true;
                    }
                }
                return false;
        }
        return super.onTouchEvent(event);
    }



    public void setListener(OnTabSelectedListener listener) {
        this.listener = listener;
    }


    public void setNumList(List<Integer> numList) {
        this.numList = numList;
        for (int i = 0; i < dataList.size(); i++) {
            if (numList.get(i) == 0) continue;
            dataList.set(i, dataList.get(i) + "(" + numList.get(i) + ")");
        }
        invalidate();
    }


    public void setTitles(List<String> dataList) {
        this.dataList = dataList;
        invalidate();
    }


    /**
     * 获取当前选中的tab的中心位置的X轴坐标
     *
     * @param positionTemp 当前选中的tab的position
     * @return             中心位置的X轴坐标
     */
    public int getCurrentX(int positionTemp) {
        return tabRects.get(positionTemp).centerX();
    }


    /**
     * 获取标题的数量
     *
     * @return 标题适量
     */
    public int getTitleSize() {
        return dataList.size();
    }
}
