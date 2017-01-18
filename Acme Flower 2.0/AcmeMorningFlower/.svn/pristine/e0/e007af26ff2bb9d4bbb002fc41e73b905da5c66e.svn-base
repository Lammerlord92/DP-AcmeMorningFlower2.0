package services;

import java.util.Collection;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;


import repositories.AdditionalCostRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;
import domain.AdditionalCost;
import domain.Administrator;
import domain.Comment;
import domain.CreditCard;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CommentServiceTest extends AbstractTest{
	@Autowired
	private CommentService commentService;
		//----------------------------------------------------
		// POSITIVE TEST CASES
		//----------------------------------------------------
//	A user who is not authenticated must be able to:
//		o List the comments regarding a flower.
		@Test
		public void testListComments(){
			
			Collection<Comment> comments=commentService.findByFlower(10);
			Assert.isTrue(comments.size()==2);
		}
//	A user who is not authenticated must be able to:
//		o Post a comment regarding a flower.
		@Test
		public void testPostComment(){
			
			Comment comment=commentService.create(10);
			comment.setText("adsasdasdasdasd");
			commentService.save(comment);
		}
//	A user who is not authenticated must be able to:
//		o Reply to a comment regarding a flower.
		@Test
		public void testReplyComment(){
			Comment parent=commentService.findOne(26);
			Comment comment=commentService.create(parent.getFlower().getId());
			comment.setParent(parent);
			comment.setText("adsasdasdasdasd");
			commentService.save(comment);
		}	
		
		//----------------------------------------------------
		// NEGATIVE TEST CASES
		//----------------------------------------------------

		//A user who is authenticated as an administrator must be able to:
		//	Update the information regarding handling and shipping costs and taxes.
		// We will try to update as an unauthenticate user
//		A user who is not authenticated must be able to:
//		o List the comments regarding a flower.
//		Trying with a non-flower id
		@Test(expected=IllegalArgumentException.class)
		public void testListCommentsNegative(){
			
			Collection<Comment> comments=commentService.findByFlower(26);
			Assert.isTrue(comments.size()==2);
		}
//	A user who is not authenticated must be able to:
//		o Post a comment regarding a flower.
//		Trying with a blank text
		@Test(expected=ConstraintViolationException.class)
		public void testPostCommentNegative(){
			
			Comment comment=commentService.create(10);
			comment.setText("");
			commentService.save(comment);
		}
//	A user who is not authenticated must be able to:
//		o Reply to a comment regarding a flower.
//		Trying with a blank text
		@Test(expected=ConstraintViolationException.class)
		public void testReplyCommentNegative(){
			Comment parent=commentService.findOne(26);
			Comment comment=commentService.create(parent.getFlower().getId());
			comment.setParent(parent);
			comment.setText("");
			commentService.save(comment);
		}	
		
}
