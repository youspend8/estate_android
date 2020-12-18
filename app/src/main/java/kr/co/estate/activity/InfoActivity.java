package kr.co.estate.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import kr.co.estate.R;
import kr.co.estate.activity.callback.TradeSearchCallback;
import kr.co.estate.activity.callback.TradeStatsCityCallback;
import kr.co.estate.activity.callback.TradeStatsPeriodCallback;
import kr.co.estate.chart.TradeAggregateCityChart;
import kr.co.estate.chart.TradeAggregatePeriodChart;
import kr.co.estate.client.RetrofitApiSpecification;
import kr.co.estate.client.RetrofitSpecificationFactory;
import kr.co.estate.dto.TradeAggsDto;
import kr.co.estate.view.radio.Radio;
import kr.co.estate.view.radio.RadioItemAttributes;
import lombok.Getter;

@Getter
public class InfoActivity extends AppCompatActivity {
    private RetrofitApiSpecification retrofitApiSpecification;
    private Button moreBtn;
    private TableLayout searchListTable;
    private TableLayout tradeStatsCityTable;
    private TradeAggregateCityChart cityChart;
    private TradeAggregatePeriodChart periodChart;
    private TradeStatsPeriodCallback tradeStatsPeriodCallback;
    private TradeSearchCallback tradeSearchCallback;
    private TradeStatsCityCallback tradeStatsCityCallback;
    private int page;
    private Radio orderRadio;
    private LinearLayout tradeAggregateCityChartSection;
    private TradeAggsDto tradeAggsDto;
    private String sortType = "amount";
    private String sortMode = "desc";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tradeAggsDto = (TradeAggsDto) getIntent().getSerializableExtra("tradeAggsDto");

        retrofitApiSpecification = RetrofitSpecificationFactory.getApiSpecification();

        tradeSearchCallback = new TradeSearchCallback(this);
        tradeStatsPeriodCallback = new TradeStatsPeriodCallback(this);
        tradeStatsCityCallback = new TradeStatsCityCallback(this);

        searchListTable = findViewById(R.id.searchListTable);
        tradeStatsCityTable = findViewById(R.id.tradeStatsCityTable);
        cityChart = findViewById(R.id.cityChart);
        periodChart = findViewById(R.id.periodChart);
        moreBtn = findViewById(R.id.moreBtn);
        orderRadio = findViewById(R.id.orderRadio);
        tradeAggregateCityChartSection = findViewById(R.id.tradeAggregateCityChartSection);

        if (tradeAggsDto.getType() >= 2) {
            tradeStatsCityTable.setVisibility(View.INVISIBLE);
            tradeAggregateCityChartSection.setVisibility(View.INVISIBLE);
        }

        getSupportActionBar().setTitle(tradeAggsDto.getFullname());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        orderRadio.setList(Arrays.asList(
                RadioItemAttributes.of("가격 비싼 순", "amount", "desc"),
                RadioItemAttributes.of("거래일 최신 순", "dealDate", "desc"))
        );
        orderRadio.<String>addOnChangeListener(target -> {
            RadioItemAttributes<String> attributes = target.getAttributes();
            switch (attributes.getName()) {
                case "amount": {
                    if ("desc".equals(attributes.getValue())) {
                        attributes.setValue("asc");
                        target.setText("가격 싼 순");
                    } else {
                        attributes.setValue("desc");
                        target.setText("가격 비싼 순");
                    }
                } break;
                case "dealDate": {
                    if ("desc".equals(attributes.getValue())) {
                        attributes.setValue("asc");
                        target.setText("거래일 오래된 순");
                    } else {
                        attributes.setValue("desc");
                        target.setText("거래일 최신 순");
                    }
                } break;
            }
            sortType = attributes.getName();
            sortMode = attributes.getValue();
            page = 0;

            searchListTable.removeAllViews();

            fetchTradeSearch(page, sortType, sortMode);
        });

        moreBtn.setOnClickListener(e -> fetchTradeSearch(++page, sortType, sortMode));

        fetchTradeSearch(page, sortType, sortMode);
        fetchTradeStatsPeriod(tradeAggsDto);
        fetchTradeStats(tradeAggsDto);
    }

    private void fetchTradeStatsPeriod(TradeAggsDto tradeAggsDto) {
        retrofitApiSpecification.tradeStatsPeriod(
                tradeAggsDto.getName(),
                tradeAggsDto.getRegionCode() + "",
                tradeAggsDto.getSigunguCode() + "",
                tradeAggsDto.getUmdCode() + "",
                tradeAggsDto.getType(),
                "TRADE",
                "2020-10-01",
                "2020-11-30"
        ).enqueue(tradeStatsPeriodCallback);
    }

    private void fetchTradeStats(TradeAggsDto tradeAggsDto) {
        retrofitApiSpecification.tradeStats(
                tradeAggsDto.getName(),
                tradeAggsDto.getRegionCode() + "",
                tradeAggsDto.getSigunguCode() + "",
                tradeAggsDto.getUmdCode() + "",
                tradeAggsDto.getType(),
                "TRADE",
                "2020-10-01",
                "2020-11-30"
        ).enqueue(tradeStatsCityCallback);
    }

    private void fetchTradeSearch(int page, String sortType, String sortMode) {
        retrofitApiSpecification.tradeSearch(
                tradeAggsDto.getName(),
                tradeAggsDto.getRegionCode() + "",
                tradeAggsDto.getSigunguCode() + "",
                tradeAggsDto.getUmdCode() + "",
                tradeAggsDto.getType(),
                page,
                10,
                sortType,
                sortMode,
                "TRADE",
                "2020-10-01",
                "2020-11-30"
        ).enqueue(tradeSearchCallback);
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
