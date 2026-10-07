package domiexpress.promociones;

import domiexpress.pedido.Pedido;
import java.util.List;
import java.util.StringJoiner;

/** Patrón Composite (bonus): varias promociones se comportan como una sola. */
public class PromoCompuesta implements Promocion {
  private final List<Promocion> promociones;

  public PromoCompuesta(List<Promocion> promociones) {
    this.promociones = List.copyOf(promociones);
  }

  @Override
  public String getNombre() {
    StringJoiner nombres = new StringJoiner(" + ");
    for (Promocion promocion : promociones) {
      nombres.add(promocion.getNombre());
    }
    return nombres.toString();
  }

  @Override
  public double calcularDescuento(Pedido pedido) {
    double descuento = 0;
    for (Promocion promocion : promociones) {
      descuento += promocion.calcularDescuento(pedido);
    }
    return descuento;
  }
}
