package domiexpress.pago;

/** DIP: el servicio depende de esta abstracción, no de una pasarela concreta. */
public interface PasarelaPago {
  boolean cobrar(MetodoPago metodo, double monto);
}
