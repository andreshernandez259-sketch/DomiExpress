package domiexpress.usuarios;

import domiexpress.productos.Producto;
import java.util.List;

/** ISP: capacidad de quien administra un catálogo. */
public interface PuedePublicarProductos {
  void publicar(Producto producto);

  List<Producto> getCatalogo();
}
