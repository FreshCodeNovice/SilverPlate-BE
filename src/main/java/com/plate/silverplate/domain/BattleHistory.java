package com.plate.silverplate.domain;

import com.plate.silverplate.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "battle_history")
public class BattleHistory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "battle_id", nullable = false)
    private Battle battle;

    @NotNull
    @Column(name = "current_yn", nullable = false)
    private boolean currentYn = false;

    @Builder
    public BattleHistory(Long id, User user, Battle battle, boolean currentYn) {
        this.id = id;
        this.user = user;
        this.battle = battle;
        this.currentYn = currentYn;
    }
}