package kr.co.estate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

public class TableHeaderTextView extends AppCompatTextView {
    public TableHeaderTextView(Context context) {
        super(context);
    }

    public TableHeaderTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableHeaderTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setTextSize(TypedValue.COMPLEX_UNIT_PT, 5);
        setPadding(0, 10, 0, 10);
        setGravity(Gravity.CENTER);
        setHeight(100);
        setWidth(200);
        setMaxWidth(1000);

        super.onDraw(canvas);
    }
}
