package Giuseppe.gestione_dispositivi.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginSuccessfulPayload {
	String accessToken;
}
