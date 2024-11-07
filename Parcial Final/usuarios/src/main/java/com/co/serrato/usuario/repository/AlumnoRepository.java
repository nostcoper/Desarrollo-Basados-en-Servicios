package com.co.serrato.usuario.repository;
import org.springframework.data.repository.CrudRepository;
import com.co.serrato.usuario.models.entity.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno, Long> {
}
