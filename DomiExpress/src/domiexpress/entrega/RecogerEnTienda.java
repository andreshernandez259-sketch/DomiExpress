package domiexpress.entrega;

/** No tiene dirección ni costo de envío: no se le obliga a nada que no le corresponde. */
public class RecogerEnTienda implements TipoEntrega {
  private final String tienda;

  public RecogerEnTienda(String tienda) {
    this.tienda = tienda;
  }

  @Override
  public double calcularCostoEnvio() {
    return 0;
  }

  @Override
  public String describir() {
    return "Recoger en tienda " + tienda;
  }
}
