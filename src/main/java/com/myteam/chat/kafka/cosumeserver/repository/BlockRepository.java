package com.myteam.chat.kafka.cosumeserver.repository;

import com.myteam.chat.kafka.cosumeserver.domain.MemberBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BlockRepository extends JpaRepository<MemberBlock,Long> {
    @Query("select b.blocked from MemberBlock b where b.blocker=:userId")
    List<UUID> getByUserId(UUID userId);
}
