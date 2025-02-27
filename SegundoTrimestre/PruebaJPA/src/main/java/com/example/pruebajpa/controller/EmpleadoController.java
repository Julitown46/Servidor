package com.example.pruebajpa.controller;

import com.example.pruebajpa.model.Empleado;
import com.example.pruebajpa.repository.EmpleadoRepository;
import com.example.pruebajpa.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping({"","/"})
    public List<Empleado> empleados(){
        return empleadoService.findAll();
    }

    @GetMapping("/create")
    public Empleado create(){
        Empleado empleado=new Empleado( );
        empleado.setNombre("Juan");
        empleado.setApellido("García Parera");
        return empleadoService.guardarEmpleado(empleado);
    }
}
