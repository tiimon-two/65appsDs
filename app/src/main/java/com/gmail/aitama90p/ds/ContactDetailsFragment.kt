package com.gmail.aitama90p.ds

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gmail.aitama90p.ds.databinding.ContactDetailsBinding
import java.lang.ref.WeakReference

class ContactDetailsFragment : Fragment() {
    private var _binding: ContactDetailsBinding? = null
    private val binding get() = _binding!!
    private var contactListService: ContactListService.ContactListServiceInterface? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if(context is ContactListService.ContactListServiceInterface) {
            contactListService = context
        }
    }

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

        requireActivity().title = requireActivity().getString(R.string.details_fragment_name)
        contactListService?.getService()?.showContactDetails(WeakReference(this))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDetach() {
        contactListService = null
        super.onDetach()
    }

    companion object {
        fun newInstance() = ContactDetailsFragment()
    }

    fun setData(data: List<Contact>) {
        binding.apply {
            var contactData = data.first()
            contactName.text = contactData.name
            contactNumberOne.text = contactData.phone
            contactNumberTwo.text = contactData.phoneTwo
            contactImage.setImageResource(contactData.photo)
            emailOne.text = contactData.email
            emailTwo.text = contactData.emailTwo
            description.text = contactData.description
        }
    }
}