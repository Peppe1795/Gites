package Giuseppe.gestione_dispositivi.dispositivi;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Giuseppe.gestione_dispositivi.Exception.NotFoundException;

@Service
public class DispositiviService {
	private final DispositiviRepository dispositiviRepo;

	@Autowired
	private DispositiviService(DispositiviRepository dispositiviRepo) {
		super();
		this.dispositiviRepo = dispositiviRepo;
	}

	public Dispositivi create(DispositiviRequestPayload body) {

		Dispositivi nuovoDispositivo = new Dispositivi(body.getStatoDispositivo(), body.getTipoDispositivo());
		return dispositiviRepo.save(nuovoDispositivo);
	}

	public Page<Dispositivi> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return dispositiviRepo.findAll(pageable);
	}

	public Dispositivi findById(UUID id) throws NotFoundException {
		return dispositiviRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Dispositivi findByIdAndUpdate(UUID id, DispositiviRequestPayload body) throws NotFoundException {
		Dispositivi found = this.findById(id);
		found.setStatoDispositivo(body.getStatoDispositivo());
		found.setTipoDispositivo(body.getTipoDispositivo());
		return dispositiviRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Dispositivi found = this.findById(id);
		dispositiviRepo.delete(found);
	}

	public Dispositivi saveDispositivo(Dispositivi dispositivo) {
		return dispositiviRepo.save(dispositivo);
	}
}
