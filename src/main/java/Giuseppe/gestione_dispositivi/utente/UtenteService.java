package Giuseppe.gestione_dispositivi.utente;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import Giuseppe.gestione_dispositivi.Exception.BadRequestException;
import Giuseppe.gestione_dispositivi.Exception.NotFoundException;

@Service
public class UtenteService {
	private final UtenteRepository utenteRepo;

	@Autowired
	public UtenteService(UtenteRepository utenteRepo) {
		super();
		this.utenteRepo = utenteRepo;
	}

	public Utente create(UtenteRequestPayload body) {
		utenteRepo.findByEmail(body.getEmail()).ifPresent(utente -> {
			throw new BadRequestException("L'email è già stata utilizzata");
		});
		Utente nuovoUtente = new Utente(body.getNome(), body.getCognome(), body.getEmail(), body.getUsername(),
				body.getPassword());
		return utenteRepo.save(nuovoUtente);
	}

	public Page<Utente> find(int page, int size, String sort) {
		Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
		return utenteRepo.findAll(pageable);
	}

	public Utente findById(UUID id) throws NotFoundException {
		return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
	}

	public Utente findByIdAndUpdate(UUID id, UtenteRequestPayload body) throws NotFoundException {
		Utente found = this.findById(id);
		found.setEmail(body.getEmail());
		found.setNome(body.getNome());
		found.setCognome(body.getCognome());
		found.setUsername(body.getUsername());
		found.setPassword(body.getPassword());

		return utenteRepo.save(found);
	}

	public void findByIdAndDelete(UUID id) throws NotFoundException {
		Utente found = this.findById(id);
		utenteRepo.delete(found);
	}

	public Utente findByEmail(String email) {
		return utenteRepo.findByEmail(email)
				.orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovato"));
	}
}
