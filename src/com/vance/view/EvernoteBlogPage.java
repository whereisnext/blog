package com.vance.view;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.thrift.TException;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.parser.XmlTag;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.evernote.edam.error.EDAMNotFoundException;
import com.evernote.edam.error.EDAMSystemException;
import com.evernote.edam.error.EDAMUserException;
import com.evernote.edam.notestore.NoteStore;
import com.evernote.edam.type.Note;
import com.evernote.edam.type.NoteAttributes;
import com.evernote.edam.type.NoteAttributes._Fields;
import com.evernote.edam.userstore.UserStore;
import com.vance.blog.evernote.EDAMManager;
import com.vance.blog.evernote.type.NoteXmlContext;

public class EvernoteBlogPage extends WebPage {
	private static NoteStore.Client noteStore;
	private static UserStore.Client userStore;
	private static String token;
	private static String ingroe1="<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">";
	private static String ingroe2="";
	
//	public EvernoteBlogPage(PageParameters g) {
//		this.token = EDAMManager.getLastToken();
//		noteStore = EDAMManager.getNoteStore();
//		String guid = g.get("guid").toString();
//		String blgContext;
//		try {
//			Note note = noteStore.getNote(token, guid, true, true, true, true);
//			byte[] bytes = note.getContent().getBytes();
//			// System.out.println(note.getContent());
//			JAXBContext jaxbContext = JAXBContext
//					.newInstance(NoteXmlContext.class);
//			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
//			InputStream inputStream = new ByteArrayInputStream(bytes);
//			Reader reader = new InputStreamReader(inputStream);
//			NoteXmlContext noteXmlContext = (NoteXmlContext) unMarshaller
//					.unmarshal(reader);
//			blgContext = noteXmlContext.getEnNote();
//			// DocumentBuilder builder =
//			// DocumentBuilderFactory.newInstance().newDocumentBuilder();
//			// Document xmlDocument = builder.parse(inputStream);
//			// DOMSource source = new DOMSource(xmlDocument);
//			//
//			// StreamResult result = new StreamResult(System.out);
//			// TransformerFactory tf = TransformerFactory.newInstance();
//			// Transformer transformer = tf.newTransformer();
//			// transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,
//			// "http://xml.evernote.com/pub/enml2.dtd");
//			// transformer.transform(source, result);
//			// blgContext=note.getContent();
//			add(new Label("blgContext", blgContext));
//		} catch (EDAMUserException e) {
//			e.printStackTrace();
//		} catch (EDAMSystemException e) {
//			e.printStackTrace();
//		} catch (EDAMNotFoundException e) {
//			e.printStackTrace();
//		} catch (TException e) {
//			e.printStackTrace();
//			// } catch (JAXBException e) {
//			// // TODO Auto-generated catch block
//			// e.printStackTrace();
//			// }
//		} catch (JAXBException e) {
//			e.printStackTrace();
//		}
//	}
	public EvernoteBlogPage(PageParameters g) {
		this.token = EDAMManager.getLastToken();
		noteStore = EDAMManager.getNoteStore();
		String guid = g.get("guid").toString();
		String blgContext;
		try {
			Note note=noteStore.getNote(token, guid, true, true, true, true);
			blgContext=note.getContent().toString();
			Reader in=new StringReader(blgContext);
			BufferedReader reader=new BufferedReader(in);
			StringBuilder str=new StringBuilder();
			String s;
			while((s=reader.readLine())!=null){
//				if(s.contains("<!DOCTYPE en-note SYSTEM \"http://xml.evernote.com/pub/enml2.dtd\">")||
//						(s.contains("<?xml version=\"1.0\" encoding=\"UTF-8\"?>"))||
//						(s.contains("en-note"))
//						){
//					s=reader.readLine();
//				}else{
//				
//				str.append(s);
//				}
				str.append(s);
			}
			System.out.println(str.toString());
			add(new Label("blgContext",str.toString()).setEscapeModelStrings(false));
			
		} catch (EDAMUserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EDAMSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EDAMNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
