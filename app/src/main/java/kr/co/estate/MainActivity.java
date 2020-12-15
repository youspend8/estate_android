package kr.co.estate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import kr.co.estate.fragment.NaverMapFragment;

public class MainActivity extends FragmentActivity {
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        NaverMapFragment mapFragment = (NaverMapFragment) fragmentManager.findFragmentById(R.id.map);
        mapFragment.init(this);

        /**
         * GPS 권한 요청
         */
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck == PackageManager.PERMISSION_DENIED) {
            Toast.makeText(this, "편리한 서비스 이용을 위해 GPS 권한을 허용해주세요.", Toast.LENGTH_LONG).show();
        }
    }
}
