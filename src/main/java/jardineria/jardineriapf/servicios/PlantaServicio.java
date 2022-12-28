package jardineria.jardineriapf.servicios;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;
import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class PlantaServicio {

	@Autowired
	PlantaRepositorio PlantaRepositorio;

	public List<Planta> getAll() {
		List<Planta> lista = new ArrayList<Planta>();
		lista = (ArrayList<Planta>) PlantaRepositorio.findAll();
		return lista;
	}

	public List<Planta> findAll() {
		return (ArrayList<Planta>) PlantaRepositorio.findAll();
	}

	public Planta finById(Long id) {
		return PlantaRepositorio.findById(id).get();
	}

	public Planta getById(Long id) {
		return PlantaRepositorio.findById(id).get();
	}

	public Planta save_Planta(Planta planta) {
		return PlantaRepositorio.save(planta);
	}

	public Planta update_Planta(Long id, Planta planta) {

		Planta _planta = PlantaRepositorio.findById(id)
				.orElseThrow(RuntimeException::new);

		_planta.setNombre_cientifico(planta.getNombre_cientifico());
		_planta.setNombre_vulgar(planta.getNombre_vulgar());
		_planta.setDescripcion(planta.getDescripcion());
		_planta.setPropiedades(planta.getPropiedades());
		_planta.setFoto(planta.getFoto());
		return PlantaRepositorio.save(planta);
	}

	public Planta delete_Planta(Long id) {

		Planta _planta = PlantaRepositorio
				.findById(id)
				.orElseThrow(RuntimeException::new);
		if (_planta != null){
			PlantaRepositorio.deleteById(id);
			return _planta;
		}
		
		return null;
	}

	public void save(Planta Planta) {
		PlantaRepositorio.save(Planta);
	}

	public void delete(Long id) {
		PlantaRepositorio.deleteById(id);
	}

}