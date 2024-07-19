package me.androidbox.authentication.data.login.repository.remote.imp

import co.touchlab.kermit.Logger
import com.google.firebase.auth.FirebaseAuth
import me.androidbox.authentication.data.login.repository.remote.UserLoginRegisterRemoteDataSource
import me.androidbox.authentication.domain.utils.CheckResult
import me.androidbox.authentication.domain.utils.DataError
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class UserLoginRegisterRemoteDataSourceImp(private val firebaseAuth: FirebaseAuth) :
    UserLoginRegisterRemoteDataSource {

    override suspend fun registerUser(email: String, password: String): CheckResult<String, Unit, Unit> {
        firebaseAuth.currentUser?.let {
            Logger.d {
                "User is already registered ${firebaseAuth.currentUser?.uid}"
            }
            return CheckResult.Success(it.uid)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Logger.d("User has been created ${firebaseAuth.currentUser?.uid}")
                        continuation.resume(CheckResult.Success(firebaseAuth.currentUser?.uid ?: ""))
                    }
                    else {
                        Logger.d("Error when creating ${firebaseAuth.currentUser?.uid}")
                        continuation.resume(CheckResult.Failure(exceptionError = DataError.Network.UNAUTHORIZED))
                    }
                }
        }
    }

    override suspend fun loginUser(email: String, password: String): CheckResult<String, Unit, Unit> {
        firebaseAuth.currentUser?.let {
            Logger.d("User is already logged in ${firebaseAuth.currentUser?.uid}")
            return CheckResult.Success(it.uid)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Logger.d("User has been logged in [$email, $password] ${firebaseAuth.currentUser?.uid}")
                        continuation.resume(CheckResult.Success(firebaseAuth.currentUser?.uid ?: ""))
                    }
                    else {
                        Logger.e("Error when logging in ${task.exception?.message}")
                        continuation.resume(CheckResult.Failure(exceptionError = DataError.Network.UNAUTHORIZED))
                    }
                }
        }
    }

    override suspend fun logout(): CheckResult<Unit, Unit, Unit> {
        if(firebaseAuth.currentUser == null) {
            return CheckResult.Success(Unit)
        }

        return suspendCoroutine { continuation ->
            firebaseAuth.signOut()
            continuation.resume(CheckResult.Success(Unit))
        }
    }

    override suspend fun isLoggedIn(): CheckResult<Boolean, Unit, Unit> {
        return CheckResult.Success(firebaseAuth.currentUser == null)
    }
}