package kr.co.estate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import kr.co.estate.R;

public class TableBodyTextView extends AppCompatTextView {
    public TableBodyTextView(Context context) {
        super(context);
    }

    public TableBodyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableBodyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setTextSize(TypedValue.COMPLEX_UNIT_PT, 6);
        setPadding(20, 10, 20, 10);
        setGravity(Gravity.CENTER);
        setHeight(85);
        setTextColor(Color.BLACK);

        super.onDraw(canvas);
    }

    public static TableBodyTextView of(Context context, Object text) {
        TableBodyTextView tableBodyTextView = new TableBodyTextView(context);
        tableBodyTextView.setText(String.valueOf(text));
        return tableBodyTextView;
    }
}
