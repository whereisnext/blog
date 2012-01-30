package com.vance.blog.evernote.type;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "enNote"
})
@XmlRootElement(name="enNote")
public class NoteXmlContext {
	@XmlElement(name="enNote")
	private String enNote;

	public String getEnNote() {
		return enNote;
	}

	public void setEnNote(String enNote) {
		this.enNote = enNote;
	}
}
