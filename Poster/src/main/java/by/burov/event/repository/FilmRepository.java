package by.burov.event.repository;

import by.burov.event.core.enums.EventStatus;
import by.burov.event.repository.entity.Concert;
import by.burov.event.repository.entity.Film;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface FilmRepository extends JpaRepository<Film,UUID> {

    @Override
    <S extends Film> S save(S entity);

    @Override
    Optional<Film> findById(UUID uuid);

    @Query("SELECT f FROM Film f WHERE f.uuid = ?1 and f.status = ?2")
    Optional<Film> findByUuidAndStatus(UUID uuid, EventStatus status);

    @Query("SELECT f FROM Film f WHERE f.uuid = ?1 and (f.status = ?2 OR f.author = ?3)")
    Optional<Film> findByUuidAndStatusOrAuthor(UUID uuid, EventStatus status,String author);

    @Query("SELECT f FROM Film f WHERE f.status = ?1")
    Page<Film> findByStatus(EventStatus status, Pageable pageable);

    @Query("SELECT f FROM Film f WHERE f.status = ?1 OR f.author = ?2")
    Page<Film> findByStatusORAuthor(EventStatus status, String author, Pageable pageable);

}