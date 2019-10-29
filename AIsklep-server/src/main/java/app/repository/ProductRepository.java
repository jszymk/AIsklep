package app.repository;

import app.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select p from Product p where (:categoryId is null or category.id = :categoryId) and (:searchPhrase is null or upper(concat(name, description)) like upper(concat('%', :searchPhrase, '%')) )")
    List<Product> findByCriteria(@Param("categoryId") Long categoryId, @Param("searchPhrase") String searchPhrase);
}
