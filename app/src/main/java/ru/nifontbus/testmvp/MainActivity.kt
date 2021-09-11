package ru.nifontbus.testmvp

import android.os.Bundle
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import ru.nifontbus.testmvp.databinding.ActivityMainBinding

class MainActivity : MvpAppCompatActivity(), MainView {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private val presenter by moxyPresenter { MainPresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCounter1.setOnClickListener { presenter.counterClick1() }
        binding.btnCounter2.setOnClickListener { presenter.counterClick2() }
        binding.btnCounter3.setOnClickListener { presenter.counterClick3() }

        initView()
    }

    private fun initView() {
        setButtonText1("0")
        setButtonText2("0")
        setButtonText3("0")
    }

    override fun setButtonText1(text: String) {
        binding.btnCounter1.text = text
    }

    override fun setButtonText2(text: String) {
        binding.btnCounter2.text = text
    }

    override fun setButtonText3(text: String) {
        binding.btnCounter3.text = text
    }
}