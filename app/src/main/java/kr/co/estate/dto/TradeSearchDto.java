package kr.co.estate.dto;

import kr.co.estate.constants.TradeType;
import kr.co.estate.dto.embedded.DealDto;
import kr.co.estate.dto.embedded.LocationDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
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
public class TradeSearchDto {
    private String uid;
    private String name;
    private Integer buildYear;
    private Integer floor;
    private Double area;
    private Double areaSub;
    private Integer amount;
    private Integer amountOption;
    private TradeType tradeType;
    private String villaType;
    private String createAt;
    private CoordinateDto coordinate;
    private DealDto deal;
    private LocationDto location;
    private Double distance;
}
