package crudjogo.rest.api.repository;

import crudjogo.rest.api.model.UsuarioModel;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {
}
