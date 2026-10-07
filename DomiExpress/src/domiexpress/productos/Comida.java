package domiexpress.productos;

public class Comida extends Producto {
  private final double precioBase;

  public Comida(String nombre, double precioBase) {
    super(nombre);
    if (precioBase < 0) {
      throw new IllegalArgumentException("El precio no puede ser negativo");
    }
    this.precioBase = precioBase;
  }

  @Override
  public double calcularPrecio() {
    return precioBase;
  }
}
