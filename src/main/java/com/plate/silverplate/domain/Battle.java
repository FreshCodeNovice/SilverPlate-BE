package com.plate.silverplate.domain;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "battle")
public class Battle extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 50)
    @NotNull
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotNull
    @Column(name = "max_headcount", nullable = false)
    private int maxHeadcount;

    @NotNull
    @Column(name = "current_headcount", nullable = false)
    private int currentHeadcount;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Builder
    public Battle(Long id, String name, int maxHeadcount, int currentHeadcount, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.name = name;
        this.maxHeadcount = maxHeadcount;
        this.currentHeadcount = currentHeadcount;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}