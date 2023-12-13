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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "Food_CD")
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
    private long servingSize;

    @Column(name = "SERVING_UNIT")
    private long servingUnit;

    @Column(name = "NUTR_CONT1")
    private long nutrCont1;

    @Column(name = "NUTR_CONT2")
    private long nutrCont2;

    @Column(name = "NUTR_CONT3")
    private long nutrCont3;

    @Column(name = "NUTR_CONT4")
    private long nutrCont4;

    @Column(name = "NUTR_CONT5")
    private long nutrCont5;

    @Column(name = "NUTR_CONT6")
    private long nutrCont6;

    @Column(name = "NUTR_CONT7")
    private long nutrCont7;

    @Column(name = "NUTR_CONT8")
    private long nutrCont8;

    @Column(name = "NUTR_CONT9")
    private long nutrCont9;

    @Builder
    public NutritionFact(Long id, String foodCD, String samplingRegionName, String samplingMonthName, String samplingRegionCD, String samplingMonthCD, String groupName, String descKor, int researchYear, String makeName, String subRefName, long servingSize, long servingUnit, long nutrCont1, long nutrCont2, long nutrCont3, long nutrCont4, long nutrCont5, long nutrCont6, long nutrCont7, long nutrCont8, long nutrCont9) {
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