package org.iesbelen.model;

public class FabricanteDTO extends Fabricante {
    private Integer cantProductos;

    public FabricanteDTO(Fabricante fabricante, Integer cantProductos) {
        this.setIdFabricante(fabricante.getIdFabricante());
        this.setNombre(fabricante.getNombre());
        this.cantProductos = cantProductos;
    }

    public FabricanteDTO() {}

    public Integer getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(Integer cantProductos) {
        this.cantProductos = cantProductos;
    }
}
