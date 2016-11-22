package es.llamalox.sgpc.dao.usuarios;

import java.util.Date;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.llamalox.sgpc.dao.GenericDaoImpl;
import es.llamalox.sgpc.model.usuarios.PersistentLogin;

@Repository("tokenRepositoryDao")
@Transactional
public class TokenDaoImpl extends GenericDaoImpl<String, PersistentLogin>
		implements PersistentTokenRepository {

	static final Logger LOGGER = LoggerFactory.getLogger(TokenDaoImpl.class);

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		LOGGER.info("Creando Token para el usuario : {}", token.getUsername());
		PersistentLogin persistentLogin = new PersistentLogin();
		persistentLogin.setUsername(token.getUsername());
		persistentLogin.setSeries(token.getSeries());
		persistentLogin.setToken(token.getTokenValue());
		persistentLogin.setLastUsed(token.getDate());
		save(persistentLogin);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		LOGGER.info("Importar Token si existe para el seriesId : {}", seriesId);
		try {
			Criteria crit = createEntityCriteria();
			crit.add(Restrictions.eq("series", seriesId));
			PersistentLogin persistentLogin = (PersistentLogin) crit
					.uniqueResult();

			return new PersistentRememberMeToken(persistentLogin.getUsername(),
					persistentLogin.getSeries(), persistentLogin.getToken(),
					persistentLogin.getLastUsed());
		} catch (Exception e) {
			LOGGER.info("Token no encontrado...");
			return null;
		}
	}

	@Override
	public void removeUserTokens(String username) {
		LOGGER.info("Borrando el Token si existe para el usuario : {}",
				username);
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		PersistentLogin persistentLogin = (PersistentLogin) crit.uniqueResult();
		if (persistentLogin != null) {
			LOGGER.info("se ha seleccionado la opción de recordar");
			delete(persistentLogin);
		}

	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		LOGGER.info("Actualizando el Token para el seriesId : {}", seriesId);
		PersistentLogin persistentLogin = getByKey(seriesId);
		persistentLogin.setToken(tokenValue);
		persistentLogin.setLastUsed(lastUsed);
		update(persistentLogin);
	}

}
