package kr.co.estate.chart;

import android.content.Context;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import kr.co.estate.R;
import kr.co.estate.dto.embedded.stats.TradeStatsCityDto;

public class TradeAggregateCityChart extends BarChart {
    private List<String> xAxisList = new ArrayList<>();

    public TradeAggregateCityChart(Context context) {
        super(context);
        initialize();
    }

    public TradeAggregateCityChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public TradeAggregateCityChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public void setDataList(List<TradeStatsCityDto> cityList) {
        List<BarEntry> dataList = new ArrayList<>();

        for (TradeStatsCityDto tradeStatsCityDto : cityList) {
            BarEntry barEntry = new BarEntry(cityList.indexOf(tradeStatsCityDto), Double.valueOf(tradeStatsCityDto.getPrice()).floatValue());
            dataList.add(barEntry);
            xAxisList.add(tradeStatsCityDto.getDong());
        }

        BarDataSet barDataSet = new BarDataSet(dataList, "평균 가격");
        barDataSet.setColor(getResources().getColor(R.color.colorDefault));

        BarData barData = new BarData(barDataSet);
        setData(barData);
        setFitBars(true);

        invalidate();
    }

    private void initialize() {
        setPinchZoom(false);
        setDrawBarShadow(false);
        setDrawGridBackground(false);
        setDoubleTapToZoomEnabled(false);
        animateY(1500);

        Description description = getDescription();
        description.setEnabled(false);

        Legend legend = getLegend();
        legend.setEnabled(false);

        YAxis rightAxis = getAxisRight();
        rightAxis.setDrawGridLines(false);
        rightAxis.setEnabled(false);
        rightAxis.setDrawLabels(false);

        YAxis leftAxis = getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setEnabled(true);
        leftAxis.setDrawLabels(true);

        XAxis xAxis = getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisList));
    }
}
