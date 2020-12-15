package kr.co.estate.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import androidx.appcompat.widget.AppCompatTextView;

import kr.co.estate.R;

public class TableBodyCornerTextView extends AppCompatTextView {
    public TableBodyCornerTextView(Context context) {
        super(context);
    }

    public TableBodyCornerTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TableBodyCornerTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setTextSize(TypedValue.COMPLEX_UNIT_PT, 6);
        setPadding(0, 10, 0, 10);
        setGravity(Gravity.CENTER);
        setHeight(85);
        setTextColor(Color.BLACK);

        super.onDraw(canvas);
    }

    public static TableBodyCornerTextView of(Context context, Object text, boolean isStripe) {
        TableBodyCornerTextView tableBodyTextView = new TableBodyCornerTextView(context);
        tableBodyTextView.setText(String.valueOf(text));
        if (isStripe) {
            tableBodyTextView.setBackgroundColor(tableBodyTextView.getResources().getColor(R.color.colorWhiteGrey));
        }
        return tableBodyTextView;
    }
}
