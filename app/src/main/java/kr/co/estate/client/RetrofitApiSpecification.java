package kr.co.estate.client;

import java.util.HashMap;
import java.util.List;

import kr.co.estate.dto.CityCodeDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiSpecification {
    @GET("/city/search?")
    Call<List<CityCodeDto>> searchCityCode(@Query("type") String type,
                                           @Query("region") String region);

    @GET("/trade/aggregate?")
    Call<List<HashMap<String, Object>>> aggregationByCoords(@Query("lat") double lat,
                                                            @Query("lon") double lon);

    @GET("/estate/city/search/coords?")
    Call<List<HashMap<String, Object>>> searchCityCoords(@Query("lat") double lat,
                                                         @Query("lon") double lon,
                                                         @Query("zoom") int zoom);
}
