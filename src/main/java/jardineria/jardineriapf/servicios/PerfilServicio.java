package jardineria.jardineriapf.servicios;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public class PerfilServicio {
    
    @Autowired
    PerfilRepositorio perfilRepositorio;

    public List<Perfil> findAll(){
        
        List<Perfil> lista = new ArrayList<Perfil>();
        perfilRepositorio.findAll().forEach(lista::add);
        return lista;

    }

    public Perfil getById(Long id){
        
        return perfilRepositorio.findById(id).get();

    }

	public Perfil save_Perfil(Perfil perfil) {
		return perfilRepositorio.save(perfil);
	}

	public Perfil update_Perfil(Long id, Perfil perfil) {

		Perfil _perfil = perfilRepositorio.findById(id)
				.orElseThrow(RuntimeException::new);

        _perfil.setNombre(perfil.getNombre());
        _perfil.setApellido(perfil.getApellido());
        _perfil.setTipo_Documento(perfil.getTipo_Documento());
        _perfil.setDocumento_identidad(perfil.getDocumento_identidad());
        _perfil.setEmail_alternativo(perfil.getEmail_alternativo());
        _perfil.setTelefono(perfil.getTelefono());
        _perfil.setFechaNacimiento(perfil.getFechaNacimiento());
        _perfil.setDomicilio(perfil.getDomicilio());
        _perfil.setDomicilio_2(perfil.getDomicilio_2());
        _perfil.setUserId(perfil.getUserId());
		
		return perfilRepositorio.save(perfil);
	}

    public void save(Perfil perfil){
        perfilRepositorio.save(perfil);
    }

    public void delete(Long id){
        perfilRepositorio.deleteById(id);
    }


	public Perfil delete_Perfil(Long id) {

		Perfil perfil = perfilRepositorio
			.findById(id)
			.orElseThrow(RuntimeException::new);
		if (perfil != null){
			perfilRepositorio.deleteById(id);
			return perfil;
		}
		
		return null;
	}

    public Boolean existsById(Long id){
        return perfilRepositorio.existsById(id);
    }

}
