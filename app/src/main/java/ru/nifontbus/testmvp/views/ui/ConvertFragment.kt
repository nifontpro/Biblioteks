package ru.nifontbus.testmvp.views.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.databinding.ConvertFragmentBinding
import ru.nifontbus.testmvp.presentation.ConvertPresenter
import ru.nifontbus.testmvp.views.BackButtonListener
import ru.nifontbus.testmvp.views.ConvertView


class ConvertFragment : MvpAppCompatFragment(), ConvertView,
    BackButtonListener {

    private val presenter by moxyPresenter { ConvertPresenter() }

    private var _binding: ConvertFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ConvertFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            presenter.convert()
        }
    }

    override fun showInputImage(bitmap: Bitmap) {
        binding.imageViewBitmap.setImageBitmap(bitmap)
    }

    override fun showOutputImage(bitmap: Bitmap) {
        binding.imageViewFile.setImageBitmap(bitmap)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun backPressed(): Boolean {
        return presenter.backPressed()
    }

    companion object {
        fun newInstance() = ConvertFragment()
    }
}