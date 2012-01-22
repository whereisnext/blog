package com.vance.blog;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

import com.vance.view.Home;

public class BlogApplication extends WebApplication {

	

	@Override
	public Class<? extends Page> getHomePage() {
		// TODO Auto-generated method stub
		return Home.class;
	}


}
