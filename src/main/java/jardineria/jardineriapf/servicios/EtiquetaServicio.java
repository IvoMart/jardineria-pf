package jardineria.jardineriapf.servicios;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class EtiquetaServicio {

    @Autowired
    EtiquetaRepositorio EtiquetaRepositorio;

    public List<Etiqueta> getAll()
    {
        List<Etiqueta> lista = new ArrayList<Etiqueta>();
        lista = (ArrayList<Etiqueta>) EtiquetaRepositorio.findAll();
        return lista;
    }

    public Etiqueta getById(Long id)
    {
        return EtiquetaRepositorio.findById(id).get();
    }

    public void save(Etiqueta Etiqueta)
    {
        EtiquetaRepositorio.save(Etiqueta);
    }

    public void delete(Long id)
    {
        EtiquetaRepositorio.deleteById(id);
    }
    
}