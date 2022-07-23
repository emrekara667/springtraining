package com.etech.springtraining.app.beanutils.dto;

import com.etech.springtraining.app.beanutils.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserDto {

    public UserDto(int id,String surname,String name) {
        this.id = id;
        this.surname = surname;
        this.name = name;
    }

    private int id;
    private String surname;
    private String name;


    public UserDto(User user){
        if(user == null){
            return;
        }
        BeanUtils.copyProperties(user, this);
    }


    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
