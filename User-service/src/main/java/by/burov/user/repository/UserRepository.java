package by.burov.user.repository;

import by.burov.user.repository.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {

    @Override
    <S extends User> S save(S entity);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    Optional<User> findById(UUID uuid);

    Optional<User> findByMail(String mail);




}
