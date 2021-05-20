package com.kadek.favorites.ui.tv

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadek.moviecatalogue.R
import com.kadek.moviecatalogue.databinding.FragmentFavoriteTvBinding
import com.kadek.favorites.ui.FavoriteViewModel
import com.kadek.core.ui.TvAdapter
import com.kadek.favorites.DaggerFavoriteComponent
import com.kadek.favorites.ViewModelFactory
import com.kadek.moviecatalogue.DetailActivity
import com.kadek.moviecatalogue.di.FavoriteModuleDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private var _favoriteTvBinding: FragmentFavoriteTvBinding? = null
    private val favoriteTvBinding get() = _favoriteTvBinding!!

    private val favoriteViewModel: FavoriteViewModel by viewModels{
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _favoriteTvBinding = FragmentFavoriteTvBinding.inflate(inflater, container, false)
        return favoriteTvBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val tvAdapter = TvAdapter()
            tvAdapter.onItemClick = {
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, it)
                intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_TV)
                startActivity(intent)
            }

            favoriteTvBinding.progressBar.visibility = View.VISIBLE
            favoriteViewModel.favTv.observe(viewLifecycleOwner, {
                tvAdapter.setTv(it)
                favoriteTvBinding.progressBar.visibility = View.GONE
                if (it != null) {
                    favoriteTvBinding.rvTv.adapter = tvAdapter
                    if (it.isNullOrEmpty()) {
                        favoriteTvBinding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, R.string.no_fav_tv, Toast.LENGTH_SHORT).show()
                    }
                }
            })

            with(favoriteTvBinding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerFavoriteComponent.builder()
            .context(context)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    context,
                    FavoriteModuleDependencies::class.java)
            )
            .build()
            .inject1(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _favoriteTvBinding = null
    }
}