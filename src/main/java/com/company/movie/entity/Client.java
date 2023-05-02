package com.company.movie.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@MappedSuperclass
@Getter
@Setter
public abstract class Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String name;
	private String surname;
	private String experience;
	private String specialty;
	private String university;

	public Client(int id, String name, String surname, String university, String specialty, String experience) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.university = university;
		this.specialty = specialty;
		this.experience = experience;
	}
}
