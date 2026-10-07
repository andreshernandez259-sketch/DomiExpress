package domiexpress.usuarios;

import domiexpress.pedido.Pedido;

/** ISP: capacidad de quien entrega pedidos. */
public interface PuedeEntregar {
  void entregar(Pedido pedido);
}
