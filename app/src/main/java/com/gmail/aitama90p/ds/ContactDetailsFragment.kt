package com.gmail.aitama90p.ds

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import com.gmail.aitama90p.ds.databinding.ContactDetailsBinding

class ContactDetailsFragment : Fragment() {
    private val dataModel: DataModel by activityViewModels()
    private var _binding: ContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var id: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
           _binding = ContactDetailsBinding.inflate(inflater, container, false)
           return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataModel.idMessage.observe(activity as LifecycleOwner,{
            id = it
        })

        dataModel.nameMessage.observe(activity as LifecycleOwner,{
            binding.contactName.text = it
        })

        dataModel.phoneMessage.observe(activity as LifecycleOwner,{
            binding.contactNumberOne.text = it
        })

        requireActivity().title = requireActivity().getString(R.string.details_fragment_name)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ContactDetailsFragment()
    }
}