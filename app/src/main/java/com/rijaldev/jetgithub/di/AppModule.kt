package com.rijaldev.jetgithub.di

import com.rijaldev.jetgithub.domain.usecase.user.DetailUserInteractor
import com.rijaldev.jetgithub.domain.usecase.user.DetailUserUseCase
import com.rijaldev.jetgithub.domain.usecase.user.ListUserInteractor
import com.rijaldev.jetgithub.domain.usecase.user.ListUserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideListUserUseCase(listUserInteractor: ListUserInteractor): ListUserUseCase

    @Binds
    @Singleton
    abstract fun provideDetailUserUseCase(detailUserUserInteractor: DetailUserInteractor): DetailUserUseCase
}