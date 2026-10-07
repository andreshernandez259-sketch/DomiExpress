package domiexpress.pago;

public class PagoTarjeta implements MetodoPago {
  private final String ultimosCuatro;
  private final double cupoDisponible;

  public PagoTarjeta(String ultimosCuatro, double cupoDisponible) {
    this.ultimosCuatro = ultimosCuatro;
    this.cupoDisponible = cupoDisponible;
  }

  @Override
  public String getNombre() {
    return "Tarjeta ****" + ultimosCuatro;
  }

  @Override
  public boolean autorizar(double monto) {
    return monto <= cupoDisponible;
  }
}
