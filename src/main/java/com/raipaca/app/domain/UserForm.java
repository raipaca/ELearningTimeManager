package com.raipaca.app.domain;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserForm {

	private Integer id;

	@Size(max = 10)
	private String name;

	@NotBlank
	@Size(max = 10)
	private String loginId;

	@NotBlank
	@Size(max = 10)
	private String loginPass;

	private Integer typeId;

	private Date created;

	private Date updated;

	private String status;

	@Size(max = 10)
	private String changeLoginPass;

	@Size(max = 10)
	private String checkLoginPass;

}
