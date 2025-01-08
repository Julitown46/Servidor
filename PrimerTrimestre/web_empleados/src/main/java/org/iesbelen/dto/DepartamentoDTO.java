package org.iesbelen.dto;

import org.iesbelen.model.Departamento;

public class DepartamentoDTO extends Departamento {
    private int cantEmpleados;

    public DepartamentoDTO() {}

    public int getCantEmpleados() { return cantEmpleados; }
    public void setCantEmpleados(int cantEmpleados) { this.cantEmpleados = cantEmpleados; }
}