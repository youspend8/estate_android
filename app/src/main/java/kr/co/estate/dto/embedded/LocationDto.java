package kr.co.estate.dto.embedded;

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
public class LocationDto {
    private String sigungu;
    private String dong;
    private String jibun;
    private String regionCode;
    private String sigunguCode;
    private String umdCode;
}
