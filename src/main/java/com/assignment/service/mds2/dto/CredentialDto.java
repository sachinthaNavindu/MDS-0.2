package com.assignment.service.mds2.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CredentialDto {
    private String username;
    private String adminId;
    private String password;
    private String recoverEmail;
}
