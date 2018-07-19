package com.mahavira.partner.login.di;

import com.google.firebase.auth.FirebaseAuth;
import com.mahavira.partner.login.data.LoginRepoImpl;
import com.mahavira.partner.login.domain.repo.LoginRepository;
import com.mahavira.partner.login.domain.usecase.LoginUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 13/07/18.
 *
 */

@Module(includes = {LoginBuilderModule.class})
public class LoginModule {

    @Provides
    @Singleton
    LoginRepository provideLoginRepository(FirebaseAuth auth) {
        return new LoginRepoImpl(auth);
    }

    @Provides
    @Singleton
    LoginUseCase provideLoginUseCase(LoginRepository repository) {
        return new LoginUseCase(repository);
    }
}
