package com.example.shopdeneme.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shopdeneme.databinding.FragmentHomeBinding
import com.example.shopdeneme.viewmodel.BaseViewModel


class HomeFragment : Fragment(),StringListAdapter.ItemClickListener {

    private val stringList = mutableListOf(
        "Technology" ,
        "Men's Clothing" ,
        "Jewelery",
        "Women's Clothing"
    )


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val adapter = HomeAdapter()
    private val viewModel by lazy { BaseViewModel(requireContext()) }
    private val secondAdapter = AllDataAdapter()






    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        val recyclerView = binding.recyclerView
        val layoutM = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = StringListAdapter(stringList, this) // StringListAdapter'ı oluştururken, tıklanma olayını burada yakalayacağız
        recyclerView.layoutManager = layoutM

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.myrecyclerview.adapter = adapter
        viewModel.top10products.observe(viewLifecycleOwner) { products ->
            adapter.setdata(products)
        }

        binding.secondrec.adapter = secondAdapter
        viewModel.products.observe(viewLifecycleOwner){allproducts->
            secondAdapter.setdata(allproducts)
        }
        binding.searchBarLayout.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToSearchFragment2()
            findNavController().navigate(action)
        }


    }

    override fun onItemClicked(category: String) {
        val filteredProducts = viewModel.products.value?.filter { it.category == category } ?: listOf()
        secondAdapter.setdata(filteredProducts)
    }






  /*  fun getData() = with(binding){

        viewModel.products.observe(viewLifecycleOwner){products->
            adapter.setdata(products)
        }
    }
    */

}