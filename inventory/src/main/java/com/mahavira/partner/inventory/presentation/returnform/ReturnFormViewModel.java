package com.mahavira.partner.inventory.presentation.returnform;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partner.base.core.Resource;
import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.base.entity.Partner;
import com.mahavira.partner.base.presentation.BaseViewModel;
import com.mahavira.partner.inventory.domain.entity.ReturnRequest;
import com.mahavira.partner.inventory.domain.usecase.ReturnGamesUseCase;
import com.mahavira.partner.profile.domain.usecase.GetProfileUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 31/07/18.
 */

public class ReturnFormViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<Boolean>> mReturnRequestData = new MutableLiveData<>();

    private final MutableLiveData<Resource<Partner>> mPartnerData = new MutableLiveData<>();

    private ReturnGamesUseCase mReturnGamesUseCase;

    private GetProfileUseCase mGetProfileUseCase;

    @Inject
    ReturnFormViewModel(ReturnGamesUseCase useCase) {
        mReturnGamesUseCase = useCase;
    }

    void attemptGetProfile(String email) {
        mGetProfileUseCase.execute(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed);
    }

    public void attemptReturnGames(Boardgame product, Partner partner) {
        ReturnRequest req = createReturnRequest(product, partner);
        mReturnGamesUseCase.execute(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubcribe())
                .subscribe(this::onRequestSuccess, this::onRequestFailed);
    }

    private void onRequestFailed(Throwable throwable) {
        mShowLoading.set(true);
        mReturnRequestData.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    private void onRequestSuccess() {
        mShowLoading.set(true);
        mReturnRequestData.setValue(Resource.success(true));
    }

    private void doOnSubcribe() {
        mShowLoading.set(true);
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mPartnerData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    private void onSuccess(Partner partner) {
        mShowLoading.set(false);
        mPartnerData.setValue(Resource.success(partner));
    }

    private ReturnRequest createReturnRequest(Boardgame product, Partner partner) {
        ReturnRequest request = new ReturnRequest();
        request.setProductName(product.getName());
        request.setFrom(partner.getName());
        return null;
    }
}
