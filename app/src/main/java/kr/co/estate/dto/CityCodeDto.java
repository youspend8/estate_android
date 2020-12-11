package kr.co.estate.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CityCodeDto {
    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("name")
    private String name;

    @SerializedName("region")
    private String region;

    @SerializedName("sigungu")
    private String sigungu;

    @SerializedName("umd")
    private String umd;

    @SerializedName("fullname")
    private String fullname;

    @SerializedName("coordinate")
    private CoordinateDto coordinate;
}
