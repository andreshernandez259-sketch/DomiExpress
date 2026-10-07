package domiexpress.usuarios;

import domiexpress.entrega.TipoEntrega;
import domiexpress.pedido.Pedido;

public class Cliente implements PuedeOrdenar {
  private final String nombre;
  private final String contacto;

  public Cliente(String nombre, String contacto) {
    this.nombre = nombre;
    this.contacto = contacto;
  }

  public String getNombre() {
    return nombre;
  }

  public String getContacto() {
    return contacto;
  }

  @Override
  public Pedido crearPedido(String id, TipoEntrega entrega) {
    return new Pedido(id, this, entrega);
  }
}
