package Giuseppe.gestione_dispositivi.utente;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import jakarta.persistence.AttributeConverter;

@Component
public class UtenteConverter implements AttributeConverter<String, String> {

	private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
	private static final String secret = "mysup3rs3cr3tttt";

	@Override
	public String convertToDatabaseColumn(String coverter) {
		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");
			Cipher c = Cipher.getInstance(ALGORITHM);

			c.init(Cipher.ENCRYPT_MODE, key);

			return Base64.getEncoder().encodeToString(c.doFinal(coverter.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		try {
			Key key = new SecretKeySpec(secret.getBytes(), "AES");

			Cipher c = Cipher.getInstance(ALGORITHM);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(dbData)));
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

}
