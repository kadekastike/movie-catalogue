package com.kadek.favorites

import android.content.Context
import com.kadek.favorites.ui.movie.FavoriteMovieFragment
import com.kadek.favorites.ui.tv.FavoriteTvFragment
import com.kadek.moviecatalogue.di.FavoriteModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {
    fun inject(activity: FavoriteActivity)
    fun inject1(fragment: FavoriteTvFragment)
    fun inject2(fragment: FavoriteMovieFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context) : Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies) : Builder
        fun build(): FavoriteComponent
    }
}

