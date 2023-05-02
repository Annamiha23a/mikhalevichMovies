package com.company.movie.dto;

import lombok.*;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ProductDTO {
	private int id;
	private String script;
	private int rating;
	private int duration;
	private String comment;
	private String critic;
	private String movie;
}
