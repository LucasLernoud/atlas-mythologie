package controller;

import model.Myth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.MythService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/myths")
public class MythController {

    @Autowired
    private MythService mythService;

    @GetMapping
    public ResponseEntity<List<Myth>> getAllMyths() {
        List<Myth> myths = mythService.findAll();
        return ResponseEntity.ok(myths);
    }

    // RECHERCHE PAR TITRE OU PAR CONTENU
    @GetMapping("/search")
    public ResponseEntity<List<Myth>> searchMyths(@RequestParam String keyword){
        List<Myth> myths = mythService.searchMyths(keyword);

        if(myths.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(myths);
        }
    }

    // FILTRAGE PAR NOM DE PERSONNAGE
    @GetMapping("/filter/character")
    public ResponseEntity<List<Myth>> filterMythsByCharacter(@RequestParam String character){
        List<Myth> myths = mythService.filterMythsByCharacter((character));

        if (myths.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(myths);
        }
    }

    //FILTRAGE PAR NOM DE THEME
    @GetMapping("/filter/theme")
    public ResponseEntity<List<Myth>> filterMythsByTheme(@RequestParam String theme){
        List<Myth> myths = mythService.filterMythsByTheme((theme));

        if (myths.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.ok(myths);
        }
    }

    @PostMapping
    public ResponseEntity<Myth> createMyth(@RequestBody Myth myth) {
        Myth savedMyth = mythService.createMyth(myth);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMyth);
    }

    @DeleteMapping
    public ResponseEntity<Myth> deleteMyth(@PathVariable Long id){
        mythService.deleteMyth(id);
        return ResponseEntity.noContent().build();
    }

}
