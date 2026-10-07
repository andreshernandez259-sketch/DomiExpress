package domiexpress.facturacion;

import domiexpress.pedido.Pedido;
import domiexpress.productos.Producto;
import domiexpress.promociones.Promocion;
import domiexpress.util.Dinero;

/** SRP: la factura se genera aquí, no dentro de Pedido. */
public class GeneradorFactura {
  public String generar(Pedido pedido) {
    StringBuilder factura = new StringBuilder();
    factura.append("========== FACTURA ").append(pedido.getId()).append(" ==========\n");
    factura.append("Cliente: ").append(pedido.getCliente().getNombre()).append('\n');
    factura.append("Entrega: ").append(pedido.getEntrega().describir()).append('\n');
    factura.append("------------------------------------------------------\n");
    for (Producto producto : pedido.getProductos()) {
      factura.append(linea(producto.getNombre(), Dinero.formato(producto.calcularPrecio())));
    }
    factura.append("------------------------------------------------------\n");
    factura.append(linea("Subtotal", Dinero.formato(pedido.calcularSubtotal())));
    factura.append(linea("Envío", Dinero.formato(pedido.calcularCostoEnvio())));
    for (Promocion promocion : pedido.getPromociones()) {
      factura.append(linea("Promo: " + promocion.getNombre(),
          "-" + Dinero.formato(promocion.calcularDescuento(pedido))));
    }
    factura.append(linea("TOTAL", Dinero.formato(pedido.calcularTotal())));
    factura.append("======================================================");
    return factura.toString();
  }

  private String linea(String concepto, String valor) {
    return String.format("%-40s %13s%n", concepto, valor);
  }
}
