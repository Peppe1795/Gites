package Giuseppe.gestione_dispositivi.utente;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
public class NewUtenteResponsePayload {
	private UUID id;
}
