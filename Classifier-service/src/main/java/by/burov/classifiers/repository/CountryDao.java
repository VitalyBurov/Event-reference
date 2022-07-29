package by.burov.classifiers.repository;

import by.burov.classifiers.repository.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryDao extends JpaRepository<Country,UUID> {

    @Override
    List<Country> findAll();

    @Override
    <S extends  Country> S save(S entity);

    @Override
    Optional<Country> findById(UUID uuid);






}
