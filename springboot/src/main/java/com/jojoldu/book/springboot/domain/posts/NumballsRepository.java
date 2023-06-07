package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// DAO : DB Layer 접근자.
public interface NumballsRepository extends JpaRepository<Numballs, Long> {
}
