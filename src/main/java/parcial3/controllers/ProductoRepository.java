package parcial3.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository <Producto, String> {
    List<Producto> findAll();

    Optional<Producto> findById(String id);

    Producto save(Producto producto);

    void deleteById(String id);
    // No es necesario definir métodos adicionales aquí, ya que JpaRepository proporciona los métodos básicos.
}
