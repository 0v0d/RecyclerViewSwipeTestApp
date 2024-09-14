package com.example.recyclerviewswipetestapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.recyclerviewswipetestapp.databinding.FragmentKeyWordBinding

class KeyWordFragment : Fragment() {
    private var fragmentKeyWordBinding: FragmentKeyWordBinding? = null
    private val binding get() = fragmentKeyWordBinding!!

    private val args: KeyWordFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentKeyWordBinding = FragmentKeyWordBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.keyword = args.keyword
    }

    override fun onDestroyView() {
        fragmentKeyWordBinding = null
        super.onDestroyView()
    }
}