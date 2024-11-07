package com.co.serrato.usuario.service;

import java.util.Optional;

import com.co.serrato.usuario.models.entity.Alumno;

public interface AlumnoServiceInterface {
	public Iterable<Alumno> findAll();
	public Optional<Alumno> findById(Long id);
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);
}
