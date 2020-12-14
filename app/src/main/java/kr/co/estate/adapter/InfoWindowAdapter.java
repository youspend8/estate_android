package kr.co.estate.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.naver.maps.map.MapFragment;
import com.naver.maps.map.overlay.InfoWindow;

import kr.co.estate.R;
import kr.co.estate.dto.TradeAggsDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class InfoWindowAdapter extends InfoWindow.ViewAdapter {
    private final TradeAggsDto tradeAggsDto;
    private final MapFragment mapFragment;

    @NonNull
    @Override
    public View getView(@NonNull InfoWindow infoWindow) {
        return View.inflate(mapFragment.getContext(), R.layout.info_window_view, null);
    }
}
