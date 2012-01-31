package com.vance.blog.exception;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;

import com.vance.view.BasePage;
import com.vance.view.Home;

public class MyAccessDeniedPage extends BasePage {
	public MyAccessDeniedPage(){
		add(new Label("errorCode","Access Denied "));
		add(new AjaxLink("home"){

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(Home.class);
			}
			
		});
	}
}
