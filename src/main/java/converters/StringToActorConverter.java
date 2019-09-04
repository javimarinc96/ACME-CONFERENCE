
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ActorRepository;
import repositories.AdministratorRepository;
import repositories.AuthorRepository;
import repositories.ReviewerRepository;

import domain.Actor;

@Component
@Transactional
public class StringToActorConverter implements Converter<String, Actor> {

	@Autowired
	ActorRepository			actorRepository;

	@Autowired
	AdministratorRepository	administratorRepository;

	@Autowired
	ReviewerRepository		reviewerRepository;

	@Autowired
	AuthorRepository		authorRepository;


	@Override
	public Actor convert(final String text) {
		Actor result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = this.administratorRepository.findOne(id);
				if (result == null)
					result = this.reviewerRepository.findOne(id);
				if (result == null)
					result = this.authorRepository.findOne(id);
			}
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
