package com.company.movie.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "mark")
@Data
@NoArgsConstructor
@Getter
@Setter
public class Mark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "script")
	private String script;
	@Column(name = "rating")
	private int rating;
	@Column(name = "duration")
	private int duration;
	@Column(name = "comment")
	private String comment;
	@ManyToOne
	private Critics critics;
	@ManyToOne
	private Movie movie;


	@Builder
	public Mark(int id, String comment, int rating, int duration, String script,
				Critics critics, Movie movie) {
		this.id = id;
		this.comment = comment;
		this.rating = rating;
		this.duration = duration;
		this.script = script;
		this.critics = critics;
		this.movie = movie;
	}
}
