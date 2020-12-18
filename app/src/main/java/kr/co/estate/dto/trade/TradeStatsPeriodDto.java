package kr.co.estate.dto.trade;

import java.util.List;

import kr.co.estate.dto.embedded.stats.TradeStatsPeriodItem;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Builder
public class TradeStatsPeriodDto {
    private List<TradeStatsPeriodItem> periodList;
    private double priceAvg;
    private long countSum;
}
