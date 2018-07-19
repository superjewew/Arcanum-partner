package com.mahavira.partner.login.domain.usecase;

import com.google.firebase.auth.AuthResult;
import com.mahavira.partner.base.core.BaseUseCaseWithParam;
import com.mahavira.partner.login.domain.entity.AuthParam;
import com.mahavira.partner.login.domain.repo.LoginRepository;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by norman on 13/07/18.
 *
 */

public class LoginUseCase implements BaseUseCaseWithParam<AuthParam, AuthResult> {

    private LoginRepository mRepo;

    @Inject
    public LoginUseCase(LoginRepository repository) {
        mRepo = repository;
    }

    @Override
    public Single<AuthResult> execute(AuthParam param) {
        return mRepo.login(param.getEmail(), param.getPassword());
    }
}
