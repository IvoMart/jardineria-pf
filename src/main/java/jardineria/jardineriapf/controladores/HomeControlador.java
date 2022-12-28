package jardineria.jardineriapf.controladores;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeControlador {
    
    @RequestMapping("/")
    public ModelAndView index(){
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/home_base");
        maw.addObject("titulo", "Inicio");
        maw.addObject("vista", "home/home_index");
        return maw;
    }

    @RequestMapping("/home")
    public ModelAndView home(){
        ModelAndView maw = new ModelAndView();
        maw.setViewName("fragments/base");
        maw.addObject("titulo", "Home");
        maw.addObject("vista", "inicio/home");
        return maw;
    }

}
