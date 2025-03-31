package parcial3.controllers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Producto {

    @Id
    private String id;
    private String nombre;

    // Constructor vacío necesario para JPA
    public Producto() {}

    public Producto(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
