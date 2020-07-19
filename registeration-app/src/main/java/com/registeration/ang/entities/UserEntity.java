package com.registeration.ang.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.registeration.ang.apiModels.ApiBaseModel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity extends ApiBaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String userId;

	private String username;

	private String email;

	private String encryptedPassword;

	// private constructor
	private UserEntity(UserEntityBuilder builder) {
		this.id = builder.id;
		this.userId = builder.userId;
		this.username = builder.username;
		this.email = builder.email;
		this.encryptedPassword = builder.encryptedPassword;
		setCreatedAt(new Date());
	}

	// Builder inner class
	public static class UserEntityBuilder {
		private long id;
		private String userId;
		private String username;
		private String email;
		private String encryptedPassword;

		public UserEntityBuilder setId(long id) {
			this.id = id;
			return this;
		}

		public UserEntityBuilder setUserId(String userId) {
			this.userId = userId;
			return this;
		}

		public UserEntityBuilder setUsername(String username) {
			this.username = username;
			return this;
		}

		public UserEntityBuilder setEmail(String email) {
			this.email = email;
			return this;
		}

		public UserEntityBuilder setEncryptedPassword(String encryptedPassword) {
			this.encryptedPassword = encryptedPassword;
			return this;
		}

		// build method
		public UserEntity build() {
			return new UserEntity(this);
		}

	}

}
