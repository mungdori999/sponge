package com.petweb.sponge.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRoomJpaRepository extends JpaRepository<ChatRoomEntity, Long> {


    @Query(value = """
                SELECT cr FROM chat_room cr 
                WHERE cr.trainer_id = :trainerId 
                ORDER BY 
                    CASE 
                        WHEN cr.modified_at IS NOT NULL AND cr.modified_at > 0 THEN cr.modified_at 
                        ELSE cr.created_at 
                    END DESC
                LIMIT :limit OFFSET :offset
            """, nativeQuery = true)
    List<ChatRoomEntity> findListByTrainerId(@Param("trainerId") Long loginId, @Param("limit") int limit, @Param("offset") int offset);
}
