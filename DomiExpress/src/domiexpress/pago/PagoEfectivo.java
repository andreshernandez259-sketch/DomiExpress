package domiexpress.pago;

/** Se paga al recibir el pedido, por eso siempre se autoriza. */
public class PagoEfectivo implements MetodoPago {
  @Override
  public String getNombre() {
    return "Efectivo";
  }

  @Override
  public boolean autorizar(double monto) {
    return true;
  }
}
