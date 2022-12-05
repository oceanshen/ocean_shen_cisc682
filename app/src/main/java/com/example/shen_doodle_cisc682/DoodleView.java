package com.example.shen_doodle_cisc682;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DoodleView extends View {

    private Paint brush = new Paint();
    private Path path = new Path();

    private ArrayList<Path> paths = new ArrayList<>();
    private ArrayList<Paint> brushes = new ArrayList<>();

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(5f);
        paths.add(path);
        brushes.add(brush);
    }
    public DoodleView(Context context) {
        this(context, null);
    }

    public void changeColor(int color) {
        float currWidth = brush.getStrokeWidth();
        brush = new Paint();
        brush.setAntiAlias(true);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(currWidth);
        if (brush.getColor() == Color.BLUE) {
            brush.setColor(Color.RED);
        }
        if (brush.getColor() == Color.RED) {
            brush.setColor(Color.BLUE);
        }
        // brush.setColor(color);
        brushes.add(brush);
        path = new Path();
        paths.add(path);
        invalidate();
    }
    public int getCurrColor() {
        return brush.getColor();
    }

    public void changeWidth(int width) {
        brush = new Paint();
        brush.setAntiAlias(true);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        int currColor = brush.getColor();
        brush.setColor(currColor);
        int newWidth = 5 + width;
        brush.setStrokeWidth(newWidth);
        brushes.add(brush);
        path = new Path();
        paths.add(path);
        invalidate();
    }

    public void clear() {
        path = new Path();
        paths.clear();
        brushes.clear();
        paths.add(path);
        brushes.add(brush);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            path.moveTo(pointX, pointY);
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            path.lineTo(pointX, pointY);
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++) {
            path = paths.get(i);
            brush = brushes.get(i);
            canvas.drawPath(path, brush);
        }
    }
}

// heres a comment
