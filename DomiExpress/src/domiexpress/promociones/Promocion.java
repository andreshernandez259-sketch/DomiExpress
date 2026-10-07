package domiexpress.promociones;

import domiexpress.pedido.Pedido;

/** OCP: una promoción nueva es una clase nueva; nada existente se modifica. */
public interface Promocion {
  String getNombre();

  /** Devuelve el valor a descontar (nunca negativo). */
  double calcularDescuento(Pedido pedido);
}
