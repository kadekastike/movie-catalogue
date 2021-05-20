package com.kadek.moviecatalogue.ui.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kadek.core.data.Resource
import com.kadek.core.ui.TvAdapter
import com.kadek.moviecatalogue.DetailActivity
import com.kadek.moviecatalogue.DetailActivity.Companion.EXTRA_TV
import com.kadek.moviecatalogue.databinding.FragmentTvBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_tv.*

@AndroidEntryPoint
class TvFragment : Fragment() {

    private var _fragmentTvBinding: FragmentTvBinding? = null
    private val fragmentTvBinding get() = _fragmentTvBinding!!

    private val tvViewModel: TvViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _fragmentTvBinding = FragmentTvBinding.inflate(inflater, container, false)
        return fragmentTvBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val tvAdapter = TvAdapter()
        tvAdapter.onItemClick = {
            val intent = Intent(activity,DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, it)
            intent.putExtra(DetailActivity.EXTRA_TYPE, EXTRA_TV)
            startActivity(intent)
        }

        fragmentTvBinding.progressBar.visibility = View.VISIBLE
        tvViewModel.tv.observe(viewLifecycleOwner, {
            fragmentTvBinding.progressBar.visibility = View.GONE
            if (it != null) {
                when(it) {
                    is Resource.Loading<*> -> fragmentTvBinding.progressBar.visibility = View.GONE
                    is Resource.Success<*> -> {
                        fragmentTvBinding.progressBar.visibility = View.GONE
                        rv_tv.adapter = tvAdapter
                        tvAdapter.setTv(it.data)
                        tvAdapter.notifyDataSetChanged()
                    }
                    is Resource.Error<*> -> {
                        fragmentTvBinding.progressBar.visibility = View.GONE
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT ).show()
                    }
                }
            }
        })

        fragmentTvBinding.rvTv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentTvBinding = null
    }
}