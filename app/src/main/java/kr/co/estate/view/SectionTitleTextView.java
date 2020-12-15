package kr.co.estate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class SectionTitleTextView extends AppCompatTextView {
    public SectionTitleTextView(Context context) {
        super(context);
    }

    public SectionTitleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SectionTitleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setTextSize(16);
//        setTextColor(Color.BLACK);
        setPadding(10, 30, 10, 30);
        super.onDraw(canvas);
    }
}
