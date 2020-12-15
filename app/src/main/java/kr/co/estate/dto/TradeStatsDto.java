package kr.co.estate.dto;

import java.util.List;

import kr.co.estate.dto.embedded.stats.TradeStatsCityDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Builder
public class TradeStatsDto {
    private List<TradeStatsCityDto> cityList;
    private double priceAverage;
    private long countSum;
}
