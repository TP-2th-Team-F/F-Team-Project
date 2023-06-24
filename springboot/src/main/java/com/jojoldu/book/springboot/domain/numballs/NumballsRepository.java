package com.jojoldu.book.springboot.domain.numballs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// DAO : DB Layer 접근자.
public interface NumballsRepository extends JpaRepository<Numballs, Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Numballs> findAllDesc();
}
