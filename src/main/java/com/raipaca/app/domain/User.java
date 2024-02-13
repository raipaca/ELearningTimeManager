package com.raipaca.app.domain;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class User {

	private Integer id;

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

}
