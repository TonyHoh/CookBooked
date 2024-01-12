package opp.CookBooked.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;
import java.sql.Time;

@Entity
@Table(name = "recept")
@Data
@EqualsAndHashCode
@NoArgsConstructor
public class Recept {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    @Column(name = "idrecept")
    private Long idRecept;

    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_korisnik")
    private Korisnik autor;

    @Getter
    @Setter
    @Column(name = "nazivRecept")
    private String nazivRecept;

    @OneToMany(mappedBy = "recept")
    @JsonIgnore
    private Set<ReceptKategorije> receptKategorije;

    @OneToMany(mappedBy = "recept")
    @JsonIgnore
    private Set<ReceptSastojci> receptSastojci;

    @OneToMany(mappedBy = "recept")
    @JsonIgnore
    private Set<VrsteKuhinjaRecepta> vrsteKuhinjaRecepta;

    @Getter
    @Setter
    @Column(name = "priprema")
    private String Priprema;

    @Getter
    @Setter
    @Column(name = "vrijemeKuhanja")
    private Time vrijemeKuhanja;

    @Getter
    @Setter
    @Column(name = "oznaka")
    private String Oznaka;

    @Getter
    @Setter
    @Column(name = "slikaRecept")
    private String slikaRecept;

    @Getter
    @Setter
    @Column(name = "videoRecept")
    private String videoRecept;

    @Getter
    @Setter
    @Column(name = "vrijemeObjave")
    private LocalDate vrijemeObjave;

    @OneToMany(mappedBy = "recept")
    @JsonIgnore
    private Set<SpremljeniRecepti> korisnici;

    @OneToMany(mappedBy = "recept")
    @JsonIgnore
    private Set<OznacavanjeRecepata> korisniciOzn;

    public Recept(String nazivRecept, Korisnik autor,
                  String priprema, Time vrijemeKuhanja,
                  String oznaka, String slikaRecept, String videoRecept) {
        this.nazivRecept = nazivRecept;
        this.autor = autor;
        this.Priprema = priprema;
        this.vrijemeKuhanja = vrijemeKuhanja;
        this.Oznaka = oznaka;
        this.slikaRecept = slikaRecept;
        this.videoRecept = videoRecept;
    }

    public Recept(Long idRecept, Korisnik autor, String nazivRecept, String priprema) {
        this.idRecept = idRecept;
        this.autor = autor;
        this.nazivRecept = nazivRecept;
        this.Priprema = priprema;
    }

}

