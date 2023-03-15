package com.example.shopdeneme.fragments.search

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.shopdeneme.data.Products
import com.example.shopdeneme.databinding.FragmentSearchBinding
import com.example.shopdeneme.fragments.home.AllDataAdapter
import com.example.shopdeneme.fragments.home.HomeFragmentDirections
import com.example.shopdeneme.fragments.search.SearchAdapter
import com.example.shopdeneme.viewmodel.BaseViewModel

class SearchFragment() : Fragment() ,SearchAdapter.OnItemClickListener{

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel by lazy { BaseViewModel(requireContext()) }
    private val adapter by lazy { SearchAdapter(this) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.searchRecyclerView.adapter = adapter
        viewModel.products.observe(viewLifecycleOwner){
            adapter.setData(it)
        }



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchView = binding.searchBar
        searchView.isIconified = false


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
        })

        viewModel.products.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }

    }

    override fun onProductClick(products: Products) {
        val action = SearchFragmentDirections.actionSearchFragmentToProductsItemFragment(products)

        findNavController().navigate(action)
    }

}
