package com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.firebase

import android.util.Log
import com.google.firebase.database.FirebaseDatabase
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.data.firebase.model.User
import kotlinx.coroutines.tasks.await

class RealTimeDatabaseManager {

    private val databaseReference = FirebaseDatabase.getInstance().reference

    suspend fun addUser(email: String, password: String): Int {
        val connection = databaseReference.child("users")
        val key = connection.push().key

        if (key != null) {
            if(checkEmail(email)==null){
                val user = User(key, email, password)
                connection.child(email.replace(".", "_dot_")).setValue(user).await()
                Log.d("addUser", "guardado el usuario $email")
                return 1
            }else{
                Log.e("addUser", "Ya hay un usuario con ese correo")
                return 2
            }
        } else {
            Log.e("addUser", "fallo al guardar el usuario $email")
            return 3
        }
    }

    suspend fun checkEmail(email: String): User? {
        val connection = databaseReference.child("users")
        val snapshot = connection.get()
        snapshot.await()

        snapshot.result.children.mapNotNull { dataSnapshot ->
            val user = dataSnapshot.getValue(User::class.java)
            Log.i("User", "$user")
            if (user != null && user.email == email) {
                return user
            }
        }
        return null
    }

    suspend fun loginUser(email: String, password: String): Int {
        val user = checkEmail(email)
        if(user==null){
            Log.d("loginUser", "$email no se encuentra registrado")
            return 1
        }else if (user.password!=password){
            Log.d("loginUser", "La contraseña no es la correcta")
            return 2
        }
        return 3
    }

    fun deleteUser(user: User){
        val connection = databaseReference.child("users")
        connection.child(user.email).removeValue()
        Log.i("deleteUser", "Usuario borrado")
    }


}