package Giuseppe.gestione_dispositivi.utente;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UtenteLoginPayload {
	String email;
	String password;
}
