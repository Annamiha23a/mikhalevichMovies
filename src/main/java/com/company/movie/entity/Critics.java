package com.company.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "critics")
public class Critics extends Client{
	@OneToMany(mappedBy = "critics")
	@JsonIgnore
	private List<Mark> marks;

	@Builder
	public Critics(int id, String name, String surname, String specialty, String university,
				   String experience) {
		super(id, name, surname, specialty, university, experience);
	}
}
