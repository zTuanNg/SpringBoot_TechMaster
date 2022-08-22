package com.example.Day16.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequest {

    @NotEmpty(message = "Old password is required")
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @NotEmpty(message = "New password is required")
    @NotBlank(message = "New password is required")
    @Length(min = 3, message = "Độ dài ít nhất 4 ký tự")
    private String newPassword;
}
