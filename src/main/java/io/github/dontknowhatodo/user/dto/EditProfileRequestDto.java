package io.github.dontknowhatodo.user.dto;

import java.util.Optional;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class EditProfileRequestDto {
    private Optional<String> name;
    private Optional<String> bio;
    private Optional<String> company;
}