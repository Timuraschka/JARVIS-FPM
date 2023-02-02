package edu.fra.uas.token;

import java.util.UUID;

public interface ITokenService {

    public Token createToken(long userID);

    public void deleteToken(UUID userToken);

    public Boolean checkIfTokenExistsAndIsValid(UUID userToken);


}
