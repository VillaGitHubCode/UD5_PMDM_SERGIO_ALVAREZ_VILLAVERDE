package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial

import androidx.lifecycle.ViewModel
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.firebase.RealTimeDatabaseManager
//import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.paperdb.PaperDBManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class InitialUIState(
    val emailExists: Boolean?,
    val registration: Boolean?,
    val passwordMatch: Boolean?,
    var userLogged: Boolean
)
class InitialViewModel: ViewModel() {


    private var _initialState: MutableStateFlow<InitialUIState> = MutableStateFlow(InitialUIState(null, null, null, false))
    val initialState: StateFlow<InitialUIState> get() = _initialState

    private val realTimeDatabaseManager = RealTimeDatabaseManager()

    //private val paperDBManager = PaperDBManager()

    fun signUp(email: String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val state = realTimeDatabaseManager.addUser(email, password)

            when(state){
                1 -> _initialState.update { it.copy(registration = true, emailExists = false) }
                2 -> _initialState.update { it.copy(emailExists = true, registration = false) }
                3 -> _initialState.update { it.copy(registration = false) }
                else -> Log.i("State", "Opción no reconocida")
            }
        }
    }

    fun login(email: String, password:String){
        viewModelScope.launch(Dispatchers.IO) {
            val state = realTimeDatabaseManager.loginUser(email, password)

            when(state){
                1 -> _initialState.update { it.copy(emailExists = false) }
                2 -> _initialState.update { it.copy(emailExists = true, passwordMatch = false) }
                3 -> _initialState.update { it.copy(emailExists = true, passwordMatch = true) }
                else -> Log.i("State", "Opción no reconocida")
            }
        }
    }

 /*   fun setUserLogged(isLogged: Boolean, key: String){
        viewModelScope.launch(Dispatchers.IO) {
            paperDBManager.setUserLogged(isLogged, key)
        }
    }

    fun getUserLogged(){
        viewModelScope.launch(Dispatchers.IO) {
            val state = paperDBManager.getUserState()

            if (state){
                _initialState.update { it.copy(userLogged = true)}
            }else{
                _initialState.update { it.copy(userLogged = false)}
            }
        }
    }*/

}

