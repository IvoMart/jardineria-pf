package jardineria.jardineriapf.controladores;

import jardineria.jardineriapf.dto.*;
import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;
import jardineria.jardineriapf.servicios.RecuperacionServicio;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; //encriptador
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthControlador {
    
    @Autowired
    private BCryptPasswordEncoder codificador;

    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private RecuperacionRepositorio recuperacionRepositorio;

    @Autowired
    private RecuperacionServicio recuperacionServicio;

    @Autowired
    private HomeControlador homeControlador;

    //@Autowired
    //private RecaptchaServicio recaptchaServicio;

    @GetMapping("/login")
    public ModelAndView showLoginForm(Model model, 
        @RequestParam(name = "error", required = false) String error,
        @RequestParam(name="logout", required = false) String logout) {
            
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Iniciar sesión");
        maw.addObject("vista", "auth/login");
        model.addAttribute("error", error);
        model.addAttribute("logout", logout);
        return maw;
    }

    @GetMapping({"/loginSuccess"})
    public RedirectView loginCheck(){
        return new RedirectView("/");
    }
    
    @GetMapping("/registro")
	public ModelAndView registro(RegistroDto registroDto)
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Registrarse");
        maw.addObject("vista", "auth/registro");
        maw.addObject("registroDto", registroDto);
        return maw;
	}

	@PostMapping("/registro")
	public ModelAndView registrar(/*@RequestParam(name="g-recaptcha-response") String recaptchaResponse,*/ @Valid RegistroDto registroDto, BindingResult br, RedirectAttributes ra, HttpServletRequest request)
    {
    
        if ( br.hasErrors() ) {
			return this.registro(registroDto);
		}

        Usuario u = new Usuario();
        u.setNombre(registroDto.getNombre());
        u.setEmail(registroDto.getEmail());
        u.setEstado(1);
        u.setPassword(codificador.encode(registroDto.getPassword()));
        u.setRol(rolRepositorio.findByNombre("Usuario").orElseThrow(() -> new IllegalArgumentException("Error al crear usuario")));

		usuarioRepositorio.save(u);

        try {
            request.login(registroDto.getEmail(), registroDto.getPassword());//esto es para que logee una vez creado 
        } catch (ServletException e) {
            e.printStackTrace();
        }

        HomeControlador hc = new HomeControlador();
        return hc.home();
	}

    @GetMapping("/cambio/{id}")
	public ModelAndView cambio(@PathVariable("id") Long id, CambioDto cambioDto) 
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Registrarse");
        maw.addObject("vista", "recuperacion/editar");
        maw.addObject("recuperacion", recuperacionServicio.getById(id));
        //maw.addObject("cambioDto", cambioDto);
        return maw;
	}

    @GetMapping("/crear")
	public ModelAndView crear(RecuperacionDto recuperacionDto)
    {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Recuperacion");
        maw.addObject("vista", "recuperacion/crear");
        maw.addObject("recuperacionDto", recuperacionDto);
        return maw;
	}
    
    @PostMapping("/crear")
	public ModelAndView creacion( @Valid RecuperacionDto recuperacionDto, BindingResult br, RedirectAttributes ra, HttpServletRequest request)
    {
     
        if ( br.hasErrors() ) {
			return this.crear(recuperacionDto);
		}

        Recuperacion u = new Recuperacion();
        u.setMensaje(recuperacionDto.getMensaje());
        u.setUsuario(usuarioRepositorio.findByEmail(recuperacionDto.getEmail()));
        recuperacionRepositorio.save(u);
        
        ModelAndView maw = homeControlador.home();
        maw.addObject("exito", "Mensaje enviado con Exito");
        return maw;
        /* 
        HomeControlador hc = new HomeControlador();
        return hc.home();
        */
	}

}
