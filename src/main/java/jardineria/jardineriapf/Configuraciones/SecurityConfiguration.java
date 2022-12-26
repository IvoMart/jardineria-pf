package jardineria.jardineriapf.Configuraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import jardineria.jardineriapf.servicios.*;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(usuarioServicio).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()
                .antMatchers("/css/*", "/js/*", "/fonts/*", "/images/*", "/images/*/*", "/", "/registro")
                .permitAll()
            .and()
            .authorizeHttpRequests().antMatchers("/usuarios")//aca en realidad tendria que ir plantas
                .hasAnyRole("Administrador", "Usuario")
            .and()
            .authorizeHttpRequests().antMatchers("/usuarios/crear")//aca en tambien
                .hasRole("Administrador")
            .and()
            .authorizeHttpRequests().antMatchers("/usuarios/editar/*")//aca en tambien
                .hasRole("Usuario")
                .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
                .usernameParameter("email").passwordParameter("password")
                .defaultSuccessUrl("/loginSuccess").permitAll()
            .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll();

        return http.build();
    }

}
