package jardineria.jardineriapf.controladores;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.servicios.*;
import java.util.*;
import java.io.*;
import java.nio.file.Paths;
import java.time.LocalDate;

import javax.mail.MessagingException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.*;
import org.springframework.validation.BindingResult; //esto devuleve el mismo formulario en caso de error
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RestController
@RequestMapping("usuarios")
public class UsuariosControlador implements WebMvcConfigurer{
    
    @Autowired
    UsuarioServicio usuarioServicio;

    @Autowired
    RolServicio rolServicio;

    @Autowired
    EmailServicio emailServicio;

    @GetMapping
    private ModelAndView index(){ //inicio

        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Lista de Usuarios");
        maw.addObject("vista", "users/index");
        maw.addObject("usuarios", usuarioServicio.getAll());
        return maw;

    }
    /*
    private List<Rol> index(){
        return rolServicio.getAll();
    }
    */

    @GetMapping("/{id}")//mostrar un rol
    private ModelAndView one(@PathVariable("id") Long id){

        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Detalles del Usuario #" + id);
        maw.addObject("vista", "users/ver");
        maw.addObject("usuario", usuarioServicio.getById(id));

        //emailServicio.enviarMailSimple("romeroarmando094@gmail.com", "Prueba de correo", usuarioServicio.getById(id).getNombre()); //esto es para prubar el servicio de Email
         /*
        String[] adjuntos = {"classpath:static/images/fondo.jpg", "classpath:static/images/plantas/prueba.jpg"};
        emailServicio.enviarMailConAdjunto("romeroarmando094@gmail.com", "Prueba de envio de adjuntos", "Este correo tiene adjuntos", adjuntos);
        */
        /* 
        Map<String, Object> atributos = new HashMap<>();
        atributos.put("nombre", "Armando");
        atributos.put("fecha", LocalDate.now().toString());
        atributos.put("habilidades", Arrays.asList("Java", "PHP", "CSS"));

        try{
            emailServicio.enviarMailHtml("romeroarmando094@gmail.com", "Email de prueba con thymeleaf", "emails/ejemplo.html", atributos);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        */
        return maw;

    }
    /* 
    private Rol one(@PathVariable("id") Long id){
        return rolServicio.getById(id);
    }
    */
    @GetMapping("/crear")
    public ModelAndView crear(Usuario usuario){
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Crear Usuario");
        maw.addObject("vista", "users/crear");
        maw.addObject("roles", rolServicio.getAll());
        return maw;
    }

    @PostMapping("/crear")
	public ModelAndView guardar(@Valid Usuario usuario, BindingResult br)
    {
		if ( br.hasErrors() ){
			return this.crear(usuario);
		}

        usuarioServicio.save(usuario); 
        ModelAndView maw = this.index();
        maw.addObject("exito", "Usuario Guardado Exitosamente");
		return maw; //vuelvo al index
	}

    @GetMapping("/editar/{id}")//editar un rol
    public ModelAndView editar(@PathVariable("id") Long id,  Usuario usuario){

        return this.editar(id, usuario, true);

    }

    public ModelAndView editar(@PathVariable("id") Long id,  Usuario usuario, boolean estaPersistido){
        
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Editar Usuario");
        maw.addObject("vista", "users/editar");
        maw.addObject("usuario", rolServicio.getAll());
        maw.addObject("roles", rolServicio.getAll());

        if(estaPersistido){
            maw.addObject("usuario", usuarioServicio.getById(id));
        }
        return  maw;
    }

    @PutMapping("/editar/{id}")
    private ModelAndView update(@PathVariable("id") Long id, @Valid Usuario usuario, BindingResult br, RedirectAttributes ra){

        if (br.hasErrors()){
            return this.editar(id, usuario, false);
        }

        Usuario registro = usuarioServicio.getById(id);
        registro.setNombre(usuario.getNombre());
        registro.setEmail(usuario.getEmail());
        registro.setPassword(usuario.getPassword());
        registro.setEstado(usuario.getEstado());
        registro.setRol(usuario.getRol());
        ModelAndView maw = this.index();

        usuarioServicio.save(registro);
        maw.addObject("exito", "Jugador Editado Exitosamente");
        return maw;
    }

    @DeleteMapping("/{id}")
    private ModelAndView delete(@PathVariable("id") Long id) {
        usuarioServicio.delete(id);
        ModelAndView maw = this.index();
        maw.addObject("exito", "Usuario borrado exitosamente");
        return maw;
    }
}
