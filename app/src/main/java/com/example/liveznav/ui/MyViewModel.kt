package com.example.liveznav.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.liveznav.data.ImplFactory
import com.example.liveznav.data.CountriesPerName
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    private val _listState = MutableLiveData<CountriesPerName>()
    val listState: LiveData<CountriesPerName>
        get() = _listState


    fun fetchData(nameText: String) {
        viewModelScope.launch(dispatcher) {
            val items = ImplFactory.impl.getItems(nameText)
            _listState.postValue(items)
        }
    }


}