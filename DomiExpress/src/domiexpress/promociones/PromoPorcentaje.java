package domiexpress.promociones;

import domiexpress.pedido.Pedido;

public class PromoPorcentaje implements Promocion {
  private final int porcentaje;

  public PromoPorcentaje(int porcentaje) {
    if (porcentaje < 0 || porcentaje > 100) {
      throw new IllegalArgumentException("El porcentaje debe estar entre 0 y 100");
    }
    this.porcentaje = porcentaje;
  }

  @Override
  public String getNombre() {
    return porcentaje + "% de descuento";
  }

  @Override
  public double calcularDescuento(Pedido pedido) {
    return pedido.calcularSubtotal() * porcentaje / 100.0;
  }
}
