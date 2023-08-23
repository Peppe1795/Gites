package Giuseppe.gestione_dispositivi.dispositivi;

import java.util.UUID;

import Giuseppe.gestione_dispositivi.utente.Utente;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Dispositivi {
	@Id
	@GeneratedValue
	private UUID id;
	@Enumerated(EnumType.STRING)
	private StatoDispositivo statoDispositivo;
	@Enumerated(EnumType.STRING)
	private TipoDispositivo tipoDispositivo;
	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	public Dispositivi(StatoDispositivo statoDispositivo, TipoDispositivo tipoDispositivo) {
		this.statoDispositivo = statoDispositivo;
		this.tipoDispositivo = tipoDispositivo;
	}

}
