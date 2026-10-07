package domiexpress.pago;

/** OCP: un medio de pago nuevo es una clase nueva que implementa esta interfaz. */
public interface MetodoPago {
  String getNombre();

  /** Indica si el medio puede cubrir el monto. No lanza excepciones por falta de fondos. */
  boolean autorizar(double monto);
}
