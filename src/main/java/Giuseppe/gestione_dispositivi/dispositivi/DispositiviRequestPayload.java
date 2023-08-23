package Giuseppe.gestione_dispositivi.dispositivi;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DispositiviRequestPayload {
	private StatoDispositivo statoDispositivo;
	private TipoDispositivo tipoDispositivo;
}
