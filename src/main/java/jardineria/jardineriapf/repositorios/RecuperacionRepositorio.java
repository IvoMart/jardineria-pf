package jardineria.jardineriapf.repositorios;

import jardineria.jardineriapf.entidades.*;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecuperacionRepositorio extends CrudRepository <Recuperacion, Long>{
    
    //Optional<Recuperacion> findBy(String string); 

}
