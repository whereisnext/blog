package com.vance.view;

import org.apache.wicket.markup.html.WebPage;

import com.vance.blog.component.BlogComponent;

public class Home extends BasePage {

	public Home(){
		add(new BlogComponent("blg","blgTitle","This is my first blog"));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
