package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Comment;
import forms.CommentForm;

import services.CommentService;

@Controller
@RequestMapping("/comment")
public class UnregisteredCommentController extends AbstractController{
	@Autowired
	private CommentService commentService;
	@RequestMapping(value="/listByFlower", method = RequestMethod.GET)
	public ModelAndView listByFlower(@RequestParam int flowerId) {
		ModelAndView result;
		String requestUri="comment/listByFlower.do?flowerId="+flowerId;	
		String returnURI="flower/list.do";	
		
		Collection<Comment> comments=commentService.findByFlower(flowerId);
		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", requestUri);
		result.addObject("comments", comments);
		result.addObject("returnURI", returnURI);
		return result;
	}
	
	@RequestMapping(value="/listReplies", method = RequestMethod.GET)
	public ModelAndView listReplies(@RequestParam int commentId) {
		ModelAndView result;
		String requestUri="comment/listReplies.do?commentId="+commentId;
		String returnURI;
		Comment comment=commentService.findOne(commentId);
		if(comment.getParent()==null){
			returnURI="comment/listByFlower.do?flowerId="+comment.getFlower().getId();
		}else{
			returnURI="comment/listReplies.do?commentId="+comment.getParent().getId();
		}	
		Collection<Comment> comments=commentService.findResponses(commentId);
		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", requestUri);
		result.addObject("comments", comments);
		result.addObject("returnURI", returnURI);
		return result;
	}
	//Post a comment
	@RequestMapping(value="/post", method = RequestMethod.GET)
	public ModelAndView post(@RequestParam int flowerId) {
		ModelAndView result;	
		
		CommentForm  commentForm=commentService.construct(flowerId);
		
		result = createEditModelAndView(commentForm);
		return result;
	}

	@RequestMapping(value="/post", method = RequestMethod.POST,params="save")
	public ModelAndView save(@Valid CommentForm commentForm,BindingResult binding) {
		ModelAndView result;
		Assert.notNull(commentForm);
		if(binding.hasErrors()){
			result=createEditModelAndView(commentForm);
		} else{
			try{
				String returnURI;
				Comment comment=commentService.reconstruct(commentForm);
				returnURI="comment/listByFlower.do?flowerId="+comment.getFlower().getId();
				commentService.save(comment);
				result=new ModelAndView("redirect:../"+returnURI);
			} catch(Throwable oops){
				result=createEditModelAndView(commentForm,"request.commit.error");
			}
		}
		return result;
	}
	//Reply a comment
	@RequestMapping(value="/reply", method = RequestMethod.GET)
	public ModelAndView reply(@RequestParam int commentId) {
		ModelAndView result;	
		
		CommentForm  commentForm=commentService.prepareReply(commentId);
		
		result = createEditModelAndViewReply(commentForm);
		return result;
	}

	@RequestMapping(value="/reply", method = RequestMethod.POST,params="save")
	public ModelAndView saveReply(@Valid CommentForm commentForm,BindingResult binding) {
		ModelAndView result;
		Assert.notNull(commentForm);
		if(binding.hasErrors()){
			result=createEditModelAndViewReply(commentForm);
		} else{
			try{
				String returnURI;
				Comment comment=commentService.reconstruct(commentForm);
				returnURI="comment/listReplies.do?commentId="+comment.getParent().getId();
				commentService.save(comment);
				result=new ModelAndView("redirect:../"+returnURI);
			} catch(Throwable oops){
				result=createEditModelAndViewReply(commentForm,"request.commit.error");
			}
		}
		return result;
	}	
	// Ancillary methods post
	protected ModelAndView createEditModelAndView(CommentForm commentForm){
		ModelAndView result;
		result=createEditModelAndView(commentForm,null);		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(CommentForm commentForm, String message){
		ModelAndView result;
		String returnURI="flower/list.do";	

		String requestUri="comment/post.do?flowerId="+commentForm.getFlower();	
		
		result=new ModelAndView("comment/post");
		result.addObject("commentForm", commentForm);
		result.addObject("message", message);
		result.addObject("requestUri", requestUri);
		result.addObject("returnURI", returnURI);
		return result;
	}
	
	// Ancillary methods reply
	protected ModelAndView createEditModelAndViewReply(CommentForm commentForm){
		ModelAndView result;
		result=createEditModelAndViewReply(commentForm,null);		
		return result;
	}
	
	protected ModelAndView createEditModelAndViewReply(CommentForm commentForm, String message){
		ModelAndView result;
		String returnURI;
		Comment comment=commentService.findOne(commentForm.getParent());
		if(comment.getParent()==null){
			returnURI="comment/listByFlower.do?flowerId="+comment.getFlower().getId();
		}else{
			returnURI="comment/listReplies.do?commentId="+comment.getParent().getId();
		}
		String requestUri="comment/reply.do?commentId="+commentForm.getParent();	
		
		result=new ModelAndView("comment/reply");
		result.addObject("commentForm", commentForm);
		result.addObject("message", message);
		result.addObject("requestUri", requestUri);
		result.addObject("returnURI", returnURI);
		return result;
	}
	
}
