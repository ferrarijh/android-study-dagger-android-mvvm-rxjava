package com.example.mydaggerapplication.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.mydaggerapplication.databinding.FragmentProfileBinding
import com.example.mydaggerapplication.di.factory.ViewModelFactory
import com.example.mydaggerapplication.network.AuthResource
import com.example.mydaggerapplication.model.User
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment: DaggerFragment() {

    @Inject lateinit var factory: ViewModelFactory
    private  val pViewModel by lazy{ ViewModelProvider(this, factory).get(ProfileViewModel::class.java)}

    private var _vBinding: FragmentProfileBinding? = null
    private val vBinding
        get() = _vBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _vBinding = FragmentProfileBinding.inflate(inflater, container, false)
        return vBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setProfileObserver()
    }

    private fun setProfileObserver(){
//        pViewModel.getUser().removeObservers(viewLifecycleOwner)
        pViewModel.getUser().observe(viewLifecycleOwner){
            when (it){
                is AuthResource.Login<User> -> setProfileDetails(it)
                is AuthResource.Error<User> -> setErrorDetails(it.message)
                else -> {}
            }
        }
    }

    private fun setProfileDetails(userRes: AuthResource.Login<User>){
        vBinding!!.apply{
            tvEmail.text = userRes.data!!.email
            tvName.text = userRes.data.name
        }
    }

    private fun setErrorDetails(msg: String?){
        vBinding!!.apply{

            tvEmail.text = msg
            tvName.text = msg
        }
    }
}
