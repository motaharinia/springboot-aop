package com.motaharinia.aop.business;

import com.motaharinia.aop.presentation.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private HashMap<Integer, UserModel> userDatabaseMap = new HashMap<>();

    @Override
    public UserModel create(UserModel userModel) {
        Optional<Integer> pk = this.userDatabaseMap.keySet().stream().reduce(Integer::max);
        if (pk.isPresent()) {
            userModel.setId(pk.get() + 1);
        } else {
            userModel.setId(1);
        }
        this.userDatabaseMap.put(userModel.getId(), userModel);
        return this.userDatabaseMap.get(userModel.getId());
    }

    @Override
    public UserModel findOne(Integer id) {
        if (this.userDatabaseMap.containsKey(id)) {
            UserModel userModel = this.userDatabaseMap.get(id);
            return userModel;
        } else {
            return null;
        }
    }



    @Override
    public List<UserModel> findAll() {
        return this.userDatabaseMap.values().stream().collect(Collectors.toList());
    }


    @Override
    public UserModel update(UserModel userModel) {
        if (this.userDatabaseMap.containsKey(userModel.getId())) {
            this.userDatabaseMap.remove(userModel.getId());
            this.userDatabaseMap.put(userModel.getId(), userModel);
            return this.userDatabaseMap.get(userModel.getId());
        } else {
            return null;
        }
    }


    @Override
    public UserModel delete(Integer id) {
        if (this.userDatabaseMap.containsKey(id)) {
            UserModel userModel = this.userDatabaseMap.get(id);
            this.userDatabaseMap.remove(id);
            return userModel;
        } else {
            return null;
        }
    }
}
