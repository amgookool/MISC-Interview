package misc.backend.CRUDdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import misc.backend.CRUDdemo.models.ItemEntity;

// Repository interface for ItemEntity
// This interface extends JpaRepository which is a Spring Data JPA specific interface
// JpaRepository has methods for performing CRUD operations on the entity
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

}
