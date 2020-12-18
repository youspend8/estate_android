package kr.co.estate.activity.callback;

import android.content.Context;
import android.content.res.Resources;
import android.widget.TableLayout;

import kr.co.estate.activity.InfoActivity;
import kr.co.estate.chart.TradeAggregateCityChart;
import kr.co.estate.chart.TradeAggregatePeriodChart;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class InfoActivityRetrofitCallback {
    private final InfoActivity infoActivity;

    protected Context getContext() {
        return infoActivity.getApplicationContext();
    }

    protected TableLayout getSearchListTable() {
        return infoActivity.getSearchListTable();
    }

    protected TradeAggregatePeriodChart getTradeAggregatePeriodChart() {
        return infoActivity.getPeriodChart();
    }

    protected TradeAggregateCityChart getTradeAggregateCityChart() {
        return infoActivity.getCityChart();
    }

    protected Resources getResources() {
        return infoActivity.getResources();
    }

    protected TableLayout getTradeStatsCityTable() {
        return infoActivity.getTradeStatsCityTable();
    }
}
