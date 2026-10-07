package domiexpress.usuarios;

import domiexpress.pedido.Pedido;

public class Repartidor implements PuedeEntregar {
  private final String nombre;

  public Repartidor(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public void entregar(Pedido pedido) {
    System.out.printf("[Repartidor %s] Entregando %s a %s%n",
        nombre, pedido.getId(), pedido.getCliente().getNombre());
  }
}
