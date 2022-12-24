package jardineria.jardineriapf.repositorios;
// contraseña de spring --> vfzbzhbnggrurkur 
import jardineria.jardineriapf.entidades.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends CrudRepository <Usuario, Long>{
    
}
