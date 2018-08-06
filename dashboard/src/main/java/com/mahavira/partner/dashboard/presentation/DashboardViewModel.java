package com.mahavira.partner.dashboard.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

import com.mahavira.partner.base.core.Resource;
import com.mahavira.partner.base.entity.Partner;
import com.mahavira.partner.base.presentation.BaseViewModel;
import com.mahavira.partner.profile.domain.usecase.GetProfileUseCase;
import com.mahavira.partner.profile.domain.usecase.SetLoggedProfileUseCase;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by norman on 23/07/18.
 *
 */

public class DashboardViewModel extends BaseViewModel {

    public final ObservableBoolean mShowLoading = new ObservableBoolean();

    private final MutableLiveData<Resource<Partner>> mPartnerData = new MutableLiveData<>();

    private final CompositeDisposable mDisposable = new CompositeDisposable();

    private GetProfileUseCase mGetProfileUseCase;

    private SetLoggedProfileUseCase mSetLoggedProfileUseCase;

    @Inject
    DashboardViewModel(GetProfileUseCase getProfileUseCase, SetLoggedProfileUseCase setLoggedProfileUseCase) {
        mGetProfileUseCase = getProfileUseCase;
        mSetLoggedProfileUseCase = setLoggedProfileUseCase;
    }

    @Override
    protected void onCleared() {
        mDisposable.clear();
    }

    public MutableLiveData<Resource<Partner>> getPartnerData() {
        return mPartnerData;
    }

    void attemptGetProfile(String email) {
        mGetProfileUseCase.execute(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(__ -> doOnSubscribe())
                .subscribe(this::onSuccess, this::onFailed);
    }

    private void onFailed(Throwable throwable) {
        mShowLoading.set(false);
        mPartnerData.setValue(Resource.error(null, throwable.getLocalizedMessage(), null));
    }

    private void onSuccess(Partner partner) {
        mShowLoading.set(false);
        mPartnerData.setValue(Resource.success(partner));
    }

    private void doOnSubscribe() {
        mShowLoading.set(true);
    }

    void attemptSaveLoggedProfile(String email) {
        try {
            mSetLoggedProfileUseCase.execute(email)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onSaveSuccess, this::onFailed);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onSaveSuccess() {

    }
}
