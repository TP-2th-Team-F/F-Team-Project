package com.jojoldu.book.springboot.domain.numballs;

import com.jojoldu.book.springboot.domain.numballs.Numballs;
import org.springframework.data.jpa.repository.JpaRepository;

// DAO : DB Layer 접근자.
public interface NumballsRepository extends JpaRepository<Numballs, Long> {
}
