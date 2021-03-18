package com.codecta.emailservice.dto;


import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class EmailResponse {
    private String email;
    private String score;
    public String content;
}
