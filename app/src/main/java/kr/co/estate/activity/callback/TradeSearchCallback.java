package kr.co.estate.activity.callback;

import android.widget.TableRow;

import java.util.List;

import kr.co.estate.activity.InfoActivity;
import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.TradeSearchDto;
import kr.co.estate.view.Divide;
import kr.co.estate.view.TableBodyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeSearchCallback extends InfoActivityRetrofitCallback implements Callback<ApiResponse<List<TradeSearchDto>>> {

    public TradeSearchCallback(InfoActivity infoActivity) {
        super(infoActivity);
    }

    @Override
    public void onResponse(Call<ApiResponse<List<TradeSearchDto>>> call, Response<ApiResponse<List<TradeSearchDto>>> response) {
        System.out.println("response.body() :: " + response.body());

        ApiResponse<List<TradeSearchDto>> responseBody = response.body();

        if (responseBody == null || responseBody.isDataEmpty()) {
            return;
        }

        List<TradeSearchDto> responseData = responseBody.getData();

        for (TradeSearchDto tradeSearchDto : responseData) {
            getSearchListTable().addView(new Divide(getContext()));

            int price = Double.valueOf(tradeSearchDto.getAmount()).intValue();

            TableRow tableRow = new TableRow(getContext());
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getName()));
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getDeal().getDealDate()));
            if (price >= 10000) {
                tableRow.addView(TableBodyTextView.of(getContext(), String.format("%.1f억", (tradeSearchDto.getAmount() / (double) 10000))));
            } else {
                tableRow.addView(TableBodyTextView.of(getContext(), String.format("%.1f억", (tradeSearchDto.getAmount() / (double) 1000))));
            }
//            tableRow.addView(TableBodyTextView.of(getContext(), ""));
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getBuildYear()));
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getArea()));
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getFloor()));
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getLocation().getSigungu()));
            tableRow.addView(TableBodyTextView.of(getContext(), tradeSearchDto.getLocation().getDong()));

            getSearchListTable().addView(tableRow);
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<List<TradeSearchDto>>> call, Throwable t) {

    }
}
