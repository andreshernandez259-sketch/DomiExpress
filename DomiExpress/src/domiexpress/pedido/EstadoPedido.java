package domiexpress.pedido;

public enum EstadoPedido {
  CREADO("fue creado"),
  PAGADO("fue pagado"),
  EN_PREPARACION("está en preparación"),
  EN_CAMINO("va en camino"),
  ENTREGADO("fue entregado");

  private final String mensaje;

  EstadoPedido(String mensaje) {
    this.mensaje = mensaje;
  }

  public String getMensaje() {
    return mensaje;
  }
}
