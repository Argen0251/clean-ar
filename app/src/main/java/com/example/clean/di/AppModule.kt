package com.example.clean.di

import com.example.clean.di.data.networkModule
import com.example.clean.di.domain.domainModule
import com.example.clean.di.presentation.presentationModule

import org.koin.core.module.Module

val appModule: List<Module> = listOf(
    networkModule,
    domainModule,
    presentationModule
)