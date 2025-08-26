package com.joaquin.Shop.domain.usecase;

import com.joaquin.Shop.domain.model.User;

public interface UserManagementUseCase {
    User createUser(String username, String password);
}
