package com.mahavira.partner.profile.di;

import android.content.Context;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partner.profile.data.ProfileLoggedRepositoryImpl;
import com.mahavira.partner.profile.data.ProfileRepoImpl;
import com.mahavira.partner.profile.domain.repo.ProfileLoggedRepository;
import com.mahavira.partner.profile.domain.repo.ProfileRepository;
import com.mahavira.partner.profile.domain.usecase.GetLoggedProfileUseCase;
import com.mahavira.partner.profile.domain.usecase.GetProfileUseCase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 23/07/18.
 *
 */

@Module(includes = {ProfileBuilderModule.class})
public class ProfileModule {

    @Provides
    ProfileRepository provideProfileRepository(FirebaseFirestore firestore) {
        return new ProfileRepoImpl(firestore);
    }

    @Provides
    ProfileLoggedRepository provideProfileLoggedRepository(Context context) {
        return new ProfileLoggedRepositoryImpl(context);
    }

    @Provides
    GetProfileUseCase provideGetProfileUseCase(ProfileRepository repository) {
        return new GetProfileUseCase(repository);
    }

    @Provides
    GetLoggedProfileUseCase provideGetLoggedProfileUseCase(
            ProfileLoggedRepository profileLoggedRepository, ProfileRepository profileRepository) {
        return new GetLoggedProfileUseCase(profileLoggedRepository, profileRepository);
    }
}
