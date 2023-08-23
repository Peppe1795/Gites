package Giuseppe.gestione_dispositivi.dispositivi;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DispositiviRepository extends JpaRepository<Dispositivi, UUID> {

}
