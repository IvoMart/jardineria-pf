package jardineria.jardineriapf.servicios;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class PublicacionServicio {

	@Autowired
	PublicacionRepositorio PublicacionRepositorio;

	public List<Publicacion> getAll() {
		List<Publicacion> lista = new ArrayList<Publicacion>();
		lista = (ArrayList<Publicacion>) PublicacionRepositorio.findAll();
		return lista;
	}

	public List<Publicacion> findAll() {
		return (ArrayList<Publicacion>) PublicacionRepositorio.findAll();
	}

	public Publicacion finById(Long id) {
		return PublicacionRepositorio.findById(id).get();
	}

	public Publicacion getById(Long id) {
		return PublicacionRepositorio.findById(id).get();
	}

	public Publicacion save_Publicacion(Publicacion publicacion) {
		return PublicacionRepositorio.save(publicacion);
	}

	public Publicacion update_Publicacion(Long id, Publicacion publicacion) {

		Publicacion _publicacion = PublicacionRepositorio.findById(id)
				.orElseThrow(RuntimeException::new);
		_publicacion.setFechaModificacion(publicacion.getFechaModificacion());
		_publicacion.setUsuarioId(publicacion.getUsuarioId());
		_publicacion.setPlantaId(publicacion.getPlantaId());
		_publicacion.setFoto(publicacion.getFoto());
		return PublicacionRepositorio.save(publicacion);
	}

	public Publicacion delete_Publicacion(Long id) {

		Publicacion _publicacion = PublicacionRepositorio.findById(id)
				.orElseThrow(RuntimeException::new);
		if (_publicacion != null){
			PublicacionRepositorio.deleteById(id);
			return _publicacion;
		}
		
		return null;
	}

	public void save(Publicacion Publicacion) {
		PublicacionRepositorio.save(Publicacion);
	}

	public void delete(Long id) {
		PublicacionRepositorio.deleteById(id);
	}

	public boolean existsById(Long id) {
		return PublicacionRepositorio.existsById(id);
	}

}