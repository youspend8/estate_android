package kr.co.estate.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import kr.co.estate.R;

public class Divide extends View {
    public Divide(Context context) {
        super(context);
        setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                3
        ));
        setBackgroundColor(getResources().getColor(R.color.colorGrey));
    }

    public Divide(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Divide(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
