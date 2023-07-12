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
import com.example.liveznav.utils.ExtensionUtils.Companion.toggleShowView

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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        binding.progressBarView.toggleShowView(true)
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
            binding.progressBarView.toggleShowView(false)
            setViews(it.name, it.countries)
        }
    }

    private fun setViews(nameTitle: String, list: List<Country>) {
        binding.nameTitle.text = "results for: $nameTitle"
        binding.noNotesView.toggleShowView(list.isEmpty())
        myListAdapter.submitList(list)
    }

    override fun onItemClick(country: Country) {}

}