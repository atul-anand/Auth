package com.experiment.authentication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;

public class Auth0Manager {

    private static final String TAG = Auth0Manager.class.getSimpleName();

    private Auth0 mAuth0;
    private Context mContext;
    private Activity mActivity;

    public Auth0Manager(Context context, Activity activity) {
        this.mContext = context;
        this.mActivity = activity;
        this.mAuth0 = setAuth0();
    }

    private Auth0 setAuth0() {
        Auth0 account = new Auth0(mContext.getString(R.string.com_auth0_client_id), mContext.getString(R.string.com_auth0_domain));
        account.setOIDCConformant(true);
        return account;
    }

    public void login() {
        WebAuthProvider.login(mAuth0)
//                .withAudience(mContext.getString(R.string.com_auth0_audience))
                .start(mActivity, new AuthCallback() {
                    @Override
                    public void onFailure(@NonNull Dialog dialog) {
                        dialog.show();
                    }

                    @Override
                    public void onFailure(AuthenticationException exception) {
                        Log.d(TAG, exception.toString());
                    }

                    @Override
                    public void onSuccess(@NonNull Credentials credentials) {
                        Log.d(TAG, credentials.toString());
                    }
                });
    }
}
