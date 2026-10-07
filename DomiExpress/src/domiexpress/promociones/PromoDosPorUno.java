package domiexpress.promociones;

import domiexpress.pedido.Pedido;
import domiexpress.productos.Producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** En cada pareja de productos, el más barato es gratis. */
public class PromoDosPorUno implements Promocion {
  @Override
  public String getNombre() {
    return "2x1";
  }

  @Override
  public double calcularDescuento(Pedido pedido) {
    List<Double> precios = new ArrayList<>();
    for (Producto producto : pedido.getProductos()) {
      precios.add(producto.calcularPrecio());
    }
    precios.sort(Collections.reverseOrder());
    double descuento = 0;
    for (int i = 1; i < precios.size(); i += 2) {
      descuento += precios.get(i);
    }
    return descuento;
  }
}
