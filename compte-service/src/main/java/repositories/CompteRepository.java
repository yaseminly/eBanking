package repositories;

import  org.sid.compteservice.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes")
public interface CompteRepository extends JpaRepository<Compte, Long> {
    List<Compte> findByTitulaireContainingIgnoreCase(String name);
}