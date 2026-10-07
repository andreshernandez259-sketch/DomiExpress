package domiexpress.entrega;

/** LSP: contrato mínimo que cumplen todas las entregas, sin excepciones ni chequeos de tipo. */
public interface TipoEntrega {
  double calcularCostoEnvio();

  String describir();
}
