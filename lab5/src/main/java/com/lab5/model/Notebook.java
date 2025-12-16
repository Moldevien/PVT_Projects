package com.lab5.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notebook")
public class Notebook {
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int id;
	protected String manufacturer;
	protected String notebookName;
	protected int pages;
	protected String coverType;
	protected String country;
	protected int circulation;
	protected String pageStyle;
	
	public Notebook(String manufacturer, String notebookName, int pages, String coverType, String country, int circulation, String pageStyle) {
		this.manufacturer = manufacturer;
		this.notebookName = notebookName;
		this.pages = pages;
		this.coverType = coverType;
		this.country = country;
		this.circulation = circulation;
		this.pageStyle = pageStyle;
	}
}
