package kr.co.estate.dto;

import com.google.gson.annotations.SerializedName;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.overlay.Marker;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CoordinateDto {
    @SerializedName("longitude")
    private double longitude;

    @SerializedName("latitude")
    private double latitude;

    public static CoordinateDto of(double longitude, double latitude) {
        return new CoordinateDto(longitude, latitude);
    }

    public LatLng asLatLng() {
        return new LatLng(latitude, longitude);
    }

    public Marker asMarker() {
        Marker marker = new Marker();
        marker.setPosition(asLatLng());
        return marker;
    }
}
