package com.beksultan.e_trans.ui.authorization;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.beksultan.e_trans.App;
import com.beksultan.e_trans.db.UserPreferences;
import com.beksultan.e_trans.model.user.User;
import com.beksultan.e_trans.model.user.AuthParam;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class AuthorizationPresenter extends MvpPresenter<AuthorizationView> {

    public void signIn(final AuthParam param) {
        App.getInstance().getApiService().authorization(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onSuccess(User user) {
                        if (user != null) {
                            UserPreferences.set(user);
                            checkRole();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onFailedApiService(e.getMessage());
                    }
                });

    }

    public void checkRole() {
        App.getInstance().getApiService().checkRole()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String role) {
                        if (role != null) {
                            getViewState().onOpenUI(role);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().onFailedApiService(e.getMessage());
                    }
                });
    }

}
