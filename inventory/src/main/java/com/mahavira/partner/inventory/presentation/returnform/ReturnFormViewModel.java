package com.mahavira.partner.inventory.presentation.returnform;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partner.base.core.Resource;
import com.mahavira.partner.base.entity.Boardgame;
import com.mahavira.partner.base.entity.Partner;
import com.mahavira.partner.base.presentation.BaseViewModel;
import com.mahavira.partner.inventory.domain.entity.ReturnRequest;
import com.mahavira.partner.inventory.domain.usecase.ReturnGamesUseCase;
import com.mahavira.partner.profile.domain.usecase.GetLoggedProfileUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 31/07/18.
 */

public class ReturnFormViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean(false);

    private final MutableLiveData<Resource<Boolean>> mReturnRequestData = new MutableLiveData<>();

    private final MutableLiveData<Resource<Partner>> mPartnerData = new MutableLiveData<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private ReturnGamesUseCase mReturnGamesUseCase;

    private GetLoggedProfileUseCase mGetProfileUseCase;

    @Inject
    ReturnFormViewModel(ReturnGamesUseCase useCase, GetLoggedProfileUseCase getProfileUseCase) {
        mReturnGamesUseCase = useCase;
        mGetProfileUseCase = getProfileUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Resource<Boolean>> getReturnRequestData() {
        return mReturnRequestData;
    }

    public MutableLiveData<Resource<Partner>> getPartnerData() {
        return mPartnerData;
    }

    void attemptGetProfile() {
        mDisposable.add(mGetProfileUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onSuccess, this::onFailed));
    }

    private void onSuccess(Partner partner) {
        mShowLoading.set(false);
        mPartnerData.setValue(Resource.success(partner));
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mPartnerData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    public void attemptReturnGames(Boardgame product, Partner partner) {
        ReturnRequest req = createReturnRequest(product, partner);
        mDisposable.add(mReturnGamesUseCase.execute(req)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .subscribe(this::onRequestSuccess, this::onRequestFailed));
    }

    private void doOnSubscribe() {
//        mShowLoading.set(true);
        mReturnRequestData.setValue(Resource.loading(null));
    }

    private void onRequestFailed(Throwable throwable) {
        mShowLoading.set(false);
        mReturnRequestData.setValue(Resource.error(null, throwable.getLocalizedMessage(), false));
    }

    private void onRequestSuccess() {
        mShowLoading.set(false);
        mReturnRequestData.setValue(Resource.success(true));
    }

    private ReturnRequest createReturnRequest(Boardgame product, Partner partner) {
        ReturnRequest request = new ReturnRequest();
        request.setProductName(product.getName());
        request.setFrom(partner.getName());
        return request;
    }
}
