package com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial.signup

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
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.databinding.FragmentSignUpBinding
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial.InitialViewModel
import com.utad.ud5_pmdm_sergio_alvarez_villaverde.ui.initial.login.LoginFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SignUpFragment : Fragment() {

    private lateinit var _binding: FragmentSignUpBinding
    private val binding: FragmentSignUpBinding get() = _binding

    private val viewModel: InitialViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModelStates()

        binding.btnSignupSignup.setOnClickListener {
            signUpProcess()
        }
    }

    private fun signUpProcess() {
        val email = binding.etUserEmailSignup.text.toString().trim()
        val password = binding.etUserPasswordSignup.text.toString().trim()
        if (getEmailAndPassword(email, password)){
            viewModel.signUp(email, password)
            goToLoginFragment()
        }else{
            Toast.makeText(requireContext(), "Los dos campos deben de estar rellenos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModelStates() {
        lifecycleScope.launch {
            viewModel.initialState.collect{ state ->
                if(state.registration == true){
                    Toast.makeText(requireContext(), "Registrado con éxito", Toast.LENGTH_SHORT).show()
                }else{
                    if(state.emailExists == true){
                        Toast.makeText(requireContext(), "El correo ya existe", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(requireContext(), "No se pudo hacer el registro", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    fun getEmailAndPassword(email: String, password: String): Boolean{

        var credentials = false
        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()){
            credentials = true
        }
        return credentials
    }

    private fun goToLoginFragment() {
        val loginFragment = LoginFragment()

        val transaction = parentFragmentManager.beginTransaction()
        transaction.setReorderingAllowed(true)
        transaction.replace(R.id.fcv_initial_activity, loginFragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transaction.commit()
    }
}