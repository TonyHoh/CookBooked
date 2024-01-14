package opp.CookBooked.controller;

import opp.CookBooked.dto.ProfilDTO;
import opp.CookBooked.model.Korisnik;
import opp.CookBooked.model.Recept;
import opp.CookBooked.service.KorisnikService;
import opp.CookBooked.service.PratiociService;
import opp.CookBooked.service.ReceptService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/korisnici")
public class KorisnikController {

    @Autowired
    private KorisnikService korisnikService;

    @Autowired
    private PratiociService pratiociService;

    @GetMapping("")
    public List<Korisnik> listKorisnik(){
        return korisnikService.listAll();
    }

    @GetMapping("/id/{id}")
    public Korisnik getKorisnikById(@RequestHeader("Authorization") String jwt, @PathVariable("id") long iDKorisnik) throws Throwable {
        return korisnikService.fetch(iDKorisnik);
    }

    @GetMapping("/profile")
    public ProfilDTO getKorisnikByKorisnickoIme(@RequestHeader("Authorization") String jwt) throws Throwable {
        return korisnikService.fetchProfil(jwt);
    }

    @PutMapping("/update/{korisnickoIme}")
    public Korisnik updateKorisnik(@RequestHeader("Authorization") String jwt, @RequestBody Korisnik korisnik) throws Throwable {
        Korisnik kr = korisnikService.getKorisnikFromJWT(jwt);
        return korisnikService.updateKorisnik(kr.getIdKorisnik(), korisnik);
    }

    @PostMapping("/{followerId}/follow/{followingId}")
    public ResponseEntity<String> followUser(
            @RequestHeader("Authorization") String jwt,
            @PathVariable long followerId,
            @PathVariable long followingId) {

        Korisnik k1 = korisnikService.findByIdKorisnik(followerId);
        Korisnik k2 = korisnikService.findByIdKorisnik(followingId);

        pratiociService.followUser(k1, k2);

        return new ResponseEntity<>("Uspješno", HttpStatus.OK);
    }

    @DeleteMapping("/delete/korisnik/{idKorisnik}")
    public ResponseEntity<String> deleteKorisnik(
            @RequestHeader("Authorization") String jwt,
            @PathVariable long idKorisnik) throws Exception {
        Korisnik korisnik = korisnikService.deleteKorisnik(idKorisnik);
        return new ResponseEntity<>("Uspješno obrisan", HttpStatus.OK);
    }
}