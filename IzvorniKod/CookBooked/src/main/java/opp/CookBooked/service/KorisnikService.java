package opp.CookBooked.service;

import opp.CookBooked.controller.RegisterDTO;
import opp.CookBooked.model.Korisnik;

import java.util.List;
import java.util.Optional;

public interface KorisnikService {
    List<Korisnik> listAll();

    Korisnik createKorisnik(String korisnickoIme, String lozinkaKorisnik, String emailKorisnik);

    Optional<Korisnik> findByKorisnickoIme(String korisnickoIme);

    Optional<Korisnik> findByIdKorisnik(long iDKorisnik);

    Korisnik fetch(long iDKorisnik) throws Throwable;

    Korisnik deleteKorisnik(long iDKorisnik);
}
