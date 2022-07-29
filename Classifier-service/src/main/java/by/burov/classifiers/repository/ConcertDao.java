package by.burov.classifiers.repository;

import by.burov.classifiers.repository.entity.ConcertCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConcertDao extends JpaRepository<ConcertCategory,UUID> {
    @Override
    List<ConcertCategory> findAll();

    @Override
    <S extends ConcertCategory> S save(S entity);


    @Override
    Optional<ConcertCategory> findById(UUID uuid);






}
