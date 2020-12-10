package kr.co.estate.dto;

import com.google.gson.annotations.SerializedName;

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

    @Override
    public String toString() {
        return "CityCodeDto{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", region='" + region + '\'' +
                ", sigungu='" + sigungu + '\'' +
                ", umd='" + umd + '\'' +
                ", fullname='" + fullname + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSigungu() {
        return sigungu;
    }

    public void setSigungu(String sigungu) {
        this.sigungu = sigungu;
    }

    public String getUmd() {
        return umd;
    }

    public void setUmd(String umd) {
        this.umd = umd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
