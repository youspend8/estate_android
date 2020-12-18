package kr.co.estate.view.radio;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class RadioItemAttributes<T> {
    private String text;
    private String name;
    private T value;

    public static <T> RadioItemAttributes<T> of(String name, T value) {
        return new RadioItemAttributes<>("", name, value);
    }

    public static <T> RadioItemAttributes<T> of(String text, String name, T value) {
        return new RadioItemAttributes<>(text, name, value);
    }
}
