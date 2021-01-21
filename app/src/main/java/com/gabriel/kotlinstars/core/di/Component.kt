package com.gabriel.kotlinstars.core.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [Module::class, ViewModelModule::class])
interface Component {


}