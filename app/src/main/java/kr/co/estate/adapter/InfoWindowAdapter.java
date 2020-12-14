package kr.co.estate.adapter;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        LinearLayout linearLayout = (LinearLayout) View.inflate(mapFragment.getContext(), R.layout.info_window_view, null);

        TextView city = linearLayout.findViewById(R.id.city);
        city.setText(tradeAggsDto.getName());

        TextView textViewPrice = linearLayout.findViewById(R.id.price);

        int price = Double.valueOf(tradeAggsDto.getAmountAverage()).intValue();
        if (price >= 10000) {
            textViewPrice.setText(String.format("%.1f억", (price / (double) 10000)));
        }

        TextView count = linearLayout.findViewById(R.id.count);
        count.setText(String.format("%s건", tradeAggsDto.getCount()));

        return linearLayout;
    }
}
