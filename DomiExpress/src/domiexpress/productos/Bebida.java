package domiexpress.productos;

public class Bebida extends Producto {
  private final double precioBase;
  private final TamanoBebida tamano;

  public Bebida(String nombre, double precioBase, TamanoBebida tamano) {
    super(nombre);
    if (precioBase < 0) {
      throw new IllegalArgumentException("El precio no puede ser negativo");
    }
    this.precioBase = precioBase;
    this.tamano = tamano;
  }

  @Override
  public double calcularPrecio() {
    return tamano.aplicarRecargo(precioBase);
  }
}
