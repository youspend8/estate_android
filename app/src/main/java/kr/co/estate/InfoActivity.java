package kr.co.estate;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import kr.co.estate.client.RetrofitApiSpecification;
import kr.co.estate.client.RetrofitSpecificationFactory;
import kr.co.estate.dto.ApiResponse;
import kr.co.estate.dto.TradeAggsDto;
import kr.co.estate.dto.TradeSearchDto;
import kr.co.estate.dto.TradeStatsDto;
import kr.co.estate.dto.embedded.stats.TradeStatsCityDto;
import kr.co.estate.view.CornerDivide;
import kr.co.estate.view.Divide;
import kr.co.estate.view.TableBodyCornerTextView;
import kr.co.estate.view.TableBodyTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InfoActivity extends AppCompatActivity {
    private RetrofitApiSpecification retrofitApiSpecification;
    private TableLayout searchListTable;
    private TableLayout aggregationTable;
    private BarChart cityChart;
    private Button moreBtn;
    private int page;

    private Callback<ApiResponse<List<TradeSearchDto>>> callback = new Callback<ApiResponse<List<TradeSearchDto>>>() {
        @Override
        public void onResponse(Call<ApiResponse<List<TradeSearchDto>>> call, Response<ApiResponse<List<TradeSearchDto>>> response) {
            System.out.println("response.body() :: " + response.body());

            ApiResponse<List<TradeSearchDto>> responseBody = response.body();

            if (responseBody == null || responseBody.isDataEmpty()) {
                return;
            }

            List<TradeSearchDto> responseData = responseBody.getData();

            for (TradeSearchDto tradeSearchDto : responseData) {
                searchListTable.addView(new Divide(getApplicationContext()));

                int price = Double.valueOf(tradeSearchDto.getAmount()).intValue();

                TableRow tableRow = new TableRow(getApplicationContext());
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getName()));
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getDeal().getDealDate()));
                if (price >= 10000) {
                    tableRow.addView(TableBodyTextView.of(getApplicationContext(), String.format("%.1f억", (tradeSearchDto.getAmount() / (double) 10000))));
                } else {
                    tableRow.addView(TableBodyTextView.of(getApplicationContext(), String.format("%.1f억", (tradeSearchDto.getAmount() / (double) 1000))));
                }
