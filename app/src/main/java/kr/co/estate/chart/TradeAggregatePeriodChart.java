package kr.co.estate.chart;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import kr.co.estate.R;
import kr.co.estate.dto.embedded.stats.TradeStatsPeriodItem;

public class TradeAggregatePeriodChart extends CombinedChart {
    private List<String> dateList = new ArrayList<>();

    public TradeAggregatePeriodChart(Context context) {
        super(context);
        initialize();
    }

    public TradeAggregatePeriodChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public TradeAggregatePeriodChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public void setDataList(List<TradeStatsPeriodItem> periodList) {
        List<Entry> lineEntries = new ArrayList<>();
        List<BarEntry> barEntries = new ArrayList<>();

        for (TradeStatsPeriodItem item : periodList) {
            lineEntries.add(new Entry(periodList.indexOf(item), item.getCount()));
            barEntries.add(new BarEntry(periodList.indexOf(item), Double.valueOf(item.getPrice()).floatValue()));
            dateList.add(item.getDate());
        }

        LineDataSet lineDataSet = new LineDataSet(lineEntries, "거래 건수");
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);

        BarDataSet barDataSet = new BarDataSet(barEntries, "평균 평당가격");
        barDataSet.setValueTextSize(10f);
        barDataSet.setColor(getResources().getColor(R.color.colorDefault));
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);

        CombinedData combinedData = new CombinedData();
        combinedData.setData(new BarData(barDataSet));
        combinedData.setData(new LineData(lineDataSet));
        setData(combinedData);

        invalidate();
    }

    private void initialize() {
        setPinchZoom(false);
        getDescription().setEnabled(false);
        setBackgroundColor(Color.WHITE);
        setDrawGridBackground(false);
        setDrawBarShadow(false);
        setHighlightFullBarEnabled(false);
        setDoubleTapToZoomEnabled(false);
        animateY(1500);
        setDrawOrder(new DrawOrder[]{
                DrawOrder.BAR, DrawOrder.BUBBLE, DrawOrder.CANDLE, DrawOrder.LINE, DrawOrder.SCATTER
        });

        Legend legend = getLegend();
        legend.setWordWrapEnabled(true);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setEnabled(false);

        YAxis rightAxis = getAxisRight();
        rightAxis.setEnabled(true);
        rightAxis.setDrawGridLines(false);

        YAxis leftAxis = getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setEnabled(true);

        XAxis xAxis = getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisMinimum(0f);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                int axisValue = (int) value;
                if (axisValue >= 0 && axisValue < dateList.size()) {
                    return dateList.get(axisValue);
                } else {
                    return "";
                }
            }
        });
    }
}
