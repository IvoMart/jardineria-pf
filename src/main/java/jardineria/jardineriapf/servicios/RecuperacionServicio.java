package jardineria.jardineriapf.servicios;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class RecuperacionServicio {
    
    @Autowired
    RecuperacionRepositorio recuperacionRepositorio;

    public List<Recuperacion> getAll(){
        
        List<Recuperacion> lista = new ArrayList<Recuperacion>();
        recuperacionRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;

    }

    public Recuperacion getById(Long id){
        
        return recuperacionRepositorio.findById(id).get();

    }

    public void save(Recuperacion recuperacion){

        recuperacionRepositorio.save(recuperacion);

    }

    public void delete(Long id){

        recuperacionRepositorio.deleteById(id);

    }

}
