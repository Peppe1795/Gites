package Giuseppe.gestione_dispositivi.utente;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import Giuseppe.gestione_dispositivi.dispositivi.Dispositivi;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({ "password", "accountNonExpired", "authorities", "credentialsNonExpired", "accountNonLocked" })
public class Utente implements UserDetails {
	@Id
	@GeneratedValue
	private UUID id;
	@Column(nullable = false)
	@Convert(converter = UtenteConverter.class)
	private String cognome;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	@Convert(converter = UtenteConverter.class)
	private String nome;

	private String password;
	@Column(nullable = false, unique = true)
	private String username;
	@Enumerated(EnumType.STRING)
	private Role role;

	@OneToMany(mappedBy = "utente")
	private List<Dispositivi> dispositiviAssegnati;

	public Utente(String username, String nome, String cognome, String email, String password) {
		this.username = username;
		this.cognome = cognome;
		this.email = email;
		this.nome = nome;
		this.password = password;
		this.role = role.USER;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	public boolean isAccountNonLocked() {

		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	public boolean isEnabled() {

		return true;
	}

	@Override
	public String getUsername() {

		return this.email;
	}

}
