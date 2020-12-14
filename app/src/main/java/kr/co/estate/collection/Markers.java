package kr.co.estate.collection;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;
import java.util.List;

import kr.co.estate.adapter.InfoWindowAdapter;
import kr.co.estate.dto.CoordinateDto;
import kr.co.estate.dto.TradeAggsDto;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Markers {
    private MapFragment mapFragment;
    private List<Marker> markerList;
    private List<InfoWindow> infoWindowList;
    private NaverMap naverMap;

    public Markers(MapFragment mapFragment, NaverMap naverMap) {
        this.markerList = new ArrayList<>();
        this.infoWindowList = new ArrayList<>();
        this.mapFragment = mapFragment;
        this.naverMap = naverMap;
    }

    public void setMarkersFrom(List<TradeAggsDto> list) {
        for (TradeAggsDto tradeAggsDto : list) {
            CoordinateDto coordinate = tradeAggsDto.getCoordinate();

            InfoWindow infoWindow = new InfoWindow();
            infoWindow.setAdapter(new InfoWindowAdapter(tradeAggsDto, mapFragment));
            infoWindow.setPosition(coordinate.asLatLng());
            infoWindow.open(naverMap);
            infoWindowList.add(infoWindow);
        }
    }

    public void removeAllOnMap() {
        for (Marker marker : markerList) {
            marker.setMap(null);
        }
        for (InfoWindow infoWindow : infoWindowList) {
            infoWindow.setMap(null);
        }
        markerList.clear();
        infoWindowList.clear();
    }

    public void addAllOnMap(NaverMap naverMap) {
        for (Marker marker : markerList) {
            marker.setHideCollidedSymbols(true);
            marker.setHideCollidedMarkers(true);
            marker.setMap(naverMap);
        }
    }
}
