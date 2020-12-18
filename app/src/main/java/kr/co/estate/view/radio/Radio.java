package kr.co.estate.view.radio;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import kr.co.estate.R;

public class Radio extends LinearLayout {
    private int selectedIndex = 0;
    private List<RadioTextButton> buttonList = new ArrayList<>();
    private List<RadioChangeEvent> changeListeners = new ArrayList<>();

    public Radio(Context context) {
        super(context);
    }

    public Radio(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Radio(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public <T> void addOnChangeListener(RadioChangeEvent<T> event) {
        changeListeners.add(event);
    }

    public <T> void setList(List<RadioItemAttributes<T>> attributes) {
        for (RadioItemAttributes<T> radioItemAttributes : attributes) {
            RadioTextButton<T> radioTextButton = new RadioTextButton<>(this.getContext());
            radioTextButton.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            radioTextButton.setAttributes(radioItemAttributes);
            radioTextButton.setText(radioItemAttributes.getText());
            radioTextButton.setTextSize(12);
            radioTextButton.setGravity(Gravity.CENTER);
            radioTextButton.setOnClickListener(e -> {
                selectedIndex = buttonList.indexOf(radioTextButton);
                onChange();
            });
            buttonList.add(radioTextButton);
            addView(radioTextButton);

            if (attributes.indexOf(radioItemAttributes) - 1 < attributes.size()) {
                View view = new View(this.getContext());
                view.setLayoutParams(new LayoutParams(15, 0));
                addView(view);
            }
        }
        setSelectEffect(buttonList.get(selectedIndex));
    }

    private void onChange() {
        clearSelectEffect();

        for (RadioChangeEvent event : changeListeners) {
            RadioTextButton selectedButton = buttonList.get(selectedIndex);
            event.onChange(selectedButton);
            setSelectEffect(selectedButton);
        }
    }

    private void clearSelectEffect() {
        for (RadioTextButton radioTextButton : buttonList) {
            radioTextButton.setTypeface(Typeface.DEFAULT);
            radioTextButton.setTextColor(this.getContext().getResources().getColor(R.color.colorGreyText));
        }
    }

    private void setSelectEffect(RadioTextButton radioTextButton) {
        radioTextButton.setTypeface(Typeface.DEFAULT_BOLD);
        radioTextButton.setTextColor(Color.BLACK);
    }

    @FunctionalInterface
    public interface RadioChangeEvent<T> {
        void onChange(RadioTextButton<T> radioTextButton);
    }
}
