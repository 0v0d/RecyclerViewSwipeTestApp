package com.example.recyclerviewswipetestapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewswipetestapp.databinding.FragmentRecyclerViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecyclerViewFragment : Fragment() {
    private var fragmentRecyclerViewBinding: FragmentRecyclerViewBinding? = null
    private val binding get() = fragmentRecyclerViewBinding!!

    private val listViewAdapter by lazy {
        ListViewAdapter(onItemClick)
    }

    private val onItemClick = { keyword: Fruit ->
        Log.d("TodoListFragment", "onTaskItemClick called with task: $keyword")
        navigateToKeyWord(keyword)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentRecyclerViewBinding =
            FragmentRecyclerViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val keyWordLayoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), keyWordLayoutManager.orientation)
        val fruitList = listOf(
            Fruit("Apple", "リンゴ（林檎、学名: Malus domestica, Malus pumila）とは、バラ科リンゴ属の落葉高木、またはその果実のこと。植物学上ではセイヨウリンゴと呼ぶ。"),
            Fruit("Banana", "バナナ（英: banana）は、バショウ科バショウ属の植物であるバナナの果実のこと。"),
            Fruit("Cherry", "サクランボ（桜桃、学名: Prunus avium）は、バラ科サクランボ属の落葉高木、またはその果実のこと。"),
        )
        listViewAdapter.submitList(fruitList)
        binding.taskRecyclerView.apply {
            layoutManager = keyWordLayoutManager
            addItemDecoration(dividerItemDecoration)
            adapter = listViewAdapter
        }

        val swipeHandler = SwipeToDeleteCallback(requireContext()) { position ->
            showDeleteConfirmationDialog(position)
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.taskRecyclerView)
    }

     private fun showDeleteConfirmationDialog(position: Int) {
        val existingDialog =
            childFragmentManager.findFragmentByTag(
                DeleteConfirmationDialogFragment.TAG
            )
        if (existingDialog != null) {
            listViewAdapter.notifyItemChanged(position)
            return
        }
        val deleteConfirmationDialog = DeleteConfirmationDialogFragment()
        deleteConfirmationDialog.setOnConfirmListener {
            listViewAdapter.notifyItemRemoved(position)
        }

        deleteConfirmationDialog.show(childFragmentManager, DeleteConfirmationDialogFragment.TAG)

        listViewAdapter.notifyItemChanged(position)
    }

    private fun navigateToKeyWord(keyword: Fruit) {
        val action =
            RecyclerViewFragmentDirections.actionRecyclerViewFragmentToKeyWordFragment(keyword)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        binding.taskRecyclerView.adapter = null
        fragmentRecyclerViewBinding = null
        super.onDestroyView()
    }
}