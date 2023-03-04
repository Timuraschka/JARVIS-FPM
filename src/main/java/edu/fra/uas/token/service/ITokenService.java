package edu.fra.uas.token.service;

import java.util.UUID;

import edu.fra.uas.token.model.Token;

public interface ITokenService {

    public Token createToken(long userID);

    public void deleteToken(UUID userToken);

    public Boolean checkIfTokenExistsAndIsValid(UUID userToken);


}
