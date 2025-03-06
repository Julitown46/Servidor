package org.iesbelen.videoclub.controller;

import lombok.extern.slf4j.Slf4j;
import org.iesbelen.videoclub.domain.Pelicula;
import org.iesbelen.videoclub.service.PeliculaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/peliculas")
public class PeliculaController {
    private final PeliculaService peliculaService;

    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!pagina", "!tamanio"})
    public List<Pelicula> all() {
        log.info("Accediendo a todas las películas");
        return this.peliculaService.all();
    }

    //Ordenar en Repository
    @GetMapping(value = {"/order"})
    public List<Pelicula> allOrdered() {
        log.info("Accediendo a todas las películas ordenadas");
        return this.peliculaService.findAllByOrderByTituloAsc();
    }

    //Paginar con array
    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!orden", "!pagina", "!tamanio"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "paginado", defaultValue = "0") String[] paginacion) {
        log.info("Accediendo a todas las películas con paginación");

        Map<String, Object> responseAll = this.peliculaService.all(Integer.parseInt(paginacion[0]), Integer.parseInt(paginacion[1]));

        return ResponseEntity.ok(responseAll);
    }

    //Paginar sin array
    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!paginado"})
    public ResponseEntity<Map<String, Object>> all(@RequestParam(value = "pagina", defaultValue = "0") int pagina,
                                                   @RequestParam(value = "tamanio", defaultValue = "3") int tamanio) {
        log.info("Accediendo a todas las películas con paginación");

        Map<String, Object> responseAll = this.peliculaService.all(pagina, tamanio);

        return ResponseEntity.ok(responseAll);
    }

//    @GetMapping(value = {"","/"}, params = {"!buscar", "!ordenar", "!paginado"})
//    public ResponseEntity<List<Pelicula>> allbyColumn(@RequestParam(value = "orden", required = false) String[] orden) {
//        log.info("Accediendo a todas las películas con ordenación por columnas: " + orden[0]);
//
//        List<Pelicula> peliculas = this.peliculaService.findAllOrderByCol(orden);
//
//        return ResponseEntity.ok(peliculas);
//    }

    //Ordenar con array
    @GetMapping(value = {"","/"}, params = {"!pagina", "!tamanio", "!paginado"})
    public ResponseEntity<List<Pelicula>> allbyColumn(@RequestParam(value = "orden", required = false) String[] orden) {
        log.info("Accediendo a todas las películas con ordenación: " + orden[0]);

        List<Pelicula> peliculas = this.peliculaService.allbyColumn(orden);
        return ResponseEntity.ok(peliculas);
    }

    @PostMapping({"","/"})
    public Pelicula newPelicula(@RequestBody Pelicula pelicula) {
        return this.peliculaService.save(pelicula);
    }

    @GetMapping("/{id}")
    public Pelicula one(@PathVariable("id") Long id) {
        return this.peliculaService.one(id);
    }

    @PutMapping("/{id}")
    public Pelicula replacePelicula(@PathVariable("id") Long id, @RequestBody Pelicula pelicula) {
        return this.peliculaService.replace(id, pelicula);
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePelicula(@PathVariable("id") Long id) {
        this.peliculaService.delete(id);
    }

    @PostMapping("/{id}/addCategoria/{id_categoria}")
    public Pelicula addCategoria(@PathVariable("id") Long id, @PathVariable("id_categoria") Long idCategoria) {
        return this.peliculaService.addCategoria(id, idCategoria);
    }

    @PostMapping("/{id}/addActor/{id_actor}")
    public Pelicula addActor(@PathVariable("id") Long id, @PathVariable("id_actor") Long idActor) {
        return this.peliculaService.addActor(id, idActor);
    }
}
