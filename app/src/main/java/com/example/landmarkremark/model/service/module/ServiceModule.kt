package com.example.landmarkremark.model.service.module

import com.example.landmarkremark.model.service.AccountService
import com.example.landmarkremark.model.service.StorageService
import com.example.landmarkremark.model.service.impl.AccountServiceImpl
import com.example.landmarkremark.model.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService
    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService
}