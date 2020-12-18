package kr.co.estate.client;

import java.util.List;

import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.TradeAggsDto;
import kr.co.estate.dto.TradeSearchDto;
import kr.co.estate.dto.TradeStatsDto;
import kr.co.estate.dto.trade.TradeStatsPeriodDto;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApiSpecification {
    @GET("/estate/api/v1/trade/aggregate?")
    Call<ApiResponse<List<TradeAggsDto>>> tradeAggregate(@Query("lat") double lat,
                                                         @Query("lon") double lon,
                                                         @Query("northLat") double northLat,
                                                         @Query("eastLong") double eastLong,
                                                         @Query("southLat") double southLat,
                                                         @Query("westLong") double westLong,
                                                         @Query("zoom") int zoom,
                                                         @Query("tradeType") String tradeType);

    @GET("/estate/api/v1/trade/search?")
    Call<ApiResponse<List<TradeSearchDto>>> tradeSearch(@Query("name") String name,
                                                        @Query("region") String region,
                                                        @Query("sigungu") String sigungu,
                                                        @Query("dong") String dong,
                                                        @Query("cityType") int cityType,
                                                        @Query("page") int page,
                                                        @Query("size") int size,
                                                        @Query("sortType") String sortType,
                                                        @Query("sortMode") String sortMode,
                                                        @Query("tradeType") String tradeType,
                                                        @Query("fromDate") String fromDate,
                                                        @Query("toDate") String toDate);

    @GET("/estate/api/v1/trade/stats?")
    Call<ApiResponse<TradeStatsDto>> tradeStats(@Query("name") String name,
                                                @Query("region") String region,
                                                @Query("sigungu") String sigungu,
                                                @Query("dong") String dong,
                                                @Query("cityType") int cityType,
                                                @Query("tradeType") String tradeType,
                                                @Query("fromDate") String fromDate,
                                                @Query("toDate") String toDate);

    @GET("/estate/api/v1/trade/stats/period?")
    Call<ApiResponse<TradeStatsPeriodDto>> tradeStatsPeriod(@Query("name") String name,
                                                            @Query("region") String region,
                                                            @Query("sigungu") String sigungu,
                                                            @Query("dong") String dong,
                                                            @Query("cityType") int cityType,
                                                            @Query("tradeType") String tradeType,
                                                            @Query("fromDate") String fromDate,
                                                            @Query("toDate") String toDate);
}
