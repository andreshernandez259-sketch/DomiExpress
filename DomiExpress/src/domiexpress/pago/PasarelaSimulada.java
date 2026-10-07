package domiexpress.pago;

import domiexpress.util.Dinero;

public class PasarelaSimulada implements PasarelaPago {
  @Override
  public boolean cobrar(MetodoPago metodo, double monto) {
    boolean aprobado = metodo.autorizar(monto);
    System.out.printf("[Pasarela] %s por %s -> %s%n",
        metodo.getNombre(), Dinero.formato(monto), aprobado ? "APROBADO" : "RECHAZADO");
    return aprobado;
  }
}
