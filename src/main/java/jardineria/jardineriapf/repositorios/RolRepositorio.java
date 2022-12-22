package jardineria.jardineriapf.repositorios;

import jardineria.jardineriapf.entidades.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepositorio extends CrudRepository <Rol, Long>{
    
}
