package com.example.cld.songchurchapplication.ui.signIn

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseUser
import android.content.Intent
import android.util.Log
import com.example.cld.songchurchapplication.App
import com.example.cld.songchurchapplication.R
import com.example.cld.songchurchapplication.ui.churchSelect.ChurchSelectActivity
import com.google.firebase.auth.GoogleAuthProvider
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.activity_signin.*
import javax.inject.Inject


class SignInActivity : AppCompatActivity() {

    private val TAG = "authScreen"
    private val RC_SIGN_IN = 9001

    @Inject
    lateinit var mAuth: FirebaseAuth

    private var mGoogleSignInClient: GoogleSignInClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)

        (application as App).dataComponent.inject(this)

        // Button listeners
        sign_in_button.setOnClickListener { signIn() }

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        //mAuth = FirebaseAuth.getInstance()
    }

    public override fun onStart() {
        super.onStart()

        // Check if user is signed in
        val currentUser = mAuth!!.currentUser
        updateUserInfo(currentUser)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.e(TAG, "Google sign in failed", e)
                //updateUserInfo(null)
            }

        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = mAuth!!.currentUser
                        with( getSharedPreferences(getString(R.string.shared_preferences_key), Context.MODE_PRIVATE)
                                .edit()){
                            putString(getString(R.string.user_id_key),user?.uid)
                            putString(getString(R.string.user_displayName_key),user?.displayName)
                            putString(getString(R.string.user_email_key),user?.email)
                            putString(getString(R.string.user_phoneNumber_key),user?.phoneNumber)
                            putString(getString(R.string.user_imageUrl_key),user?.photoUrl.toString())
                            apply()
                        }
                        startActivity(Intent(this,ChurchSelectActivity::class.java))
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.e(TAG, "signInWithCredential:failure", task.exception)
                    }
                }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

   /* private fun signOut() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google sign out
        mGoogleSignInClient!!.signOut().addOnCompleteListener(this
        ) { updateUserInfo(null) }
    }*/

    /*private fun revokeAccess() {
        // Firebase sign out
        mAuth!!.signOut()

        // Google revoke access
        mGoogleSignInClient!!.revokeAccess().addOnCompleteListener(this
        ) { updateUserInfo(null) }
    }*/

    private fun updateUserInfo(user: FirebaseUser?) {
        if (user != null) {
            Log.e(TAG, user.email)
            Log.e(TAG, user.uid)
            startActivity(Intent(this,ChurchSelectActivity::class.java))
            finish()
        }
    }

}

