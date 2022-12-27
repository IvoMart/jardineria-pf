package jardineria.jardineriapf.servicios;

import java.util.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import jardineria.jardineriapf.entidades.*;
import jardineria.jardineriapf.repositorios.*;

@Service
public class UsuarioServicio implements UserDetailsService{
    
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
/*
    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private BCryptPasswordEncoder codificador;
*/

    //esto se genero solo al principio pero creo que lo vamos a editar
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.findByEmail(email);
        List<GrantedAuthority> ga = buildAuthorities(usuario.getRol());
        return buildUser(usuario, ga);
    }

    public User buildUser(Usuario usuario, List<GrantedAuthority> ga) {
        return new User(usuario.getEmail(), usuario.getPassword(), ga);
    }

    public List<GrantedAuthority> buildAuthorities(Rol rol) {
        List<GrantedAuthority> ga = new ArrayList<>();
        ga.add( new SimpleGrantedAuthority("ROLE_" + rol.getNombre()) );//aca se concatena para que el codigo entienda el rol
        return ga;
    }
/* 
    @Transactional
    public void registro(Usuario usuario) {
        if (usuarioRepositorio.existsByEmail(usuario.getEmail()))
            throw new IllegalArgumentException("Ya existe un usuario con este email");

        usuario.setPassword( codificador.encode(usuario.getPassword()) );
        usuario.setRol(rolRepositorio.findByNombre("Usuario").orElseThrow(() -> new IllegalArgumentException("Error al crear usuario")));
        usuarioRepositorio.save(usuario);
    }
*/
    public List<Usuario> getAll(){
        
        List<Usuario> lista = new ArrayList<Usuario>();
        usuarioRepositorio.findAll().forEach(registro -> lista.add(registro));
        return lista;

    }

    public Usuario getById(Long id){
        
        return usuarioRepositorio.findById(id).get();

    }

    public void save(@Valid Usuario usuario){

        usuarioRepositorio.save(usuario);

    }

    public void delete(Long id){

        usuarioRepositorio.deleteById(id);

    }

}
