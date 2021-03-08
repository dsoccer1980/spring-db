package ru.dsoccer.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.dsoccer.app.model.StoreUser;

@RepositoryRestResource
public interface StoreRepository extends JpaRepository<StoreUser, Long> {

}
