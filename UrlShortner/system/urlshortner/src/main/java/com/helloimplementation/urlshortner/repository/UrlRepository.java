package com.helloimplementation.urlshortner.repository;

import com.helloimplementation.urlshortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<Url,Long> {
    @Query("SELECT u FROM Url u WHERE u.shortenedUrl = :shortenedUrl")
    Optional<Url> findUrlByShortenedUrl(@Param("shortenedUrl") String shortenedUrl);
}
