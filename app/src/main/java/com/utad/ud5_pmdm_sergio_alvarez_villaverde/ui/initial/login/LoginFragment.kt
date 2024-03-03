package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.R
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.databinding.FragmentLoginBinding
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.home.HomeActivity
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial.InitialViewModel
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial.signup.SignUpFragment
import kotlinx.coroutines.launch
import java.io.IOException


class LoginFragment : Fragment() {

    private lateinit var _binding: FragmentLoginBinding
    private val binding: FragmentLoginBinding get() = _binding

    private val viewModel: InitialViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAccessLogin.setOnClickListener { loginProcess() }
        binding.btnSignupLogin.setOnClickListener { goToSignUpFragment() }

        observeViewModelStates()
    }

    private fun observeViewModelStates() {
        lifecycleScope.launch {
            viewModel.initialState.collect{ state ->

                /*if (state.userLogged == true){
                    goToHomeActivity()
                }*/
                if(state.emailExists == false){
                    Toast.makeText(requireContext(), "No hay ningún usuario con ese email registrado", Toast.LENGTH_SHORT).show()
                }else if(state.passwordMatch == false){
                    Toast.makeText(requireContext(), "La contraseña no coincide", Toast.LENGTH_SHORT).show()
                }else if(state.emailExists == true && state.passwordMatch == true){
                    Toast.makeText(requireContext(), "Usuario loggeado", Toast.LENGTH_SHORT).show()
                    goToHomeActivity()
                }
            }
        }
    }

    private fun loginProcess() {
        val email = binding.etUserEmailLogin.text.toString().trim()
        val password = binding.etUserPasswordLogin.text.toString().trim()
        if (checkFieldsAreNotEmpty(email, password)){
            viewModel.login(email, password)
        }
        else{
            Toast.makeText(requireContext(), "Los dos campos deben de estar rellenos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkFieldsAreNotEmpty(email: String, password: String): Boolean{

        var credentials = false
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
            credentials = true
        }
        return credentials
    }

    private fun goToSignUpFragment(){

        val signUpFragment = SignUpFragment()

        val transaction = parentFragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.replace(R.id.fcv_initial_activity, signUpFragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    private fun goToHomeActivity() {
        requireActivity().finish()
        val intent = Intent(requireContext(), HomeActivity::class.java)
        startActivity(intent)
    }



}