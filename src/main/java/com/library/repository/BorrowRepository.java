package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.library.model.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

    @Query(value = "SELECT * FROM borrow r where r.book_id = :bookId AND r.borrowed_by = :userId", nativeQuery = true)
    Borrow findByBookIdAndUserId(@Param("bookId") int bookId,@Param("userId") int userId);
    
    @Query(value = "SELECT * FROM borrow r where r.borrowed_by = :userId", nativeQuery = true)
    List<Borrow> findByUserId(@Param("userId") int userId);
}
