package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network.DealApi
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.network.model.Deal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


data class HomeUIState(
    val isLoading: Boolean,
    val dealList: List<Deal>?,
    val error: String?
)
class HomeViewModel: ViewModel() {

    private var _homeState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState(false, null, null))
    val homeState: StateFlow<HomeUIState> get() = _homeState

    fun getDealsList(storeID: String){
        _homeState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch(Dispatchers.IO) {
            val response = DealApi.service.getDealsByStoreID(storeID)

            if(response.isSuccessful && !response.body().isNullOrEmpty()){
                _homeState.update {
                    it.copy(dealList = response.body(), isLoading = false, error = null)
                }
            }else{
                _homeState.update {
                    it.copy(isLoading = false, error = "Error:${response.code()}")
                }
            }
        }
    }
}