package by.burov.event.repository;

import by.burov.event.core.enums.EventStatus;
import by.burov.event.repository.entity.Concert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConcertRepository extends JpaRepository<Concert, UUID> {

    @Override
    <S extends Concert> S save(S entity);

    @Override
    Optional<Concert> findById(UUID uuid);

    @Query("SELECT c FROM Concert c WHERE c.uuid = ?1 and c.status = ?2")
    Optional<Concert> findByUuidAndStatus(UUID uuid, EventStatus status);

    @Query("SELECT c FROM Concert c WHERE c.uuid = ?1 and (c.status = ?2 OR c.author = ?3)")
    Optional<Concert> findByUuidAndStatusOrAuthor(UUID uuid, EventStatus status,String author);

    @Query("SELECT c FROM Concert c WHERE c.status = ?1")
    Page<Concert> findByStatus(EventStatus status, Pageable pageable);

    @Query("SELECT c FROM Concert c WHERE c.status = ?1 OR c.author = ?2")
    Page<Concert> findByStatusORAuthor(EventStatus status, String author, Pageable pageable);


}
