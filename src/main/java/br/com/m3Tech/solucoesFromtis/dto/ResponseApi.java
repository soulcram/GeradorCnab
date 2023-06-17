package br.com.m3Tech.solucoesFromtis.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApi {
    private String Message;
    private String ErrorCode;
    private Long LiqdcId;

}
