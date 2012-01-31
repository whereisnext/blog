package com.vance.blog.exception;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;

import com.vance.view.BasePage;
import com.vance.view.Home;

public class MyExpiredPage extends BasePage {
	public MyExpiredPage(){
		add(new Label("errorCode","Expired"));
		add(new AjaxLink("home"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(Home.class);
			}
			
		});
	}
}
