package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Repository //acceso al repos de db
@Transactional //arma consultas de db
public class UsuarioDaoImp implements UsuarioDao{


    @PersistenceContext
    EntityManager entityManager;//conexion db

    @Override
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public void eliminar(int id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        entityManager.remove(usuario);
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);//para añadirlo a la base de datos
    }

    @Override
    public boolean verificarCredenciales(Usuario usuario){
    String query="FROM Usuario WHERE email=:email ";
    List<Usuario>lista=entityManager.createQuery(query)
            .setParameter("email",usuario.getEmail())
            .getResultList();

        String passwordHashed = lista.get(0).getPassword();//

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        argon2.verify(passwordHashed,usuario.getPassword());//devuelve un boolean
        return !lista.isEmpty();
    }
}
