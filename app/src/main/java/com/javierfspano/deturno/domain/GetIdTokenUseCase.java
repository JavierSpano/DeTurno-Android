package com.javierfspano.deturno.domain;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.javierfspano.deturno.exception.FirebaseSignInException;
import com.javierfspano.deturno.exception.IdTokenException;
import com.javierfspano.deturno.util.GenericServiceCallback;

public class GetIdTokenUseCase {

    private FirebaseUser user;

    public void execute(GenericServiceCallback<String> callback) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        if (user == null) {
            signIn(firebaseAuth, callback);
            return;
        }
        getTokenId(user, callback);


    }

    private void signIn(FirebaseAuth firebaseAuth, GenericServiceCallback<String> idTokenCallback) {
        firebaseAuth.signInAnonymously()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null && task.getResult().getUser() != null) {
                        user = task.getResult().getUser();
                        getTokenId(user, idTokenCallback);
                    } else {
                        idTokenCallback.onError(new FirebaseSignInException());
                    }
                });
    }


    private void getTokenId(FirebaseUser firebaseUser, GenericServiceCallback<String> idTokenCallback) {
        firebaseUser.getIdToken(true).addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult() != null) {
                idTokenCallback.onSuccess(task.getResult().getToken());
            } else {
                idTokenCallback.onError(new IdTokenException());
            }
        });
    }
}
