package domiexpress.usuarios;

import domiexpress.pedido.Pedido;

/** ISP: capacidad de quien prepara pedidos. */
public interface PuedePrepararPedidos {
  void prepararPedido(Pedido pedido);
}
