package domiexpress.pago;

public class PagoPSE implements MetodoPago {
  private final String banco;

  public PagoPSE(String banco) {
    this.banco = banco;
  }

  @Override
  public String getNombre() {
    return "PSE (" + banco + ")";
  }

  @Override
  public boolean autorizar(double monto) {
    return monto > 0;
  }
}
