package domiexpress.promociones;

import domiexpress.pedido.Pedido;

public class PromoEnvioGratis implements Promocion {
  @Override
  public String getNombre() {
    return "Envío gratis";
  }

  @Override
  public double calcularDescuento(Pedido pedido) {
    return pedido.calcularCostoEnvio();
  }
}
