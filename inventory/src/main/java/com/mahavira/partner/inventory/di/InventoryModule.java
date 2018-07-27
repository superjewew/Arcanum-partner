package com.mahavira.partner.inventory.di;

import com.google.firebase.firestore.FirebaseFirestore;
import com.mahavira.partner.inventory.data.InventoryRepoImpl;
import com.mahavira.partner.inventory.domain.repo.InventoryRepository;
import com.mahavira.partner.inventory.domain.usecase.GetCurrentBorrowedGamesUseCase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by norman on 27/07/18.
 *
 */

@Module(includes = {InventoryBuilderModule.class})
public class InventoryModule {

    @Provides
    @Singleton
    InventoryRepository provideInventoryRouter(FirebaseFirestore firestore) {
        return new InventoryRepoImpl(firestore);
    }

    @Provides
    GetCurrentBorrowedGamesUseCase provideGetBorrowedUseCase(InventoryRepository repository) {
        return new GetCurrentBorrowedGamesUseCase(repository);
    }
}
