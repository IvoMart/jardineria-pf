package jardineria.jardineriapf.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeControlador {
    
    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("home/index");
        return maw;
    }

    @RequestMapping("/about")
    public ModelAndView about() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("home/about");
        return maw;
    }

    @RequestMapping("/services")
    public ModelAndView services() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("home/services");
        return maw;
    }

    @RequestMapping("/products")
    public ModelAndView products() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("home/products");
        return maw;
    }

    @RequestMapping("/contact")
    public ModelAndView contact() {
        ModelAndView maw = new ModelAndView();
        maw.setViewName("home/contact");
        return maw;
    }
    
    @RequestMapping("/charts-apexcharts")
    public ModelAndView charts_apexcharts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/charts-apexcharts");
        return modelAndView;
    }

    @RequestMapping("/charts-chartjs")
    public ModelAndView charts_chartjs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/charts-chartjs");
        return modelAndView;
    }

    @RequestMapping("/charts-echarts")
    public ModelAndView charts_echarts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/charts-echarts");
        return modelAndView;
    }

    @RequestMapping("/components-accordion")
    public ModelAndView components_accordion() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-accordion");
        return modelAndView;
    }

    @RequestMapping("/components-alerts")
    public ModelAndView components_alerts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-alerts");
        return modelAndView;
    }

    @RequestMapping("/components-badges")
    public ModelAndView components_badges() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-badges");
        return modelAndView;
    }

    @RequestMapping("/components-breadcrumbs")
    public ModelAndView components_breadcrumbs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-breadcrumbs");
        return modelAndView;
    }

    @RequestMapping("/components-buttons")
    public ModelAndView components_buttons() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-buttons");
        return modelAndView;
    }

    @RequestMapping("/components-cards")
    public ModelAndView components_cards() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-cards");
        return modelAndView;
    }

    @RequestMapping("/components-carousel")
    public ModelAndView components_carousel() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-carousel");
        return modelAndView;
    }

    @RequestMapping("/components-list-group")
    public ModelAndView components_list_group() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-list-group");
        return modelAndView;
    }

    @RequestMapping("/components-modal")
    public ModelAndView components_modal() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-modal");
        return modelAndView;
    }

    @RequestMapping("/components-pagination")
    public ModelAndView components_pagination() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-pagination");
        return modelAndView;
    }

    @RequestMapping("/components-progress")
    public ModelAndView components_progress() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-progress");
        return modelAndView;
    }

    @RequestMapping("/components-spinners")
    public ModelAndView components_spinners() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-spinners");
        return modelAndView;
    }

    @RequestMapping("/components-tabs")
    public ModelAndView components_tabs() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-tabs");
        return modelAndView;
    }

    @RequestMapping("/components-tooltips")
    public ModelAndView components_tooltips() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/components-tooltips");
        return modelAndView;
    }

    @RequestMapping("/forms-editors")
    public ModelAndView forms_editors() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/forms-editors");
        return modelAndView;
    }

    @RequestMapping("/forms-elements")
    public ModelAndView forms_elements() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/forms-elements");
        return modelAndView;
    }

    @RequestMapping("/forms-layouts")
    public ModelAndView forms_layouts() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/forms-layouts");
        return modelAndView;
    }

    @RequestMapping("/forms-validation")
    public ModelAndView forms_validation() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/forms-validation");
        return modelAndView;
    }

    @RequestMapping("/icons-bootstrap")
    public ModelAndView icons_bootstrap() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/icons-bootstrap");
        return modelAndView;
    }

    @RequestMapping("/icons-boxicons")
    public ModelAndView icons_boxicons() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/icons-boxicons");
        return modelAndView;
    }

    @RequestMapping("/icons-remix")
    public ModelAndView icons_remix() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/icons-remix");
        return modelAndView;
    }

    @RequestMapping("/pages-blank")
    public ModelAndView pages_blank() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/pages-blank");
        return modelAndView;
    }

    @RequestMapping("/pages-contact")
    public ModelAndView pages_contact() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/pages-contact");
        return modelAndView;
    }

    @RequestMapping("/pages-error-404")
    public ModelAndView pages_error_404() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/pages-error-404");
        return modelAndView;
    }

    @RequestMapping("/pages-faq")
    public ModelAndView pages_faq() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/pages-faq");
        return modelAndView;
    }

    @RequestMapping("/pages-login")
    public ModelAndView pages_login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/pages-login");
        return modelAndView;
    }

    @RequestMapping("/pages-register")
    public ModelAndView pages_register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/pages-register");
        return modelAndView;
    }

    @RequestMapping("/tables-data")
    public ModelAndView tables_data() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/tables-data");
        return modelAndView;
    }

    @RequestMapping("/tables-general")
    public ModelAndView tables_general() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/tables-general");
        return modelAndView;
    }

    @RequestMapping("/users-profile")
    public ModelAndView users_profile() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/users-profile");
        return modelAndView;
    }

    @RequestMapping("/home")
    public ModelAndView index () {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("_dashboard/index");
        return modelAndView;
    }
}