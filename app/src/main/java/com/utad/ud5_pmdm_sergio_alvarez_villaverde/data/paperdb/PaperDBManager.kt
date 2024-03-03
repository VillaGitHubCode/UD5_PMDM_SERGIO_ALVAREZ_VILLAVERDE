/*package com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.paperdb

import io.paperdb.Paper

data class UserState(var isLogged: Boolean, val key: String)

class PaperDBManager {

    fun setUserLogged(isLogged: Boolean, key:String){
        Paper.book("user_state").write(UserState, UserState(isLogged))
    }

    fun getUserState(key: String): Boolean {
        val userState: UserState? = Paper.book("user_state").read(key)

        return userState.isLogged
    }
}*/