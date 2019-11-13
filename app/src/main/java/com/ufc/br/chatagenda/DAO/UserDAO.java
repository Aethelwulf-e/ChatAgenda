package com.ufc.br.chatagenda.DAO;

import com.ufc.br.chatagenda.Model.User;

import java.util.List;

public interface UserDAO {

    User create( User user );
    List<User> readAll();
    User update( User user );
    void delete( User user );

}
