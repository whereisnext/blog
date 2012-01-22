package com.vance.blog.component;

import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public class BlogComponent extends Panel implements IHeaderContributor{
	private static final long serialVersionUID = 1L;
	public BlogComponent(String id,String blogTitle,String blogContext) {	
		super(id);
		super.setMarkupId(id);
		super.setOutputMarkupId(true);
		add(new Label("blgTitle",blogTitle));
		add(new Label("blgContext",blogContext));
			
	}
	@Override	
	public void renderHead(IHeaderResponse headerResponse){
		/*
		 * do something here...
		 * e.g: add javascripts:
		 *  headerResponse.renderJavaScriptReference(url);
		 * */
	}
	

}
