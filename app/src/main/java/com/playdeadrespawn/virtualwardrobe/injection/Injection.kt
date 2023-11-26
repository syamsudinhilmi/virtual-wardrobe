package com.playdeadrespawn.virtualwardrobe.injection

import com.playdeadrespawn.virtualwardrobe.data.Repository

object Injection {
    fun provideRepository(): Repository {
        return Repository.getInstance()
    }
}