//                    tableRow.addView(TableBodyTextView.of(getApplicationContext(), ""));
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getBuildYear()));
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getArea()));
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getFloor()));
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getLocation().getSigungu()));
                tableRow.addView(TableBodyTextView.of(getApplicationContext(), tradeSearchDto.getLocation().getDong()));

                searchListTable.addView(tableRow);
            }
        }

        @Override
        public void onFailure(Call<ApiResponse<List<TradeSearchDto>>> call, Throwable t) {

        }
    };

    private void fetchTradeSearch(TradeAggsDto tradeAggsDto) {
        retrofitApiSpecification.tradeSearch(
                tradeAggsDto.getName(),
                tradeAggsDto.getRegionCode() + "",
                tradeAggsDto.getSigunguCode() + "",
                page++,
                10,
                "amount",
                "desc",
                "TRADE")
                .enqueue(callback);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        retrofitApiSpecification = RetrofitSpecificationFactory.getApiSpecification();

        searchListTable = findViewById(R.id.searchListTable);
        aggregationTable = findViewById(R.id.aggregationTable);
        moreBtn = findViewById(R.id.moreBtn);
        cityChart = findViewById(R.id.cityChart);

        cityChart.getXAxis().setDrawGridLines(false);
        cityChart.setPinchZoom(false);
        cityChart.setDrawBarShadow(false);
        cityChart.setDrawGridBackground(false);
        cityChart.getDescription().setEnabled(false);

        XAxis xAxis = cityChart.getXAxis();
        xAxis.setDrawGridLines(false);
        cityChart.getAxisLeft().setDrawGridLines(false);
        cityChart.getAxisRight().setDrawGridLines(false);
        cityChart.getAxisRight().setEnabled(false);
        cityChart.getAxisLeft().setEnabled(true);
        cityChart.getXAxis().setDrawGridLines(false);
        cityChart.animateY(1500);
        cityChart.getLegend().setEnabled(false);

        cityChart.getAxisRight().setDrawLabels(false);
        cityChart.getAxisLeft().setDrawLabels(true);
//        cityChart.setTouchEnabled(false);
        cityChart.setDoubleTapToZoomEnabled(false);
        cityChart.getXAxis().setEnabled(true);
        cityChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);

        TradeAggsDto tradeAggsDto = (TradeAggsDto) getIntent().getSerializableExtra("tradeAggsDto");

        getSupportActionBar().setTitle(tradeAggsDto.getFullname());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fetchTradeSearch(tradeAggsDto);

        moreBtn.setOnClickListener(e -> fetchTradeSearch(tradeAggsDto));

        Call<ApiResponse<TradeStatsDto>> responseCall =
                retrofitApiSpecification.tradeStats(
                        tradeAggsDto.getName(),
                        tradeAggsDto.getRegionCode() + "",
                        tradeAggsDto.getSigunguCode() + "",
                        "dealDate",
                        "desc",
                        "TRADE");

        responseCall.enqueue(new Callback<ApiResponse<TradeStatsDto>>() {
            @Override
            public void onResponse(Call<ApiResponse<TradeStatsDto>> call, Response<ApiResponse<TradeStatsDto>> response) {
                System.out.println("response.body() :: " + response.body());

                ApiResponse<TradeStatsDto> responseBody = response.body();

                if (responseBody == null || responseBody.isDataEmpty()) {
                    return;
                }

                TradeStatsDto responseData = responseBody.getData();

                List<BarEntry> dataList = new ArrayList<>();
                List<String> axis = new ArrayList<>();

                for (TradeStatsCityDto tradeStatsCityDto : responseData.getCityList()) {
                    TableRow tableRow = new TableRow(getApplicationContext());
                    tableRow.setBackground(getResources().getDrawable(R.drawable.corner_radius));
                    tableRow.setClipToOutline(true);

                    boolean isStripe = responseData.getCityList().indexOf(tradeStatsCityDto) % 2 == 0;

                    tableRow.addView(TableBodyCornerTextView.of(getApplicationContext(), tradeStatsCityDto.getDong(), isStripe));
                    tableRow.addView(TableBodyCornerTextView.of(getApplicationContext(), String.format(Locale.getDefault(), "%d만", (int) tradeStatsCityDto.getPrice()), isStripe));
                    tableRow.addView(TableBodyCornerTextView.of(getApplicationContext(), String.format(Locale.getDefault(), "%d건", tradeStatsCityDto.getCount()), isStripe));
                    aggregationTable.addView(tableRow);
                    aggregationTable.addView(new CornerDivide(getApplicationContext()));

                    BarEntry barEntry = new BarEntry(responseData.getCityList().indexOf(tradeStatsCityDto), Double.valueOf(tradeStatsCityDto.getPrice()).floatValue());
                    dataList.add(barEntry);
                    axis.add(tradeStatsCityDto.getDong());
                }

                BarDataSet barDataSet = new BarDataSet(dataList, "평균 가격");
                barDataSet.setColor(getResources().getColor(R.color.colorDefault));

                BarData barData = new BarData(barDataSet);
                cityChart.setData(barData);
                cityChart.invalidate();

                cityChart.setFitBars(true);
                XAxis xAxis = cityChart.getXAxis();
                xAxis.setGranularity(1f);
                xAxis.setGranularityEnabled(true);
                xAxis.setValueFormatter(new IndexAxisValueFormatter(axis));
            }

            @Override
            public void onFailure(Call<ApiResponse<TradeStatsDto>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
