package com.api.quiz.dto;

import com.api.quiz.models.*;
import java.io.Serializable;

public class PartidaInfoDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String id;
    
    private String token;
    

    public PartidaInfoDTO() {
    }

    public PartidaInfoDTO(Partida obj) {
        this.id = obj.getId().toString();
        this.token = obj.getToken();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}