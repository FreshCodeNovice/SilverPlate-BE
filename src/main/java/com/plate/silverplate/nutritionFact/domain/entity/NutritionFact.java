package com.plate.silverplate.nutritionFact.domain.entity;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "nutrition_fact")
public class NutritionFact extends BaseTimeEntity {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "FOOD_CD")
    private String foodCD;

    @Column(name = "SAMPLING_REGION_NAME")
    private String samplingRegionName;

    @Column(name = "SAMPLING_MONTH_NAME")
    private String samplingMonthName;

    @Column(name = "SAMPLING_REGION_CD")
    private String samplingRegionCD;

    @Column(name = "SAMPLING_MONTH_CD")
    private String samplingMonthCD;

    @Column(name = "GROUP_NAME")
    private String groupName;

    @Column(name = "DESC_KOR")
    private String descKor;

    @Column(name = "RESEARCH_YEAR")
    private int researchYear;

    @Column(name = "MAKER_NAME")
    private String makeName;

    @Column(name = "SUB_REF_NAME")
    private String subRefName;

    @Column(name = "SERVING_SIZE")
    private double servingSize;

    @Column(name = "SERVING_UNIT")
    private String servingUnit;

    @Column(name = "NUTR_CONT1")
    private double nutrCont1;

    @Column(name = "NUTR_CONT2")
    private double nutrCont2;

    @Column(name = "NUTR_CONT3")
    private double nutrCont3;

    @Column(name = "NUTR_CONT4")
    private double nutrCont4;

    @Column(name = "NUTR_CONT5")
    private double nutrCont5;

    @Column(name = "NUTR_CONT6")
    private double nutrCont6;

    @Column(name = "NUTR_CONT7")
    private double nutrCont7;

    @Column(name = "NUTR_CONT8")
    private double nutrCont8;

    @Column(name = "NUTR_CONT9")
    private double nutrCont9;

    @Builder
    public NutritionFact(Long id, String foodCD, String samplingRegionName, String samplingMonthName, String samplingRegionCD, String samplingMonthCD, String groupName, String descKor, int researchYear, String makeName, String subRefName, double servingSize, String servingUnit, double nutrCont1, double nutrCont2, double nutrCont3, double nutrCont4, double nutrCont5, double nutrCont6, double nutrCont7, double nutrCont8, double nutrCont9) {
        this.id = id;
        this.foodCD = foodCD;
        this.samplingRegionName = samplingRegionName;
        this.samplingMonthName = samplingMonthName;
        this.samplingRegionCD = samplingRegionCD;
        this.samplingMonthCD = samplingMonthCD;
        this.groupName = groupName;
        this.descKor = descKor;
        this.researchYear = researchYear;
        this.makeName = makeName;
        this.subRefName = subRefName;
        this.servingSize = servingSize;
        this.servingUnit = servingUnit;
        this.nutrCont1 = nutrCont1;
        this.nutrCont2 = nutrCont2;
        this.nutrCont3 = nutrCont3;
        this.nutrCont4 = nutrCont4;
        this.nutrCont5 = nutrCont5;
        this.nutrCont6 = nutrCont6;
        this.nutrCont7 = nutrCont7;
        this.nutrCont8 = nutrCont8;
        this.nutrCont9 = nutrCont9;
    }
}