package domiexpress.util;

import java.util.Locale;

/** Utilidad para mostrar valores en pesos colombianos. */
public final class Dinero {
  private static final Locale COLOMBIA = Locale.forLanguageTag("es-CO");

  private Dinero() {}

  public static String formato(double valor) {
    return "$" + String.format(COLOMBIA, "%,.0f", valor);
  }
}
