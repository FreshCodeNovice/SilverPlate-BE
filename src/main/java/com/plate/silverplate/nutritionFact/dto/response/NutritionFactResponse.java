package com.plate.silverplate.nutritionFact.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.plate.silverplate.nutritionFact.domain.entity.NutritionFact;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NutritionFactResponse {

    @JsonProperty("NUM")
    private Long id;

    @JsonProperty("FOOD_CD")
    private String foodCD;

    @JsonProperty("SAMPLING_REGION_NAME")
    private String samplingRegionName;

    @JsonProperty("SAMPLING_MONTH_NAME")
    private String samplingMonthName;

    @JsonProperty("SAMPLING_REGION_CD")
    private String samplingRegionCD;

    @JsonProperty("SAMPLING_MONTH_CD")
    private String samplingMonthCD;

    @JsonProperty("GROUP_NAME")
    private String groupName;

    @JsonProperty("DESC_KOR")
    private String descKor;

    @JsonProperty("RESEARCH_YEAR")
    private int researchYear;

    @JsonProperty("MAKER_NAME")
    private String makeName;

    @JsonProperty("SUB_REF_NAME")
    private String subRefName;

    @JsonProperty("SERVING_SIZE")
    private double servingSize;

    @JsonProperty("SERVING_UNIT")
    private String servingUnit;

    @JsonProperty("NUTR_CONT1")
    private double nutrCont1;

    @JsonProperty("NUTR_CONT2")
    private double nutrCont2;

    @JsonProperty("NUTR_CONT3")
    private double nutrCont3;

    @JsonProperty("NUTR_CONT4")
    private double nutrCont4;

    @JsonProperty("NUTR_CONT5")
    private double nutrCont5;

    @JsonProperty("NUTR_CONT6")
    private double nutrCont6;

    @JsonProperty("NUTR_CONT7")
    private double nutrCont7;

    @JsonProperty("NUTR_CONT8")
    private double nutrCont8;

    @JsonProperty("NUTR_CONT9")
    private double nutrCont9;

    // responseDto를 엔티티로 변경
    public static NutritionFact toEntity(NutritionFactResponse res){
        return NutritionFact.builder()
                .id(res.getId())
                .foodCD(res.getFoodCD())
                .samplingRegionName(res.getSamplingRegionName())
                .samplingMonthName(res.getSamplingMonthName())
                .samplingRegionCD(res.getSamplingRegionCD())
                .samplingMonthCD(res.getSamplingMonthCD())
                .groupName(res.getGroupName())
                .descKor(res.getDescKor())
                .researchYear(res.getResearchYear())
                .makeName(res.getMakeName())
                .subRefName(res.getSubRefName())
                .servingSize(res.getServingSize())
                .servingUnit(res.getServingUnit())
                .nutrCont1(res.getNutrCont1())
                .nutrCont2(res.getNutrCont2())
                .nutrCont3(res.getNutrCont3())
                .nutrCont4(res.getNutrCont4())
                .nutrCont5(res.getNutrCont5())
                .nutrCont6(res.getNutrCont6())
                .nutrCont7(res.getNutrCont7())
                .nutrCont8(res.getNutrCont8())
                .nutrCont9(res.getNutrCont9())
                .build();
    }
    /*
     * 리스트에서 받은 dto들을 toEntity 반환해서 NutritionFact값으로 변경
     * NutritionFact 값들을 Collectors.toList를 통해 List<NutritionFact>로 변경
     * */
    public static List<NutritionFact> toListEntity(List<NutritionFactResponse> list) {
        return list.stream()
                .filter(dto -> dto.getId() != null)
                .map(NutritionFactResponse::toEntity)
                .collect(Collectors.toList());
    }

}
