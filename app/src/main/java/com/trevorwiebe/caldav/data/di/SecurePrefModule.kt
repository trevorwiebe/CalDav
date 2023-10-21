package com.trevorwiebe.caldav.data.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.trevorwiebe.caldav.R
import com.trevorwiebe.caldav.data.auth.SecurePref
import com.trevorwiebe.caldav.data.auth.SecurePrefImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SecurePrefModule {

    @Singleton
    @Provides
    fun provideEncryptionPref(
        app: Application
    ): SharedPreferences {
        val masterKey = MasterKey.Builder(app, MasterKey.DEFAULT_MASTER_KEY_ALIAS)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        return EncryptedSharedPreferences.create(
            app,
            app.getString(R.string.encrypt_user_pref),
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    @Singleton
    @Provides
    fun provideSecurePref(
        encryptedSharedPreferences: SharedPreferences
    ): SecurePref {
        return SecurePrefImpl(encryptedSharedPreferences)
    }

}