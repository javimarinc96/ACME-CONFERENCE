
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import domain.DomainEntity;

@Component
@Transactional
public class StringToEntity<S extends DomainEntity, T extends JpaRepository<S, Integer>> implements Converter<String, S> {

	@Autowired(required = false)
	private T	repository;


	@Override
	public S convert(final String source) {
		S res = null;
		int id;
		try {
			if (!StringUtils.isEmpty(source)) {
				id = Integer.valueOf(source);
				res = this.repository.findOne(id);
			}
		} catch (final Throwable t) {
			throw new IllegalArgumentException(t);
		}
		return res;
	}

}
