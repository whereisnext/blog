package com.vance.blog.evernote;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.LRUMap;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.joda.time.DateTime;

import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteFilter;
import com.evernote.edam.notestore.NoteList;
import com.evernote.edam.notestore.NoteStore;
import com.evernote.edam.type.Note;
import com.evernote.edam.userstore.AuthenticationResult;
import com.evernote.edam.userstore.Constants;
import com.evernote.edam.userstore.UserStore;

public class EDAMManager {
	private static UserStore.Client userStore;
	private static NoteStore.Client noteStore;
	private String username;
	private String password;
	private static Map<String,Long> tokenPool=new LRUMap();
	private static String authToken;
	private final static String consumerKey="vancezhao-1804";
	private final static String consumerSecret = "128e537e7df1d25a";
	private final static String evernoteHost="www.evernote.com";
	private static final String userStoreUrl = "https://" + evernoteHost
			+ "/edam/user";
	private static final String noteStoreUrlBase = "https://" + evernoteHost
			+ "/edam/note/";
	private static final String userAgent = "Evernote/EDAMDemo (Java) "
			+ Constants.EDAM_VERSION_MAJOR + "." + Constants.EDAM_VERSION_MINOR;
	
	public EDAMManager(){

		THttpClient userStoreTrans;
		try {
			userStoreTrans = new THttpClient(userStoreUrl);
//			String userAgent="";
			userStoreTrans.setCustomHeader("User-Agent", userAgent);
			TBinaryProtocol userStoreProt = new TBinaryProtocol(userStoreTrans);
			userStore = new UserStore.Client(userStoreProt, userStoreProt);
		} catch (TTransportException e) {
			e.printStackTrace();
		}
	}
	
	public boolean authenciate(String userName,String pw){
		this.username=userName;
		this.password=pw;
		AuthenticationResult authResult;
		try {
			authResult = userStore.authenticate(username, password, consumerKey, consumerSecret);
			this.authToken=authResult.getAuthenticationToken();
			tokenPool.put(authResult.getAuthenticationToken(),authResult.getExpiration());
			initialNoteStore();
			return true;
		} catch (EDAMUserException e) {
			e.printStackTrace();
		} catch (EDAMSystemException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public HashMap<String,String> getNoteTitleLists() {
		//Set the Map<Ttitle,Note_Guid> here
		HashMap<String,String> titleMap=new HashMap<String,String>();
		String query="tag:blog";
		NoteFilter filter=new NoteFilter();
		filter.setWords(query);
		filter.setAscendingIsSet(true);
		try {
			NoteList noteLists=noteStore.findNotes(getLastToken(), filter, 0, 100);
			List<Note> notes=noteLists.getNotes();
			for(Note note:notes){
				System.out.println(note.getGuid());
				String guid=note.getGuid();
				String title=note.getTitle();
				titleMap.put(title,guid);
			}
			return titleMap;
		} catch (EDAMUserException e) {
			e.printStackTrace();
		} catch (EDAMSystemException e) {
			e.printStackTrace();
		} catch (EDAMNotFoundException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		return null;
	}
	private static void initialNoteStore() {
		THttpClient thttpClient;
		try {
			String shardId=userStore.getUser(getLastToken()).getShardId();;
			String noteStoreUrl = noteStoreUrlBase + shardId;
			thttpClient = new THttpClient(noteStoreUrl);
			thttpClient.setCustomHeader("User-Agent", userAgent);
			TBinaryProtocol tp=new TBinaryProtocol(thttpClient);
			noteStore=new NoteStore.Client(tp,tp);
			
		} catch (TTransportException e) {
			e.printStackTrace();
		} catch (EDAMUserException e) {
			e.printStackTrace();
		} catch (EDAMSystemException e) {
			e.printStackTrace();
		} catch (TException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static NoteStore.Client getNoteStore(){
		if(noteStore==null){
			initialNoteStore();
		}
		return noteStore;
	}

	public static void main(String[] args){
		EDAMManager edamManager=new EDAMManager();
		if(edamManager.authenciate("vancezhao", "jsvskk")){
			edamManager.getNoteTitleLists();
		}
		
	}
	
	public static String getLastToken() {
		Long tokenExpiration=tokenPool.values().iterator().next();
		Date date=new Date();
		System.out.println("Current Time is: "+date.getTime());
		boolean needRefreshToken;
		if(tokenExpiration.compareTo(date.getTime())<0){
			needRefreshToken=true;
		}else{
			needRefreshToken=false;
		}
		if(needRefreshToken){
			try {
				authToken=userStore.refreshAuthentication(authToken).getAuthenticationToken();
			} catch (EDAMUserException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EDAMSystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return authToken;
	}
}
