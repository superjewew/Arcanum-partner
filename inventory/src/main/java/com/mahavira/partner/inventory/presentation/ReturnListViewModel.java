package com.mahavira.partner.inventory.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partner.base.core.Resource;
import com.mahavira.partner.base.presentation.BaseViewModel;
import com.mahavira.partner.inventory.domain.entity.Boardgame;
import com.mahavira.partner.inventory.domain.usecase.GetCurrentBorrowedGamesUseCase;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 27/07/18.
 *
 */

public class ReturnListViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<List<Boardgame>>> mBorrowedGamesData = new MutableLiveData<>();

    private final MutableLiveData<Boardgame> mProductClickedEvent = new MutableLiveData<>();

    private GetCurrentBorrowedGamesUseCase mUseCase;

    @Inject
    ReturnListViewModel(GetCurrentBorrowedGamesUseCase useCase) {
        mUseCase = useCase;
    }

    MutableLiveData<Resource<List<Boardgame>>> getBorrowedGamesData() {
        return mBorrowedGamesData;
    }

    MutableLiveData<Boardgame> getProductClickedEvent() {
        return mProductClickedEvent;
    }

    void attemptGetBorrowed(String email) {
        mUseCase.execute(email).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void doOnSubscribe() {
        mShowLoading.set(true);
    }

    private void onSuccess(List<Boardgame> boardgames) {
        mShowLoading.set(false);
        mBorrowedGamesData.setValue(Resource.success(boardgames));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mBorrowedGamesData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }
}
