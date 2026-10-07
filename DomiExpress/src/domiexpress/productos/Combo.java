package domiexpress.productos;

import java.util.List;

/** Combo = suma de sus ítems con descuento. Un ítem puede ser otro Producto (composite). */
public class Combo extends Producto {
  private final List<Producto> items;
  private final double descuento;

  public Combo(String nombre, List<Producto> items, double descuento) {
    super(nombre);
    if (descuento < 0 || descuento > 1) {
      throw new IllegalArgumentException("El descuento debe estar entre 0 y 1");
    }
    this.items = List.copyOf(items);
    this.descuento = descuento;
  }

  @Override
  public double calcularPrecio() {
    double suma = 0;
    for (Producto item : items) {
      suma += item.calcularPrecio();
    }
    return suma * (1 - descuento);
  }
}
