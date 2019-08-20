package com.amdocs.challenge.test.task5;

import java.util.Date;

import com.amdocs.challenge.test.task5.Solution.Comment;

public class Solution {

	static class User {
		private String name;
		private Date loginDate;
		private boolean isLogIn;
		
		public User(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}

		public boolean isLoggedIn() {
			return isLogIn;
		}

		public Date getLastLoggedInAt() {
			return this.loginDate;
		}

		public void logIn() {
			isLogIn = true;
			Date date = new Date();
			this.loginDate = date;
		}

		public void logOut() {
			isLogIn = false;
		}

		public boolean canEdit(Comment user1Comment) {
			String user = user1Comment.getAuthor().name;
			if(name == user){
				return true;
			}
			return false;
		}

		public boolean canDelete(Comment user1Comment) {
			// TODO Auto-generated method stub
			return false;
		}
	}

	static class Moderator extends User {

		private String modName;
		
		public Moderator(String name) {
			super(name);
			this.modName = name;
		}

		@Override
		public boolean canDelete(Comment user1Comment) {
			return true;
		}
		
		@Override
		public boolean canEdit(Comment user1Comment) {
			String user = user1Comment.getAuthor().name;
			if(modName == user){
				return true;
			}
			return false;
		}

	}

	static class Admin extends Moderator {
		private String name;

		public Admin(String name) {
			super(name);
		}
		
		@Override
		public boolean canEdit(Comment user1Comment) {
			return true;
		}

		@Override
		public boolean canDelete(Comment user1Comment) {
			return true;
		}
	}

	static class Comment {

		private User user;
		private Moderator moderator;
		private String message;
		private String author;
		private Date createdAt;
		private Comment comment;
		private boolean replyTo;

		public Comment(User user, String commentMsg) {
			this.user = user;
			this.message = commentMsg;
			Date date = new Date();
			this.createdAt = date;
		}

		public Comment(User user, String commentMsg, Comment comment) {
			this.user = user;
			this.message = commentMsg;
			this.comment = comment;
			Date date = new Date();
			this.createdAt = date;
			this.replyTo = true;
		}

		public Date getCreatedAt() {
			return createdAt;
		}

		public User getAuthor() {
			// TODO Auto-generated method stub
			return user;
		}

		public String getCommentMsg() {
			return message;
		}

		public Comment getRepliedTo() {
			Comment comment1 = null;
			if (comment != null) {
				comment1 = comment;
			}
			return comment1;
		}

		public void setMessage(String message) {
			this.message = message;

		}

		public String getMessage() {
			// TODO Auto-generated method stub
			return message;
		}

		@Override
		public String toString() {
			String msg = this.message + " by " + user.getName();
			if (replyTo) {
				msg = msg + " (replied to " + comment.getAuthor().getName() + ")";
			}

			return msg;
		}
	}

}
