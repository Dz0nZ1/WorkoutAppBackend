package rs.ac.singidunum.workout.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.workout.models.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
}
