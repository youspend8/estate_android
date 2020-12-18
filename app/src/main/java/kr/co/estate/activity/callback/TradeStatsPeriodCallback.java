package kr.co.estate.activity.callback;

import kr.co.estate.activity.InfoActivity;
import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.trade.TradeStatsPeriodDto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeStatsPeriodCallback extends InfoActivityRetrofitCallback implements Callback<ApiResponse<TradeStatsPeriodDto>> {
    public TradeStatsPeriodCallback(InfoActivity infoActivity) {
        super(infoActivity);
    }

    @Override
    public void onResponse(Call<ApiResponse<TradeStatsPeriodDto>> call, Response<ApiResponse<TradeStatsPeriodDto>> response) {
        System.out.println("response.body() :: " + response.body());

        getTradeAggregatePeriodChart().setDataList(response.body().getData().getPeriodList());
    }

    @Override
    public void onFailure(Call<ApiResponse<TradeStatsPeriodDto>> call, Throwable t) {
        System.out.println("Throwable :: " + t);

    }
}
