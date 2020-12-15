package kr.co.estate.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CornerDivide extends View {
    public CornerDivide(Context context) {
        super(context);
        setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                15
        ));
    }

    public CornerDivide(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CornerDivide(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
