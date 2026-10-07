package domiexpress.usuarios;

import domiexpress.entrega.TipoEntrega;
import domiexpress.pedido.Pedido;

/** ISP: capacidad de quien hace pedidos. */
public interface PuedeOrdenar {
  Pedido crearPedido(String id, TipoEntrega entrega);
}
