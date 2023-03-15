package com.example.shopdeneme.fragments.productItem

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shopdeneme.R
import com.example.shopdeneme.databinding.FragmentProductsItemBinding
import com.example.shopdeneme.util.setPicture


class ProductsItemFragment : Fragment() {

    private var _binding : FragmentProductsItemBinding? = null
    private val binding get() = _binding!!
    private val args: ProductsItemFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProductsItemBinding.inflate(inflater,container,false)
        binding.imgBack.setOnClickListener {
            val action = ProductsItemFragmentDirections.actionProductsItemFragmentToSearchFragment()
            findNavController().navigate(action)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val product = args.products
        binding.txtProductName.text = product.title
        binding.txtProductDescription.text = product.description
        binding.txtProductPrice.text = product.price.toString()
        binding.ratingBar.rating = product.rating.rate.toFloat()
        binding.imgProduct.setPicture(product.image)

    }

}