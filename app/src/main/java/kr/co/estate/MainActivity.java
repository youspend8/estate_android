package kr.co.estate;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.geometry.LatLngBounds;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.co.estate.client.RetrofitApiSpecification;
import kr.co.estate.client.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity  extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        mapFragment.getMapAsync(naverMap -> {
            naverMap.setMinZoom(6.0);
            naverMap.setMaxZoom(18.0);

            naverMap.addOnLocationChangeListener(x -> {
                System.out.println("addOnLocationChangeListener ==> " + x);
            });

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

                    requestCoords(naverMap, currentZoomLevel);

                    prevZoomLevel = zoomLevel;
                }

//                request(naverMap);
                Toast.makeText(this.getApplicationContext(), "changed", Toast.LENGTH_SHORT).show();
            });

            naverMap.setOnMapClickListener((point, coord) -> {
                System.out.println("setOnMapClickListener.point ==> " + point);
                System.out.println("setOnMapClickListener.coord ==> " + coord);
            });
        });

    }

    private void requestCoords(NaverMap naverMap, double zoom) {
        specification = RetrofitClient.getInstance().create(RetrofitApiSpecification.class);

        LatLngBounds latLngBounds = naverMap.getContentBounds();

        Call<List<HashMap<String, Object>>> call = specification.searchCityCoords(latLngBounds.getCenter().latitude, latLngBounds.getCenter().longitude, (int) zoom);

        call.enqueue(new Callback<List<HashMap<String, Object>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, Object>>> call, Response<List<HashMap<String, Object>>> response) {
                Log.d("response.code()",response.code() + "");

                List<HashMap<String, Object>> result = response.body();

                Log.i("result", result + "");

                removeMarkerList();

                for (HashMap<String, Object> item : result) {
                    Map<String, Object> coords = (Map<String, Object>) item.get("coordinate");

                    Marker marker = new Marker();
                    marker.setPosition(new LatLng((double) coords.get("latitude"), (double) coords.get("longitude")));
                    markerList.add(marker);
                }

                addMarkerList(naverMap);
            }

            @Override
            public void onFailure(Call<List<HashMap<String, Object>>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private double prevZoomLevel;

    private RetrofitApiSpecification specification;

    private List<Marker> markerList = new ArrayList<>();

    private void request(NaverMap naverMap) {
        specification = RetrofitClient.getInstance().create(RetrofitApiSpecification.class);

        LatLngBounds latLngBounds = naverMap.getContentBounds();

        Call<List<HashMap<String, Object>>> call = specification.aggregationByCoords(latLngBounds.getCenter().latitude, latLngBounds.getCenter().longitude);

        call.enqueue(new Callback<List<HashMap<String, Object>>>() {
            @Override
            public void onResponse(Call<List<HashMap<String, Object>>> call, Response<List<HashMap<String, Object>>> response) {
                Log.d("response.code()",response.code() + "");

                List<HashMap<String, Object>> result = response.body();

                Log.i("result", result + "");

                removeMarkerList();

                for (HashMap<String, Object> item : result) {
                    Marker marker = new Marker();
                    marker.setPosition(new LatLng((double) item.get("latitude"), (double) item.get("longitude")));
                    markerList.add(marker);
                }

//                addMarkerList(naverMap);
            }

            @Override
            public void onFailure(Call<List<HashMap<String, Object>>> call, Throwable t) {
                call.cancel();
            }
        });
    }

    private void removeMarkerList() {
        for (Marker marker : markerList) {
            marker.setMap(null);
        }
        markerList.clear();
    }

    private void addMarkerList(NaverMap naverMap) {
        for (Marker marker : markerList) {
            marker.setMap(naverMap);
        }
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

    }
}
