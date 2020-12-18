package kr.co.estate.activity.callback;

import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.estate.R;
import kr.co.estate.activity.InfoActivity;
import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.TradeStatsDto;
import kr.co.estate.dto.embedded.stats.TradeStatsCityDto;
import kr.co.estate.view.CornerDivide;
import kr.co.estate.view.TableBodyCornerTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeStatsCityCallback extends InfoActivityRetrofitCallback implements Callback<ApiResponse<TradeStatsDto>> {

    public TradeStatsCityCallback(InfoActivity infoActivity) {
        super(infoActivity);
    }

    @Override
    public void onResponse(Call<ApiResponse<TradeStatsDto>> call, Response<ApiResponse<TradeStatsDto>> response) {
        Log.d("response", response.body() + "");

        ApiResponse<TradeStatsDto> responseBody = response.body();

        if (responseBody == null || responseBody.isDataEmpty()) {
            return;
        }

        TradeStatsDto responseData = responseBody.getData();

        TableLayout tradeStatsCityTable = getTradeStatsCityTable();

        getTradeAggregateCityChart().setDataList(responseData.getCityList());

        for (TradeStatsCityDto tradeStatsCityDto : responseData.getCityList()) {
            TableRow tableRow = new TableRow(getContext());
            tableRow.setBackground(getResources().getDrawable(R.drawable.corner_radius));
            tableRow.setClipToOutline(true);

            boolean isStripe = responseData.getCityList().indexOf(tradeStatsCityDto) % 2 == 0;

            tableRow.addView(TableBodyCornerTextView.of(getContext(), tradeStatsCityDto.getDong(), isStripe));
            tableRow.addView(TableBodyCornerTextView.of(getContext(), String.format(Locale.getDefault(), "%d만", (int) tradeStatsCityDto.getPrice()), isStripe));
            tableRow.addView(TableBodyCornerTextView.of(getContext(), String.format(Locale.getDefault(), "%d건", tradeStatsCityDto.getCount()), isStripe));

            tradeStatsCityTable.addView(tableRow);
            tradeStatsCityTable.addView(new CornerDivide(getContext()));
        }
    }

    @Override
    public void onFailure(Call<ApiResponse<TradeStatsDto>> call, Throwable t) {

    }
}
