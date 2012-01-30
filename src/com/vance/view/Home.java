package com.vance.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import com.vance.blog.component.BlogComponent;
import com.vance.blog.evernote.EDAMManager;

public class Home extends BasePage {
	private static EDAMManager edamManager=new EDAMManager();
	private static HashMap<String,String> titleMap;
	static void initEdamManager(){
		edamManager.authenciate("vancezhao@gmail.com", "jsvskk");
		titleMap=edamManager.getNoteTitleLists();
		
	}
	public Home(){
		initEdamManager();
		add(new BlogComponent("blg","blgTitle","This is my first blog......."));
		List<String> titles=new ArrayList<String>();
		Iterator it=titleMap.entrySet().iterator();
		while(it.hasNext()){
			String key=it.next().toString().split("=")[0];
			titles.add(key);
		}
		add(new ListView<Object>("blgTitleLists",titles){
			private static final long serialVersionUID = 1L;
			@Override
			protected void populateItem(final ListItem item) {
				Link<Void> link=new Link<Void>("titleLine"){
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					@Override
					public void onClick() {
						PageParameters parameters=new PageParameters();
						String guid=titleMap.get(item.getModelObject().toString());
						parameters.add("guid", guid);
						setResponsePage(EvernoteBlogPage.class,parameters);
					}

				};
				link.setBody(Model.of(item.getModel()));
				item.add(link);
			}	
		});
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}
