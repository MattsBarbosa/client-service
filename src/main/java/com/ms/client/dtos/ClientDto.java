package com.ms.client.dtos;

import com.ms.client.models.Address;
import jakarta.validation.constraints.NotNull;

public record ClientDto (@NotNull String firstName,
                         @NotNull String lastName,
                         @NotNull String cpf,
                         @NotNull String email,
                         @NotNull String phone,
                         @NotNull Address address) {
}
