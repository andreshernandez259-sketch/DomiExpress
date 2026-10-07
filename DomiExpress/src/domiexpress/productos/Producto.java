package domiexpress.productos;

/** POO: abstracción. Define el contrato de precio sin implementarlo. */
public abstract class Producto {
  private final String nombre;

  protected Producto(String nombre) {
    if (nombre == null || nombre.isBlank()) {
      throw new IllegalArgumentException("El producto necesita nombre");
    }
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public abstract double calcularPrecio();
}
