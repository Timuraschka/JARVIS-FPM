package edu.fra.uas.token.repository;


import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import edu.fra.uas.token.model.Token;

import java.util.ArrayList;

@Repository
public class TokenRepository {


    public ArrayList<Token> tokenList = new ArrayList<>();

    //Admin creation
    @PostConstruct
    public void init() {



    }

}
