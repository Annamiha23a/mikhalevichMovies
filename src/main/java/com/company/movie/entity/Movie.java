package com.company.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "movie")
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name="title")
	private String title;
	@Column(name="genre")
	private String genre;
	@Column(name="country")
	private String country;
	@Column(name="director")
	private String director;
	@Column(name="description")
	private String description;

	@OneToMany(mappedBy = "movie")
	@JsonIgnore
	private List<Mark> marks;

	public Movie(int id, String title, String genre, String country,
				 String director, String description, List<Mark> marks) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.country = country;
		this.director = director;
		this.description = description;
		this.marks = marks;
	}
}
