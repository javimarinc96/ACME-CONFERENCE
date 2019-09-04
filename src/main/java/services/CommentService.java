
package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Actor;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	// Managed repository

	@Autowired
	private CommentRepository	commentRepository;

	@Autowired
	private ActorService		actorService;


	// Simple CRUD methods

	public Comment create() {
		Comment result;
		result = new Comment();
		result.setMoment(new Date());

		final Actor logueado = this.actorService.findByPrincipal();

		if (logueado == null)
			result.setAuthor("Unknown");
		else
			result.setAuthor(logueado.getName());

		return result;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		
		//Asi comprobamos que, al guardar cualquier comment que extienda a commentable, a pesar de tenr
		//como opcional cada relacion ,obligue a que el comentario se este guardando estan asignado 
		//a , al menos, una de las entidades con las que se relaciona commentable, se ampliara si 
		//se comentan mas entidades y se relacionan con commentable en versiones futuras
		
		Boolean estaAsociadoEntidad = true;
		
		if(comment.getActivity() == null && comment.getConference() == null){
			estaAsociadoEntidad = false;
		}
		Assert.isTrue(estaAsociadoEntidad);
		
		Comment result;
		result = this.commentRepository.save(comment);
		return result;
	}

	public void delete(final Comment comment) {
		Assert.notNull(comment);

		this.commentRepository.delete(comment);
	}

	public Collection<Comment> findAll() {
		Collection<Comment> result;

		result = this.commentRepository.findAll();

		return result;
	}

	public Comment findOne(final int commentId) {
		Comment result;

		result = this.commentRepository.findOne(commentId);
		Assert.notNull(result);

		return result;
	}

	public void flush() {
		this.commentRepository.flush();
	}

	// Other business methods

	public Collection<Comment> findByConference(final int conferenceId) {
		return this.commentRepository.findByConference(conferenceId);
	}

	public Collection<Comment> findByActivity(final int activityId) {
		return this.commentRepository.findByActivity(activityId);
	}

}
