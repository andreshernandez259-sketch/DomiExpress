package domiexpress.pago;

public class PagoNequi implements MetodoPago {
  private final String celular;
  private final double saldo;

  public PagoNequi(String celular, double saldo) {
    this.celular = celular;
    this.saldo = saldo;
  }

  @Override
  public String getNombre() {
    return "Nequi " + celular;
  }

  @Override
  public boolean autorizar(double monto) {
    return monto <= saldo;
  }
}
