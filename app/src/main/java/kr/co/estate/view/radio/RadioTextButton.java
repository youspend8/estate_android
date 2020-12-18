package kr.co.estate.view.radio;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RadioTextButton<T> extends AppCompatTextView {
    private RadioItemAttributes<T> attributes;

    public RadioTextButton(Context context) {
        super(context);
    }

    public RadioTextButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RadioTextButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
