package com.mahavira.partner.profile.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partner.profile.data.ProfileRepoImpl;
import com.mahavira.partner.profile.domain.repo.ProfileRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 23/07/18.
 *
 */

@Module(includes = {ProfileBuilderModule.class})
public class ProfileModule {

    @Provides
    ProfileRepository provideProductRepository(FirebaseFirestore firestore) {
        return new ProfileRepoImpl(firestore);
    }
}
