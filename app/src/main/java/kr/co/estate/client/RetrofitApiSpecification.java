package kr.co.estate.client;

import java.util.HashMap;
import java.util.List;

import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.CityCodeDto;
import kr.co.estate.dto.TradeAggsDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiSpecification {
    @GET("/city/search?")
    Call<ApiResponse<List<CityCodeDto>>> searchCityCode(@Query("type") String type,
                                                        @Query("region") String region);

    @GET("/estate/api/v1/trade/aggregate?")
    Call<List<HashMap<String, Object>>> aggregationByCoords(@Query("lat") double lat,
                                                            @Query("lon") double lon);

    @GET("/estate/api/v1/city/search/coords?")
    Call<ApiResponse<List<CityCodeDto>>> searchCityCoords(@Query("lat") double lat,
                                                          @Query("lon") double lon,
                                                          @Query("northLat") double northLat,
                                                          @Query("eastLong") double eastLong,
                                                          @Query("southLat") double southLat,
                                                          @Query("westLong") double westLong,
                                                          @Query("zoom") int zoom);

    @GET("/estate/api/v1/trade/aggregate?")
    Call<ApiResponse<List<TradeAggsDto>>> tradeAggregate(@Query("lat") double lat,
                                                         @Query("lon") double lon,
                                                         @Query("northLat") double northLat,
                                                         @Query("eastLong") double eastLong,
                                                         @Query("southLat") double southLat,
                                                         @Query("westLong") double westLong,
                                                         @Query("zoom") int zoom);
}
