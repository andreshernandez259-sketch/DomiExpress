package domiexpress.entrega;

public class Domicilio implements TipoEntrega {
  private final String direccion;
  private final double costoEnvio;

  public Domicilio(String direccion, double costoEnvio) {
    if (direccion == null || direccion.isBlank()) {
      throw new IllegalArgumentException("El domicilio requiere dirección");
    }
    this.direccion = direccion;
    this.costoEnvio = costoEnvio;
  }

  @Override
  public double calcularCostoEnvio() {
    return costoEnvio;
  }

  @Override
  public String describir() {
    return "Domicilio a " + direccion;
  }
}
