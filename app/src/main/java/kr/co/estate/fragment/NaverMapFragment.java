package kr.co.estate.fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.util.FusedLocationSource;

import java.util.List;

import kr.co.estate.MainActivity;
import kr.co.estate.client.RetrofitApiSpecification;
import kr.co.estate.client.RetrofitClient;
import kr.co.estate.collection.Markers;
import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.TradeAggsDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NaverMapFragment extends MapFragment implements OnMapReadyCallback {
    private MainActivity activity;
    private NaverMap naverMap;
    private RetrofitApiSpecification specification;
    private FusedLocationSource locationSource;
    private double prevZoomLevel;
    private Markers markers;

    private void requestCoords(NaverMap naverMap, double zoom) {
        LatLngBounds latLngBounds = naverMap.getContentBounds();

        Call<ApiResponse<List<TradeAggsDto>>> call = specification.tradeAggregate(
                  latLngBounds.getCenter().latitude
                , latLngBounds.getCenter().longitude
                , naverMap.getContentBounds().getNorthLatitude()
                , naverMap.getContentBounds().getEastLongitude()
                , naverMap.getContentBounds().getSouthLatitude()
                , naverMap.getContentBounds().getWestLongitude()
                , zoom == 0 ? 15 : (int) zoom
                , "TRADE");

        call.enqueue(new Callback<ApiResponse<List<TradeAggsDto>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<TradeAggsDto>>> call, Response<ApiResponse<List<TradeAggsDto>>> response) {
                Log.d("response.code()",response.code() + "");

                if (response.code() != 200) {
                    Toast.makeText(activity.getApplicationContext(), "데이터 요청에 실패했습니다.", Toast.LENGTH_LONG).show();
                    return;
                }

                ApiResponse<List<TradeAggsDto>> responseBody = response.body();
                if (responseBody == null || responseBody.isDataEmpty()) {
                    Toast.makeText(activity.getApplicationContext(), "데이터가 없습니다.", Toast.LENGTH_LONG).show();
                    return;
                }
                List<TradeAggsDto> list = responseBody.getData();

                Log.i("response data", list + "");

                markers.removeAllOnMap();
                markers.addMarkersFrom(list);
            }

            @Override
            public void onFailure(Call<ApiResponse<List<TradeAggsDto>>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void init(MainActivity mainActivity) {
        activity = mainActivity;
        getMapAsync(this::onMapReady);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        specification = RetrofitClient.getInstance().create(RetrofitApiSpecification.class);
        locationSource = new FusedLocationSource(this, MainActivity.LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        this.naverMap = naverMap;

        markers = new Markers(this, naverMap);

        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);
        naverMap.setLocationSource(locationSource);
        naverMap.getUiSettings().setZoomControlEnabled(true);
        naverMap.getUiSettings().setLocationButtonEnabled(true);
        naverMap.setMinZoom(6.0);
        naverMap.setMaxZoom(18.0);

        naverMap.addOnCameraIdleListener(() -> {
            System.out.println("map.getCameraPosition() ==> " + naverMap.getCameraPosition());

            double minus = naverMap.getCameraPosition().zoom - (int) naverMap.getCameraPosition().zoom;

            if (minus != 0) {
                double zoomLevel = naverMap.getCameraPosition().zoom;
                double currentZoomLevel = 0;
                if (prevZoomLevel < zoomLevel) {
                    currentZoomLevel = Math.ceil(zoomLevel);
                } else if (prevZoomLevel > zoomLevel) {
                    currentZoomLevel = Math.floor(zoomLevel);
                }

                CameraPosition cameraPosition = new CameraPosition(naverMap.getCameraPosition().target, currentZoomLevel);
                naverMap.setCameraPosition(cameraPosition);

                prevZoomLevel = currentZoomLevel;
            }
            requestCoords(naverMap, prevZoomLevel);
        });
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)) {
            if (!locationSource.isActivated()) {
                naverMap.setLocationTrackingMode(LocationTrackingMode.None);
            }
        }
    }
}
