package kr.co.estate.collection;

import android.content.Intent;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.overlay.InfoWindow;

import java.util.ArrayList;
import java.util.List;

import kr.co.estate.InfoActivity;
import kr.co.estate.adapter.InfoWindowAdapter;
import kr.co.estate.dto.CoordinateDto;
import kr.co.estate.dto.TradeAggsDto;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Markers {
    private MapFragment mapFragment;
    private List<InfoWindow> infoWindowList;
    private NaverMap naverMap;

    public Markers(MapFragment mapFragment, NaverMap naverMap) {
        this.infoWindowList = new ArrayList<>();
        this.mapFragment = mapFragment;
        this.naverMap = naverMap;
    }

    public void addMarkersFrom(List<TradeAggsDto> list) {
        for (TradeAggsDto tradeAggsDto : list) {
            CoordinateDto coordinate = tradeAggsDto.getCoordinate();

            InfoWindow infoWindow = new InfoWindow();
            infoWindow.setAdapter(new InfoWindowAdapter(tradeAggsDto, mapFragment));
            infoWindow.setPosition(coordinate.asLatLng());
            infoWindow.setAlpha(0.75f);
            infoWindow.open(naverMap);
            infoWindow.setOnClickListener(e -> {
                Intent intent = new Intent(this.mapFragment.getContext(), InfoActivity.class);
                intent.putExtra("tradeAggsDto", tradeAggsDto);

                this.mapFragment.startActivity(intent);
                return true;
            });
            infoWindowList.add(infoWindow);
        }
    }

    public void removeAllOnMap() {
        for (InfoWindow infoWindow : infoWindowList) {
            infoWindow.setMap(null);
        }
        infoWindowList.clear();
    }
}
