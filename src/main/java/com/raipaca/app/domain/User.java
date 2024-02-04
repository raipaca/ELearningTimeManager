package com.raipaca.app.domain;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class User {

	private Integer id;

	@NotBlank
	private String name;

	@NotBlank
	private String loginId;

	@NotBlank
	private String loginPass;

	private Integer typeId;

	private Date created;

	private String status;

}
