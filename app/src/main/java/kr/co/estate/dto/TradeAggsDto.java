package kr.co.estate.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
public class TradeAggsDto implements Serializable {
    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private int type;

    @SerializedName("regionCode")
    private int regionCode;

    @SerializedName("sigunguCode")
    private int sigunguCode;

    @SerializedName("umdCode")
    private int umdCode;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("amountAverage")
    private double amountAverage;

    @SerializedName("count")
    private long count;

    @SerializedName("coordinate")
    private CoordinateDto coordinate;
}

