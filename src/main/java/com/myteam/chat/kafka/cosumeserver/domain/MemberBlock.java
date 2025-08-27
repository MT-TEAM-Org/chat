package com.myteam.chat.kafka.cosumeserver.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member_block",
        uniqueConstraints = @UniqueConstraint(columnNames = {"blocker_id", "blocked_id"}))
public class MemberBlock extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blocker_id", nullable = false)
    private UUID blocker;

    @Column(name = "blocked_id", nullable = false)
    private UUID blocked;

}
