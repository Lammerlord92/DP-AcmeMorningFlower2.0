package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Comment;
import domain.Flower;
import forms.CommentForm;

@Transactional
@Service
public class CommentService {

	// Managed repository -----------------------------------------
	@Autowired
	private CommentRepository commentRepository;
	// Supporting services ----------------------------------------
	@Autowired
	private FlowerService flowerService;

	// Constructor ------------------------------------------------
	// Simple CRUD methods ----------------------------------------
	public Comment create(int flowerId) {
		Flower flower = flowerService.findOne(flowerId);
		Comment result = new Comment();
		result.setFlower(flower);
		Collection<Comment> comments = new ArrayList<Comment>();
		result.setResponses(comments);
		Date date=new Date();
		long milisec=date.getTime()-1;
		result.setCreationMoment(new Date(milisec));
		return result;
	}

	//
	// public Collection<Administrator> findAll(){
	// Collection<Administrator> result = administratorRepository.findAll();
	// return result;
	// }

	public Comment findOne(int commentId) {
		Comment result = commentRepository.findOne(commentId);
		return result;
	}

	public void save(Comment comment) {
		commentRepository.saveAndFlush(comment);
	}

	//
	// public void delete(Administrator administrator){
	// administratorRepository.delete(administrator);
	// }

	// Other business methods -------------------------------------
	// List the comments regarding a flower
	public Collection<Comment> findByFlower(int flowerId) {
		Collection<Comment> result = commentRepository.findByFlower(flowerId);
		return result;
	}
	//// List the reply of a comment
	public Collection<Comment> findResponses(int commentId) {
		Collection<Comment> result = commentRepository.findResponses(commentId);
		return result;
	}
	// Prepare reply
	public CommentForm prepareReply(Integer commentId) {
		CommentForm result = new CommentForm();
		Comment comment = findOne(commentId);
		result.setFlower(comment.getFlower().getId());
		result.setNick("Anonymous");
		result.setParent(commentId);

		return result;
	}

	// Prepare comment
	public CommentForm construct(int flowerId) {
		CommentForm result = new CommentForm();
		result.setFlower(flowerId);
		result.setNick("Anonymous");
		return result;
	}

	public Comment reconstruct(CommentForm commentForm) {
		Comment result;
		result = create(commentForm.getFlower());
		result.setNick(commentForm.getNick());
		result.setText(commentForm.getText());
		result.setUrl(commentForm.getUrl());
		if (commentForm.getParent() != null) {
			Comment comment = findOne(commentForm.getParent());
			result.setParent(comment);
		}

		return result;
	}

	public void isAdmin() {
		UserAccount account = LoginService.getPrincipal();
		Collection<Authority> authorities = account.getAuthorities();
		Boolean res = false;
		for (Authority a : authorities) {
			if (a.getAuthority().equals("ADMIN"))
				res = true;
		}
		Assert.isTrue(res);
	}
}
