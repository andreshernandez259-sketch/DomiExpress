package domiexpress.persistencia;

import domiexpress.pedido.Pedido;
import java.util.List;
import java.util.Optional;

/** DIP: el negocio no sabe si los datos viven en memoria, MySQL o MongoDB. */
public interface RepositorioPedidos {
  void guardar(Pedido pedido);

  Optional<Pedido> buscarPorId(String id);

  List<Pedido> listarTodos();
}
