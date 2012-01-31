package com.vance.blog;

import org.apache.wicket.Application;
import org.apache.wicket.Page;
import org.apache.wicket.RuntimeConfigurationType;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.settings.IExceptionSettings;

//import com.vance.blog.exception.ExpiredPage;
import com.vance.blog.exception.MyAccessDeniedPage;
import com.vance.blog.exception.MyExpiredPage;
import com.vance.blog.exception.MyInternalErrorPage;
import com.vance.view.Home;

public class BlogApplication extends WebApplication {

	@Override
	public void init(){
		getApplicationSettings().setPageExpiredErrorPage(MyExpiredPage.class);
		getApplicationSettings().setAccessDeniedPage(MyAccessDeniedPage.class);
		getApplicationSettings().setInternalErrorPage(MyInternalErrorPage.class);
		getExceptionSettings().setUnexpectedExceptionDisplay(IExceptionSettings.SHOW_INTERNAL_ERROR_PAGE); 
	}

	@Override
	public Class<? extends Page> getHomePage() {
		return Home.class;
	}


}
