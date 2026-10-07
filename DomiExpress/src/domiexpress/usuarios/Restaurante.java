package domiexpress.usuarios;

import domiexpress.pedido.Pedido;
import domiexpress.productos.Producto;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurante implements PuedePrepararPedidos, PuedePublicarProductos {
  private final String nombre;
  private final List<Producto> catalogo = new ArrayList<>();

  public Restaurante(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public void publicar(Producto producto) {
    catalogo.add(producto);
  }

  @Override
  public List<Producto> getCatalogo() {
    return Collections.unmodifiableList(catalogo);
  }

  @Override
  public void prepararPedido(Pedido pedido) {
    System.out.printf("[Restaurante %s] Preparando %s%n", nombre, pedido.getId());
  }
}
