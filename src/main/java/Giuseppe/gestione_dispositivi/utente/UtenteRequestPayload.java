package Giuseppe.gestione_dispositivi.utente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UtenteRequestPayload {
	private String cognome;
	private String email;
	private String nome;
	private String password;
	private String username;

}
