package domiexpress.productos;

/** El recargo vive en el enum: así Bebida no necesita if/switch por tamaño. */
public enum TamanoBebida {
  PEQUENO(0.0),
  MEDIANO(0.10),
  GRANDE(0.20);

  private final double recargo;

  TamanoBebida(double recargo) {
    this.recargo = recargo;
  }

  public double aplicarRecargo(double precioBase) {
    return precioBase * (1 + recargo);
  }
}
