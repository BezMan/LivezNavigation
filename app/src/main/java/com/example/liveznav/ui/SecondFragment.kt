package com.example.liveznav.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.liveznav.data.Country
import com.example.liveznav.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), MyListAdapter.OnItemClickListener {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var myListAdapter: MyListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        observeResponse()


        viewModel.fetchData("nameText")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun initUI() {

        //RECYCLER
        binding.recView.layoutManager = LinearLayoutManager(requireActivity())
        binding.recView.setHasFixedSize(true)
        myListAdapter = MyListAdapter(this)
        binding.recView.adapter = myListAdapter

    }


    private fun observeResponse() {
        viewModel.listState.observe(viewLifecycleOwner) {
            val list = it.list
            displayData(list)
        }
    }

    private fun displayData(list: List<Country>) {
        myListAdapter.submitList(list)
        //no notes layout
//        binding.noNotesView.toggleShowView(list.isEmpty())

    }

    override fun onItemClick(country: Country) {}

}