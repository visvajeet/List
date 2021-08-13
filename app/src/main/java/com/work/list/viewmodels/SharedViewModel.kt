package com.work.list.viewmodels

import androidx.lifecycle.*
import com.work.list.data.network.Api
import com.work.list.data.network.Resource
import com.work.list.data.network.Status
import com.work.list.models.ItemsResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class SharedViewModel : ViewModel() {

    private val apiService by lazy { Api.service }

    private val _items = MutableLiveData<Resource<ItemsResponse>>()
    val items: LiveData<Resource<ItemsResponse>> = _items

    val isLoading = Transformations.map(items) {
        when (it.status) {
            Status.ERROR -> false
            Status.LOADING -> true
            Status.SUCCESS -> false
        }
    }

    init {
        Timber.d("SharedViewModel : Created")
    }

    override fun onCleared() {
        super.onCleared()
        Timber.d("SharedViewModel : Cleared")
    }


    private fun getData() {
        _items.postValue(Resource.loading(data = null))
        viewModelScope.launch {
            try {
                _items.postValue((Resource.success(data = apiService.getItems())))
            } catch (exception: Exception) {
                _items.postValue(
                    Resource.error(
                        data = null,
                        message = exception.message ?: "Error Occurred!"
                    )
                )
            }
        }
    }

    fun refreshData() {
        getData()
    }

}